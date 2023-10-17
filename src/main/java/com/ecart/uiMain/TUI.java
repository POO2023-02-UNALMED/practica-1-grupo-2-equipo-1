package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import com.ecart.gestorAplicacion.entites.*;
import java.util.Map;
import java.util.HashMap;

import java.util.Scanner;

/** Textual User Interface */
public class TUI {

	private static Scanner scnr = new Scanner(System.in);

	public static void userMenu() {
		// maps are abstracts, while HashMaps aren't
		Map<String, Runnable> options = new HashMap<>();

		options.put("Explore stores", () -> center("viewing stores", true));
		options.put("Manage your stores", () -> center("manage your stores", true));
		options.put("Managa your balance", () -> center("manage your balance", true));
		// options.put("Update personal info", () -> center("showing menu to update
		// personal info"));

		Renderer.menu(Banners.LOGIN, options);
	}

	/** Render main menu */
	public static void menu() {
		while (true) {

			clear();
			vcenter(12);

			centerBanner(Banners.MAIN);

			print();
			center("===== Empower Your Passion, Share Your Creations =====", true);
			center("(Ctrl + C or Z to exit)", true);
			print(2);

			center("💁 Username: ", 11, false, true);
			center("🔒 Password: ", 11, false, false);

			move(0);
			erase();

			center("💁 Username: ", 11, false, false);
			String username = scnr.nextLine();

			center("🔒 Password: ", 11, false, false);
			String password = scnr.nextLine();

			String ctype = ""; // if it passed, store the class type

			for (Person e : Person.getInstances()) {
				if (e.getUsername().equals(username)) {
					if (e.getPassword().equals(password)) {
						String classname = e.getClass().getName();
						ctype = classname.substring(classname.lastIndexOf(".") + 1);
					}
					break;
				}
			}
			print();

			// show correct menu or prompt potential user to create an account
			if (!ctype.isEmpty()) {
				center("Welcome back " + username + "!", true);
				sleep(2);

				switch (ctype) {
					case "User":
						userMenu();
						break;
					case "Admin":
						System.out.println("Processing for Admin");
						break;
					case "Delivery":
						System.out.println("Processing for Delivery");
						break;
					default:
						break;
				}
			} else {
				center("Hmm looks like you don't have an account. Would you like to create one?", true);
				center("[yes|no] 👉 ", 2, true, false);
				String input = scnr.nextLine();

				erase(2);

				if (input.equals("yes")) {
					while (true) {
						if (Person.getUserNames().contains(username)) {
							center("Your desired username is already being used", true);
							center("New username: ", 2, true, false);
							username = scnr.nextLine();
							erase(2);
							continue;
						}
						break;
					}

					print();
					new User(username, password);
					center("✅ Your account was created successfully!", true, false);
					sleep(2);
					userMenu();
				} else {
					center("🫂 Alright, see you later", true, false);
					sleep(2);
				}
			}
		}
	}
}
