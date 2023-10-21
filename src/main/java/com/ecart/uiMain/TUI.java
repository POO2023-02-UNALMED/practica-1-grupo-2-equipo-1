package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
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

	private static Scanner scnr = new Scanner(System.in);

	private static String getRepeatedInput(String[] questions, Predicate<String> condition) {
		String input = "";
		while (true) {
			boolean nl = true;
			for (int i = 0; i < questions.length; i++) {
				if (i == questions.length - 1)
					nl = false;
				center(questions[i], true, nl);
			}

			input = scnr.nextLine();
			if (condition.test(input))
				erase(questions.length);
			else
				break;
		}

		erase(questions.length);
		return input;
	}

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

				String storeName = getRepeatedInput(
						new String[] { "Please select one store you would you manage", "(type its name) 👉 " },
						i -> Store.validate(i, user.getStores()) == null);

				Store userStore = Store.validate(storeName, user.getStores());

				clear();

				LinkedHashMap<String, Runnable> storeSubmenu = new LinkedHashMap<>();

				// public Retval createProduct(String name, double price, String description,
				// int quantity, Tags tag) {

				storeSubmenu.put("🩳 Create product", () -> {
					Renderer.figletBanner("create  product");

					ArrayList<String> questions = new ArrayList<>(List.of(
							"📛 name",
							"💲 price",
							"📄 description",
							"🗳️  quantity"));
					ArrayList<String> r = Renderer.questions(questions, 20);

					clear();
					print(3);
					Renderer.renderAllTags();

					print(2);

					String tagName = getRepeatedInput(
							new String[] { "Please select what Tag you would like for your product", "(type the name) 👉 " },
							i -> Tags.getByName(i) == null);

					Retval retval = new Retval();
					try {
						retval = user.createProduct(userStore, r.get(0), Double.parseDouble(r.get(1)), r.get(2),
								Integer.parseInt(r.get(3)),
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

					ArrayList<String> questions = new ArrayList<>(List.of(
							"💁 Store name",
							"🔒 Passcode"));
					ArrayList<String> r = Renderer.questions(questions, 20);
					Retval retval = user.addStore(r.get(0), r.get(1));

					print(2);
					center(retval.getMessage(), true);
					sleep(2);
				});

				storeSubmenu.put("💁 Remove members", () -> {
					Renderer.figletBanner("manage  members");


					String memberName = getRepeatedInput(
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

					ArrayList<String> questions = new ArrayList<>(List.of(
							"💁 New store name",
							"🔒 New passcode",
							"📄 New description"));
					ArrayList<String> r = Renderer.questions(questions, 20);

					Retval retval = new Retval("Updated settings successfully!", true);

					if (r.get(0) != "" && Store.validate(r.get(0)) != null)
						retval = new Retval("The store name is already taken", false);
					else {
						if (r.get(0) != "")
							userStore.setName(r.get(0));
						if (r.get(1) != "")
							userStore.setPassword(r.get(1));
						if (r.get(2) != "")
							userStore.setDescription(r.get(2));
					}

					print(2);
					center(retval.getMessage(), true);
					sleep(2);
				});

				Renderer.menu("management", storeSubmenu, true, false, true);
			});

			submenu.put("🗃️  Create store", () -> {

				Renderer.figletBanner("create  store");

				ArrayList<String> questions = new ArrayList<>(List.of(
						"💁 Store name",
						"🔒 Passcode",
						"📄 Description"));
				ArrayList<String> r = Renderer.questions(questions, 20);
				sleep(1);

				if (r.get(0) == "") {
					center(new Retval("The name of the store must be non-empty", false).getMessage(), true);
					sleep(2);
					return;
				}

				clear();
				print(3);
				Renderer.renderAllTags();

				print(2);

				String tagName = getRepeatedInput(
						new String[] { "Please select what Tag you would like for your store", "(type the name) 👉 " },
						i -> Tags.getByName(i) == null);

				Retval retval = user.createStore(r.get(0), r.get(1), r.get(2), Tags.getByName(tagName));

				center(retval.getMessage(), true);
				sleep(2);
			});

			submenu.put("🥋 Join store", () -> {
				Renderer.figletBanner("join  store");

				ArrayList<String> questions = new ArrayList<>(List.of(
						"💁 Store name",
						"🔒 Passcode"));
				ArrayList<String> r = Renderer.questions(questions, 20);
				Retval retval = user.addStore(r.get(0), r.get(1));

				print(2);
				center(retval.getMessage(), true);
				sleep(2);
			});

			Renderer.menu("stores", submenu, true);
		});
		options.put("🗞️  Manage your orders", () -> center("manage your balance", true));
		options.put("👱 Profile settings", () -> center("showing menu to update personal info"));

		Renderer.menu("login", options, true, true);
	}

	/**
	 * Generate dummy data.
	 * This is for testing purposes only! while the persistence layer is baking...
	 */
	public static void dummyData() {
		new User("q", "1");
	}

	/** Render main menu */
	public static void menu() {

		// dummy data. Don't delete (for now)
		dummyData();

		while (true) {

			clear();
			vcenter(12);

			centerBanner(Banners.MAIN);

			print();
			center("===== Empower Your Passion, Share Your Creations =====", true);
			center("(Ctrl + C to exit)", true);
			print(2);

			// Tags[] tags = { Tags.PHOTOGRAPHY, Tags.PLUSHIES, Tags.MUSIC };
			// int numCols = tags.length;
			//

			// sleep(3);

			// if (true) return;

			ArrayList<String> questions = new ArrayList<>(List.of("💁 Username", "🔒 Password"));
			ArrayList<String> r = Renderer.questions(questions, 6);
			String username = r.get(0);
			String password = r.get(1);

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
				String input = getRepeatedInput(
						new String[] { "Hmm looks like you don't have an account. Would you like to create one?",
								"[yes|no] 👉 " },
						i_ -> !(i_.equals("yes") == true || i_.equals("no") == true));

				if (input.equals("yes")) {
					if (User.validate(username) != null) {
						username = getRepeatedInput(
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
