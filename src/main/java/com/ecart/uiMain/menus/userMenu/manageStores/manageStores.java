package com.ecart.uiMain.menus.userMenu.manageStores;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.userMenu.manageStores.viewStores.viewStores;

final public class manageStores {

	private static void createStore(User user) {
		Renderer.figletBanner("create  store");

		String[] r = questionnaire(
				new String[] {
						"💁 Store name",
						"🔒 Passcode",
						"📄 Description"
				});
		sleep(1);

		if (r[0] == "") {
			center(new Retval("The name of the store must be non-empty", false).getMessage(), true);
			sleep(2);
			return;
		}

		clear();
		print(3);
		Renderer.renderAllTags();

		print(2);

		String tagName = conditionalInquiry(
				new String[] { "Please select what Tag you would like for your store", "(type the name) 👉 " },
				i -> Tags.getByName(i) == null);

		Retval retval = user.createStore(r[0], r[1], r[2], Tags.getByName(tagName));

		center(retval.getMessage(), true);
		sleep(2);
	}

	private static void joinStore(User user) {
		Renderer.figletBanner("join  store");

		String[] r = questionnaire(
				new String[] {
						"💁 Store name",
						"🔒 Passcode"
				});

		Retval retval = user.addStore(r[0], r[1]);

		print(2);
		center(retval.getMessage(), true);
		sleep(2);
	}

	public static void call(User user) {

		LinkedHashMap<String, Runnable> submenu = new LinkedHashMap<>();

		submenu.put("🖼️  View your stores", () -> viewStores.call(user));
		submenu.put("🗃️  Create store", () -> createStore(user));
		submenu.put("🥋 Join store", () -> joinStore(user));

		menu("stores", submenu, true);
	}
}
