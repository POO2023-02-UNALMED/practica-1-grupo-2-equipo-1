package com.ecart.uiMain.menus.userMenu.viewShoppingCart;

import java.util.LinkedHashMap;

import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.gestorAplicacion.transactions.ShoppingCart;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.Commons;
import static com.ecart.uiMain.Input.*;
import static com.ecart.uiMain.Utils.*;

final public class ViewShoppingCart {
	private static boolean drawProducts(User user) {
		return Commons.drawProducts(user.getShoppingCart().getCartProducts());
	}

	private static void placeOrder(User user) {
		Renderer.drawBanner("place  order");
		boolean showedProducts = drawProducts(user);
		if (!showedProducts) return;

		String response = conditionalInquiry(
				new String[] { "Would you like to place an order for your products?",
						"[yes|no] 👉 " },
				i -> !(i.equals("yes") == true || i.equals("no") == true));

		if (response.equals("no"))
			return;

		Retval retval = user.placeOrder();
		Commons.dialog(retval);
	}

	private static void deleteItems(User user) {
		Renderer.drawBanner("delete  items");
		boolean showedProducts = drawProducts(user);
		if (!showedProducts) return;

		String productToOrderName = conditionalInquiry(
				new String[] { "Please select the product order you'd like to delete:", "(type its name) 👉 " },
				i -> Product.validate(i, user.getShoppingCart().getCartProducts()) == null && i.equals("") == false);

		if (productToOrderName.equals(""))
			return;

		Product productToOrder = Product.validate(productToOrderName, user.getShoppingCart().getCartProducts());

		Retval retval = user.getShoppingCart().removeItem(productToOrder);
		Commons.dialog(retval);
	}

	private static void editItems(User user) {
		Renderer.drawBanner("edit  items");
		boolean showedProducts = drawProducts(user);
		if (!showedProducts) return;

		String productToOrderName = conditionalInquiry(
				new String[] { "Please select the product order you'd like to manage:", "(type its name) 👉 " },
				i -> Product.validate(i, user.getShoppingCart().getCartProducts()) == null && i.equals("") == false);

		if (productToOrderName.equals(""))
			return;

		Product productToOrder = Product.validate(productToOrderName, user.getShoppingCart().getCartProducts());

		clear();
		Renderer.drawBanner("update order");

		Renderable unit = new Renderable(
				productToOrder.getTag(),
				new String[] { productToOrder.getTag().name() },
				new String[] {
					productToOrder.getName(),
					"$" + Double.toString(productToOrder.getPrice()),
					Integer.toString(productToOrder.getQuantity()),
				});

		Renderer.drawCard(unit, null, new String[] {
				"Name: ", "Price: ", "Available stock: "
		});

		print(2);

		String newQuantity = conditionalInquiry(
				new String[] { "Please select the new quantity:", "(type a number) 👉 " },
				i -> ShoppingCart.isProductAvailable(productToOrder, Integer.valueOf(i)) == false && i.equals("") == false);

		if (newQuantity.equals(""))
			return;

		Retval retval = user.getShoppingCart().updateItem(productToOrder, Integer.valueOf(newQuantity));
		Commons.dialog(retval);
	}

	public static void call(User user) {

		LinkedHashMap<String, Runnable> submenu = new LinkedHashMap<>();

		if (user.getShoppingCart().getCartProducts().isEmpty()) {
			Renderer.drawBanner("shopping cart");
			center("Looks like you don't have anything in your shopping cart. Go shopping!", true);
			sleep(2);
			return;
		}

		submenu.put("🧾 Place order", () -> placeOrder(user));
		submenu.put("🗂️  Edit items", () -> editItems(user));
		submenu.put("❌ Delete items", () -> deleteItems(user));

		menu("shopping cart", submenu, true);
	}
}
