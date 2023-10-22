package com.ecart.uiMain.menus.userMenu;

import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.uiMain.menus.userMenu.manageStores.manageStores;

/*
 * Structure:
 * 
 * If an option requires more than one screen (i.e. has a submenu),
 * a directory is created and inside of it a .java file, both with
 * the same name of the functionality
 *
 * Otherwise the option's method is created in the same menu class
 * */

final public class userMenu {

	public static void call(User user) {
		// maps are abstracts, while HashMaps aren't
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();

		options.put("🛍️  Go shopping!", () -> center("viewing stores", true));
		options.put("🏪 Manage your stores", () -> manageStores.call(user));
		options.put("🗞️  Manage your orders", () -> center("manage your balance", true));
		options.put("👱 Profile settings", () -> center("showing menu to update personal info"));

		menu("login", options, true, true);
	}

}
