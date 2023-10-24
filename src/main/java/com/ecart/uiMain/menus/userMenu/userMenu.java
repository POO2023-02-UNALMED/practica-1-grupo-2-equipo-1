package com.ecart.uiMain.menus.userMenu;

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
import com.ecart.uiMain.menus.userMenu.manageBankAccount.ManageBankAccount;
import com.ecart.uiMain.menus.userMenu.manageStores.manageStores;
import com.ecart.uiMain.menus.userMenu.suggestProducts.SuggestProducts;
import com.ecart.uiMain.menus.userMenu.viewShoppingCart.ViewShoppingCart;

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

	private static void updateSettings(User user) {
		Renderer.drawBanner("update  settings");

		Renderable unit = new Renderable(
				Tags.getByName("decorations"),
				new String[] { "descorations" },
				new String[] {
						user.getName(),
						user.getPassword(),
						Arrays.toString(user.getAddress()),
				});

		Renderer.drawCard(unit, null, new String[] {
				"Name: ", "Password: ", "Address: "
		});

		print(2);
		center("Only type what you wish to change. Press <Enter> for the rest", true);
		print();

		String[] r = questionnaire(
				new String[] {
						"💁 New name",
						"🔒 New password",
						"📄 New Calle",
						"📄 New Carrera"
				});

		Retval retval = new Retval("Updated settings successfully!", true);

		if (r[0] != "" && User.validate(r[0]) != null)
			retval = new Retval("That username is already taken", false);
		else {
			try {
				int calle = -1;
				int carrera = -1;

				if (r[2] != "")
					calle = Integer.parseInt(r[2]);

				if (r[3] != "")
					carrera = Integer.parseInt(r[3]);

				calle = calle == -1 ? user.getAddress()[0] : calle;
				carrera = carrera == -1 ? user.getAddress()[1] : carrera;

				int oldAddress[] = user.getAddress();

				if (!Person.isAddressAvailable(new int[] { calle, carrera }) && calle != oldAddress[0]
						&& carrera != oldAddress[1]) {
					retval = new Retval("Error: that address is already taken", false);
				} else {
					if (r[0] != "")
						user.setName(r[0]);
					if (r[1] != "")
						user.setPassword(r[1]);

					user.setAddress(new int[] { calle, carrera });
				}

			} catch (ClassCastException e) {
				retval = new Retval(
						"Failed to parse your input. Make sure you are not typing letters in the place of numbers",
						false);
			}
		}

		Commons.dialog(retval);
	}

	public static void call(User user) {
		// maps are abstracts, while HashMaps aren't
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();
		
		options.put("🛍️  Go shopping!", () -> GoShopping.call(user));
		options.put("🏪 Manage your stores", () -> manageStores.call(user));
		options.put("🗞️  View shopping cart", () -> ViewShoppingCart.call(user));
		options.put("🗳️  Suggest products", () -> SuggestProducts.call(user));
		options.put("💳 Manage bank account", () -> ManageBankAccount.call(user));
		options.put("🚲 Deliver Orders", () -> SuggestProducts.call(user));
		options.put("👱 Profile settings", () -> updateSettings(user));

		menu("login", options, true, true);
	}

}
