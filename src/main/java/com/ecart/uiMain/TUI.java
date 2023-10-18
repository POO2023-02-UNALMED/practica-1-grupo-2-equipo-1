package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import com.ecart.gestorAplicacion.entites.*;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

/** Textual User Interface */
public class TUI {

	private static Scanner scnr = new Scanner(System.in);

	public static void adminMenu() {}

	public static void userMenu(User user) {
		// maps are abstracts, while HashMaps aren't
		LinkedHashMap<String, Runnable> options = new LinkedHashMap<>();
		
		
		options.put("🛍️  Go shopping!", () -> center("viewing stores", true));
		options.put("🏪 Manage your stores", () -> {
			LinkedHashMap<String, Runnable> submenu = new LinkedHashMap<>();

			submenu.put("🖼️  Select store", () -> center("viewing stores", true));
			submenu.put("🗃️  Create store", () -> {

				Renderer.figletBanner("create  store");

				ArrayList<String> questions = new ArrayList<>(List.of(
					"💁 Store name",
					"🔒 Passcode",
					"📄 Description"
				));
				ArrayList<String> r = Renderer.questions(questions, 20);
				boolean ok = user.createStore(r.get(0), r.get(1), r.get(2));

				print(2);
				if (ok) center("Created store successfully!", true);
				else center("Failed to craete store", true);
				sleep(2);

			});
			submenu.put("🥋 Join store", () -> center("viewing stores", true));

			Renderer.menu(Banners.STORES, submenu, true);
		});
		options.put("🗞️  Manage your orders", () -> center("manage your balance", true));
		options.put("👱 Profile settings", () -> center("showing menu to update personal info"));

		Renderer.menu(Banners.LOGIN, options, true, true);
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

			ArrayList<String> questions = new ArrayList<>(List.of("💁 Username", "🔒 Password"));
			ArrayList<String> r = Renderer.questions(questions, 6);
			String username = r.get(0);
			String password = r.get(1);

			Person person = User.getByCredentials(username, password);
			if (person == null) person = Admin.getByCredentials(username, password);

			print();

			// show correct menu or prompt potential user to create an account
			if (person != null) {
				center("Welcome back " + username + "!", true);
				sleep(2);

				if (person instanceof User) userMenu((User) person);
				else if (person instanceof Admin) adminMenu();

			} else {
				center("Hmm looks like you don't have an account. Would you like to create one?", true);
				center("[yes|no] 👉 ", 2, true, false);
				String input = scnr.nextLine();

				erase(2);

				if (input.equals("yes")) {
					while (true) {
						if (User.getUsernames().contains(username)) {
							center("Your desired username is already being used", true);
							center("New username: ", 2, true, false);
							username = scnr.nextLine();
							erase(2);
							continue;
						}
						break;
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
