package com.ecart.uiMain.menus.userMenu.payDeliveredOrders;

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

public final class PayDeliveredOrders {

	public static void call(User user) {
		Renderer.drawBanner("pay  orders");

		boolean atLeastOneOrder = false;
		for (Order order : user.getOrders()) {
			atLeastOneOrder = true;

			Renderable unit = new Renderable(
					Tags.getByName("decorations"),
					new String[] { "decorations" },
					new String[] {
							String.valueOf(order.getId()),
							String.valueOf(order.isDelivered()),
							String.valueOf(order.getTotalPrice()),
							String.valueOf(order.getPayedSoFar()),
							String.valueOf(order.isPayedFullPrice()),
					});

			Renderer.drawCard(unit, null, new String[] {
				"Order ID: ", "Was delivered: ", "Total price: ", "Payed so far: ", "Fully payed: "
			});
		}

		if (atLeastOneOrder == false) {
			center("Looks like you have not ordered anything!", true);
			sleep(2);
			return;
		}

		print(2);

		ArrayList<Order> validOrders = new ArrayList<>();

		for (Order order : user.getOrders()) {
			if (order.isDelivered())
				validOrders.add(order);
		}

		String orderId = "";
		try {
			orderId = conditionalInquiry(
					new String[] { "Please select the order ID (you can only pay orders that appear as delivered)", "(type its number) 👉 " },
					i -> Order.validate(Integer.valueOf(i), false, validOrders) == null && i.equals("") == false);
		} catch (NumberFormatException e) {
			return;
		}

		if (orderId.equals(""))
			return;

		Order pickedOrder = Order.validate(Integer.valueOf(orderId), false, user.getOrders());

		String amounToAbonar = conditionalInquiry(
				new String[] { "Please select how much money you would like to abonar", "(type a number) 👉 " },
				i -> Double.parseDouble(i) < 0 && i.equals("") == false);

		if (amounToAbonar.equals(""))
			return;

		Retval retval = user.abonarOrder(pickedOrder, Double.parseDouble(amounToAbonar));
		Commons.dialog(retval);
	}
}
