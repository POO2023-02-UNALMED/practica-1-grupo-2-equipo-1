package com.ecart.uiMain.menus.userMenu.deliverOrders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;

import com.ecart.gestorAplicacion.entites.Person;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.gestorAplicacion.transactions.Order;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.Commons;
import com.ecart.uiMain.menus.userMenu.goShopping.GoShopping;
import com.ecart.uiMain.menus.userMenu.manageStores.manageStores;
import com.ecart.uiMain.menus.userMenu.suggestProducts.SuggestProducts;
import com.ecart.uiMain.menus.userMenu.viewShoppingCart.ViewShoppingCart;

final public class DeliverOrders {

	public static void call(User user) {
		Renderer.drawBanner("deliver  orders");

		boolean atLeastOneOrder = false;
		for (Order order : Order.getInstances()) {
			if (order.getDeliveryUser() != null)
				continue;

			String destUser = order.getDestinationUser().getName();

			if (destUser.equals(user.getName()))
				continue;

			atLeastOneOrder = true;

			int calle = order.getDestinationUser().getAddress()[0];
			int carrera = order.getDestinationUser().getAddress()[1];

			Renderable unit = new Renderable(
					Tags.getByName("decorations"),
					new String[] { "decorations" },
					new String[] {
							order.getDestinationUser().getName(),
							String.valueOf(order.getId()),
							String.valueOf(calle),
							String.valueOf(carrera),
					});

			Renderer.drawCard(unit, null, new String[] {
					"Order placer: ", "Order ID: ", "Destination calle: ", "Destination carrera: "
			});
		}

		if (atLeastOneOrder == false) {
			center("Looks like there are no available orders!", true);
			sleep(2);
			return;
		}

		print(2);

		String orderId = "";

		ArrayList<Order> validOrders = new ArrayList<>();

		for (Order order : Order.getInstances()) {
			if (!order.getDestinationUser().getName().equals(user.getName()))
				validOrders.add(order);
		}

		try {
			orderId = conditionalInquiry(
					new String[] { "Please select the order ID (you can only pay orders that appear as delivered)", "(type its number) 👉 " },
					i -> Order.validate(Integer.valueOf(i), true, validOrders) == null && i.equals("") == false);
		} catch (NumberFormatException e) {
			return;
		}

		if (orderId.equals(""))
			return;

		Order pickedOrder = Order.validate(Integer.valueOf(orderId), true);
		Retval retval = user.deliverOrder(pickedOrder, pickedOrder.getDestinationUser());
		Commons.dialog(retval);

	}
}
