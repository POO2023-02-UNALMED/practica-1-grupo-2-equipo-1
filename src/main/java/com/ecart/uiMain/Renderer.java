package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import com.ecart.App;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

class Renderer {

	private static Scanner scnr = new Scanner(System.in);

	public static void card() {
	} // one or more

	public static void dialogBox() {
	} // one

	public static void tiles() {
	} // multiple stacked

	public static void menu(Banners banner, Map<String, Runnable> options) {
		ArrayList<Map.Entry<String, Runnable>> orderedOptions = new ArrayList<>(options.entrySet());

		while (true) {
			clear();

			// banner
			vcenter(15);
			centerBanner(banner);
			print(4);

			// left-align all of the options
			int biggest = 0;
			for (int i = 0; i < orderedOptions.size(); i++) {
				Map.Entry<String, Runnable> entry = orderedOptions.get(i);
				if (entry.getKey().length() > biggest)
					biggest = entry.getKey().length();
			}

			biggest = (getDimensions()[1] / 2) - biggest / 2;

			for (int i = 0; i < orderedOptions.size(); i++) {
				Map.Entry<String, Runnable> entry = orderedOptions.get(i);
				print(" ".repeat(biggest) + "(" + i + ") " + entry.getKey());
			}
			print(" ".repeat(biggest) + "(8) Log out");
			print(" ".repeat(biggest) + "(9) Exit");

			print(2);
			center("Option 👉 ", 6, false, false);
			String input = scnr.nextLine();
			print();

			if (input.equals("8"))
				return;
			else if (input.equals("9"))
				App.shutdown(true);
			else if (Integer.valueOf(input) <= orderedOptions.size()) {
				Map.Entry<String, Runnable> entry = orderedOptions.get(Integer.valueOf(input));
				Runnable fn = entry.getValue();
				clear();
				fn.run();
				sleep(1); // testing
			} else {
				center("Please pick one of the option");
				sleep(2);
			}
		}
	}
}
