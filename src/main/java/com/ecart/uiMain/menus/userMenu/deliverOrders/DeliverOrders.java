package com.ecart.uiMain.menus.userMenu.deliverOrders;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;

import com.ecart.gestorAplicacion.entites.Person;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.Commons;
import com.ecart.uiMain.menus.userMenu.goShopping.GoShopping;
import com.ecart.uiMain.menus.userMenu.manageStores.manageStores;
import com.ecart.uiMain.menus.userMenu.suggestProducts.SuggestProducts;
import com.ecart.uiMain.menus.userMenu.viewShoppingCart.ViewShoppingCart;

final public class DeliverOrders {
	public static void call(User user) {
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();

		// options.put("💸 Withdraw money", () -> withdrawMoney(user));
		// options.put("💰 Deposit money", () -> depositMoney(user));

		menu("bank  account", options, true, true);
	}
}
