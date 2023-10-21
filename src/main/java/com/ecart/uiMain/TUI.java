package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.*;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;

import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

import com.github.lalyos.jfiglet.FigletFont;

/** Textual User Interface */
public class TUI {

	public static void adminMenu() {
	}

	public static void userMenu(User user) {
		// maps are abstracts, while HashMaps aren't
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();

		options.put("🛍️  Go shopping!", () -> center("viewing stores", true));
		options.put("🏪 Manage your stores", () -> {
			LinkedHashMap<String, Runnable> submenu = new LinkedHashMap<>();

			submenu.put("🖼️  View your stores", () -> {
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

				// public Retval createProduct(String name, double price, String description,
				// int quantity, Tags tag) {

				storeSubmenu.put("🩳 Create product", () -> {
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
				});

				storeSubmenu.put("❗ Delete product", () -> {
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
				});

				storeSubmenu.put("💁 Remove members", () -> {
					Renderer.figletBanner("manage  members");

					String memberName = conditionalInquiry(
							new String[] { "Which member would you like to remove?", "(type their name) 👉 " },
							i -> Tags.getByName(i) == null);

					print(2);
					// center(retval.getMessage(), true);
					sleep(2);
				});

				storeSubmenu.put("🗃️  Update settings", () -> {
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
				});

				menu("management", storeSubmenu, true, false, true);
			});

			submenu.put("🗃️  Create store", () -> {

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
			});

			submenu.put("🥋 Join store", () -> {
				Renderer.figletBanner("join  store");

				String[] r = questionnaire(
						new String[] {
								"💁 Store name",
								"🔒 Passcode"
						});

				Retval retval = user.addStore(r[0], r[1] );

				print(2);
				center(retval.getMessage(), true);
				sleep(2);
			});

			menu("stores", submenu, true);
		});
		options.put("🗞️  Manage your orders", () -> center("manage your balance", true));
		options.put("👱 Profile settings", () -> center("showing menu to update personal info"));

		menu("login", options, true, true);
	}

	/**
	 * Generate dummy data.
	 * This is for testing purposes only! while the persistence layer is baking...
	 */
	public static void dummyData() {
		new User("q", "1");
	}

	/** Render main menu */
	public static void mainMenu() {

		// dummy data. Don't delete (for now)
		dummyData();

		while (true) {

			clear();
			vcenter(12);

			Renderer.centerBanner(Banners.MAIN);

			print();
			center("===== Empower Your Passion, Share Your Creations =====", true);
			center("(Ctrl + C to exit)", true);
			print(2);

			String[] r = questionnaire(
					new String[] { "💁 Username", "🔒 Password" }, 6);

			String username = r[0];
			String password = r[1];

			Person person = User.validate(username, password);
			// TODO: rework Admin too
			// if (person == null) person = Admin.validate(username, password);

			print();

			// show correct menu or prompt potential user to create an account
			if (person != null) {
				center("Welcome back " + username + "!", true);
				sleep(2);

				if (person instanceof User)
					userMenu((User) person);
				else if (person instanceof Admin)
					adminMenu();

			} else {
				String input = conditionalInquiry(
						new String[] { "Hmm looks like you don't have an account. Would you like to create one?",
								"[yes|no] 👉 " },
						i_ -> !(i_.equals("yes") == true || i_.equals("no") == true));

				if (input.equals("yes")) {
					if (User.validate(username) != null) {
						username = conditionalInquiry(
								new String[] { "Your desired username is already being used", "New username: " },
								i -> User.validate(i) != null);
					}

					print();
					person = new User(username, password);
					center("✅ Your account was created successfully!", true, false);
					sleep(2);
					userMenu((User) person);
				} else {
					center("🫂 Alright, see you later", true, false);
					sleep(2);
				}
			}
		}
	}
}
