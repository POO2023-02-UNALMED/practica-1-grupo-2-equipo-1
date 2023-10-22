package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.*;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.uiMain.menus.userMenu.userMenu;

/** Textual User Interface */
public final class TUI {

	public static void adminMenu() {
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

			// Renderable unit = new Renderable(
			// 	Tags.getByName("photography"), new String[] { "photography" }, new String[] {
			// 		"Cool object",
			// 		"$10 USD",
			// 		"very low"
			// });
			//
			// Renderer.drawCard(
			// 	unit,
			// 	new String[] {"bottoplceholdr"},
			// 	new String[] {
			// 		"Name: ",
			// 		"Price: ",
			// 		"Priority: "
			// 	}
			// );
			// Renderer.drawAllTags();

			if (true) return;

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
					userMenu.call((User) person);
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
					userMenu.call((User) person);
				} else {
					center("🫂 Alright, see you later", true, false);
					sleep(2);
				}
			}
		}
	}
}
