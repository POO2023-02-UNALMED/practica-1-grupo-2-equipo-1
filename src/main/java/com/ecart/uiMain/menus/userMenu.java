package com.ecart.uiMain.menus;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderer;

/*
 * The nomenclature being used is:
 * every "main" menu is named as is, and any submenus are named with "_m0", where
 * "mo" should be replaced with the main menus' name. Submenus can have multiple
 * submenus too.
 *
 * For example:
 *
 * manageStores(): the main menu
 * _mg_viewStores(): submenu 
 * _mg_vs_createProduct(): submenu option of the submenu
 *
 * Tips:
 *		1. In the source code, keep them ordered as in the menus themselves
 *		2. Remove articles and pronouns from the (sub)menus' function names
 *
 * */

final public class userMenu {

	// ############### manageStores
	// #################### _mg_viewStores 
	private static void _mg_vs_createProduct(User user, Store userStore) {
		Renderer.figletBanner("create  product");

		String[] r = questionnaire(
				new String[] {
						"📛 name",
						"💲 price",
						"📄 description",
						"🗳️  quantity"
				});

		clear();
		print(3);
		Renderer.renderAllTags();

		print(2);

		String tagName = conditionalInquiry(
				new String[] { "Please select what Tag you would like for your product", "(type the name) 👉 " },
				i -> Tags.getByName(i) == null);

		Retval retval = new Retval();
		try {
			retval = user.createProduct(userStore, r[0], Double.parseDouble(r[1]), r[2],
					Integer.parseInt(r[3]),
					Tags.getByName(tagName));
		} catch (ClassCastException e) {
			retval = new Retval(
					"Failed to parse your input. Make sure you are not typing letters in the place of numbers",
					false);
		}

		center(retval.getMessage(), true);
		sleep(2);
	}

	private static void _mg_vs_deleteProduct(User user, Store userStore) {
		Renderer.figletBanner("delete  product");

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

	private static void _mg_vs_removeMembers(User user, Store userStore) {
		Renderer.figletBanner("manage  members");

		String memberName = conditionalInquiry(
				new String[] { "Which member would you like to remove?", "(type their name) 👉 " },
				i -> Tags.getByName(i) == null);

		print(2);
		// center(retval.getMessage(), true);
		sleep(2);
	}

	private static void _mg_vs_updateSettings(User user, Store userStore) {
		Renderer.figletBanner("update  settings", 20);

		LinkedHashMap<String, String> data = new LinkedHashMap<>();

		data.put("Name: ", userStore.getName());
		data.put("Passcode: ", userStore.getPassword());
		data.put("Description: ", userStore.getDescription());

		Renderer.renderCard(userStore.getTag(), data);

		print(2);
		center("Only type what you wish to change. Press <Enter> for the rest", true);
		print();

		String[] r = questionnaire(
				new String[] {
						"💁 New store name",
						"🔒 New passcode",
						"📄 New description"
				});

		Retval retval = new Retval("Updated settings successfully!", true);

		if (r[0] != "" && Store.validate(r[0]) != null)
			retval = new Retval("The store name is already taken", false);
		else {
			if (r[0] != "")
				userStore.setName(r[0]);
			if (r[1] != "")
				userStore.setPassword(r[1]);
			if (r[2] != "")
				userStore.setDescription(r[2]);
		}

		print(2);
		center(retval.getMessage(), true);
		sleep(2);

	}

	private static void _mg_viewStores(User user) {
		Renderer.figletBanner("your  stores", 20);

		ArrayList<Store> stores = user.getStores();

		if (stores.isEmpty()) {
			center("Looks like you don't have any stores!", true);
			sleep(2);
			return;
		}

		for (Store store : stores) {
			LinkedHashMap<String, String> data = new LinkedHashMap<>();

			data.put("Name: ", store.getName());
			data.put("Tag: ", store.getTag().name());
			data.put("Reviews: ", "🌟 🌟 🌟 🌟 🌟");

			Renderer.renderCard(store.getTag(), data);
			print();
		}

		String storeName = conditionalInquiry(
				new String[] { "Please select one store you would you manage", "(type its name) 👉 " },
				i -> Store.validate(i, user.getStores()) == null);

		Store userStore = Store.validate(storeName, user.getStores());

		clear();

		LinkedHashMap<String, Runnable> storeSubmenu = new LinkedHashMap<>();

		storeSubmenu.put("🩳 Create product", () -> _mg_vs_createProduct(user, userStore));
		storeSubmenu.put("❗ Delete product", () -> _mg_vs_deleteProduct(user, userStore));
		storeSubmenu.put("💁 Remove members", () -> _mg_vs_removeMembers(user, userStore));
		storeSubmenu.put("🗃️  Update settings", () -> _mg_vs_updateSettings(user, userStore));

		menu("management", storeSubmenu, true, false, true);
	}
	// #################### _mg_viewStores 

	private static void _mg_createStore(User user) {
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

	private static void _mg_joinStore(User user) {
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

	private static void manageStores(User user) {

		LinkedHashMap<String, Runnable> submenu = new LinkedHashMap<>();

		submenu.put("🖼️  View your stores", () -> _mg_viewStores(user));
		submenu.put("🗃️  Create store", () -> _mg_createStore(user));
		submenu.put("🥋 Join store", () -> _mg_joinStore(user));

		menu("stores", submenu, true);
	}
	// ############### manageStores

	public static void mainMenu(User user) {
		// maps are abstracts, while HashMaps aren't
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();

		options.put("🛍️  Go shopping!", () -> center("viewing stores", true));
		options.put("🏪 Manage your stores", () -> manageStores(user));
		options.put("🗞️  Manage your orders", () -> center("manage your balance", true));
		options.put("👱 Profile settings", () -> center("showing menu to update personal info"));

		menu("login", options, true, true);
	}

}
