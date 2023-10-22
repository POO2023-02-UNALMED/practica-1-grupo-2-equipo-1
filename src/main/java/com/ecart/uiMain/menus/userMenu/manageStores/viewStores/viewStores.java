package com.ecart.uiMain.menus.userMenu.manageStores.viewStores;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderer;

final public class viewStores {
	private static void createProduct(User user, Store userStore) {
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

	private static void removeProduct(User user, Store userStore) {
		Renderer.figletBanner("remove  product");

		print(2);
		center("Removing a product will unlist it for the public", true);
		print();

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

	private static void removeMembers(User user, Store userStore) {
		Renderer.figletBanner("manage  members");

		String memberName = conditionalInquiry(
				new String[] { "Which member would you like to remove?", "(type their name) 👉 " },
				i -> Tags.getByName(i) == null);

		print(2);
		// center(retval.getMessage(), true);
		sleep(2);
	}

	private static void updateSettings(User user, Store userStore) {
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

	public static void call(User user) {
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
				new String[] { "Please select one store you would like to manage", "(type its name) 👉 " },
				i -> Store.validate(i, user.getStores()) == null);

		Store userStore = Store.validate(storeName, user.getStores());

		clear();

		LinkedHashMap<String, Runnable> submenu = new LinkedHashMap<>();

		submenu.put("🩳 Create product", () -> createProduct(user, userStore));
		submenu.put("❗ Remove product", () -> removeProduct(user, userStore));
		submenu.put("💁 Remove members", () -> removeMembers(user, userStore));
		submenu.put("🗃️  Update settings", () -> updateSettings(user, userStore));

		menu("management", submenu, true, false, true);
	}
}
