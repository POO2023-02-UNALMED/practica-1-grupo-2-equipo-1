package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;

import java.util.Scanner;

/** Textual User Interface */
public class TUI {

	private static Scanner scnr = new Scanner(System.in);

	/** Render main menu */
	public static void menu() {
		int averageLength = averageLength(Banners.MAIN.split(), true);
		// while(true) {
		
			clear();
			vcenter(12);

			for (String line : Banners.MAIN.split()) {
				center(line, averageLength);
			}
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
		// }
	}
}
