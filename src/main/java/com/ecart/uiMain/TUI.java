package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.*;
import com.ecart.gestorAplicacion.merchandise.Store;
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
		User q = new User("q", "1", new int[] { 50, 50 });
		q.createStore("unal", "pass1234", "super cool store", Tags.getByName("office"));
		q.createProduct(Store.validate("unal", q.getStores()), "boombox", (double) 20, "super cool sound box", 10,
				Tags.getByName("music"));
		q.createProduct(Store.validate("unal", q.getStores()), "pencil", (double) 20, "super cool pencil", 100,
				Tags.getByName("office"));
		q.createProduct(Store.validate("unal", q.getStores()), "paper", (double) 20, "super cool paper", 100,
				Tags.getByName("office"));

		User p = new User("p", "2", new int[] { 51, 51 });
		p.createStore("deli postres", "pass1234", "super cool postres", Tags.getByName("food"));
		p.createProduct(Store.validate("deli postres", p.getStores()), "postresito", (double) 20, "super cool postresito",
				10, Tags.getByName("materials"));
		p.createProduct(Store.validate("deli postres", p.getStores()), "servilleta", (double) 20, "super cool servilleta",
				100, Tags.getByName("food"));
		p.createProduct(Store.validate("deli postres", p.getStores()), "plato", (double) 20, "super cool plato", 100,
				Tags.getByName("food"));
	}

	/** Render main menu */
	public static void mainMenu() {

		// dummy data. Don't delete (for now)
		dummyData();

		while (true) {

			clear();

			Renderer.drawBanner("ECART", 4, 1);

			center("===== Empower Your Passion, Share Your Creations =====", true);
			center("(Ctrl + C to exit)", true);
			print(2);

			String[] r = questionnaire(
					new String[] { "💁 Username", "🔒 Password" }, 6);

			String username = r[0];
			String password = r[1];

			Person person = User.validate(username, password);

			print();

			// show correct menu or prompt potential user to create an account
			if (person != null) {
				center("Welcome back " + username + "!", true);
				sleep(2);

				if (person instanceof User)
					userMenu.call((User) person);

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

					String calle = "";
					String carrera = "";
					while (true) {
						calle = conditionalInquiry(
								new String[] { "We need your address to be able to register you in the system",
										"Your calle (number from 0 to 100): " },
								i -> (Integer.parseInt(i) > 100 || Integer.parseInt(i) < 0));

						carrera = conditionalInquiry(
								new String[] { "We need your address to be able to register you in the system",
										"Your carrera (number from 0 to 100): " },
								i -> (Integer.parseInt(i) > 100 || Integer.parseInt(i) < 0));

						if (!Person.isAddressAvailable(new int[] { Integer.parseInt(calle), Integer.parseInt(carrera) })) {
							center("⚠️  This address is already taken", true);
							sleep(2);
							erase(1);
						} else
							break;
					}

					print();
					person = User.create(
							username,
							password,
							new int[] { Integer.parseInt(calle), Integer.parseInt(carrera) });

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
