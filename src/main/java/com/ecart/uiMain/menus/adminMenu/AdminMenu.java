package com.ecart.uiMain.menus.adminMenu;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;

import com.ecart.gestorAplicacion.entites.Admin;
import com.ecart.gestorAplicacion.entites.Delivery;
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

final public class AdminMenu {

	private static void createDelivery(Admin admin) {
		Renderer.drawBanner("create delivery");

		String[] r = questionnaire(
				new String[] {
						"💁 name",
						"🔒 password",
						"📄 Calle",
						"📄 Carrera"
				});

		if (r[0].equals("") || r[1].equals("") || r[2].equals("") || r[3].equals(""))
			Commons.dialog(new Retval("Not enough data provided", false));

		Delivery newDelivery = Delivery.validate(r[0]);

		if (newDelivery != null) {
			Commons.dialog(new Retval("There is already a delivery driver with that name", false));
			return;
		}

		int calle = Integer.valueOf(r[2]);
		int carrera = Integer.valueOf(r[3]);

		if (!Person.isAddressAvailable(new int[] {calle, carrera})) {
			Commons.dialog(new Retval("That address is already taken", false));
			return;
		}

		Retval retval = admin.createDelivery(r[0], r[1], new int[] {calle, carrera});
		Commons.dialog(retval);
	}

	private static void createOtherAdmins() {
		Renderer.drawBanner("create admin");

		String[] r = questionnaire(
				new String[] {
						"💁 name",
						"🔒 password",
						"📄 Calle",
						"📄 Carrera"
				});

		if (r[0].equals("") || r[1].equals("") || r[2].equals("") || r[3].equals(""))
			Commons.dialog(new Retval("Not enough data provided", false));

		int calle = Integer.valueOf(r[2]);
		int carrera = Integer.valueOf(r[3]);

		Admin newAdmin = Admin.create(r[0], r[1], new int[] {calle, carrera});

		Retval retval = new Retval("Created admin successfully!");

		if (newAdmin == null)
			retval = new Retval("Failed to created an admin with the provided data");

		Commons.dialog(retval);
	}

	// private static void payDelivery(Admin admin) {
	//
	// }

	public static void call(Admin admin) {
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();

		

		options.put("🙍 Create delivery", () -> createDelivery(admin));
		options.put("⛹️  Create other admins", () -> createOtherAdmins());

		menu("admin menu", options, true, true);
	}

}
