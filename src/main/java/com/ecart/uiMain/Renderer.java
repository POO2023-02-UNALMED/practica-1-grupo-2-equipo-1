package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map;

class Renderer {

	private static Scanner scnr = new Scanner(System.in);

	public static void card(HashMap<String, String> data, Tags tag) {

	}

	public static void dialogBox() {
	} // one

	public static void tiles() {
	} // multiple stacked

	private static void errorMenu() {
		center("Please pick one of the option");
		sleep(2);
	}

	public static String prompt() {
		return "";
	}

	public static ArrayList<String> questions(ArrayList<String> questions, int offset) {
		ArrayList<String> answers = new ArrayList<>();

		// left-align all of the options
		int biggest = 0;
		for (String question : questions) {
			if (question.length() > biggest)
				biggest = question.length();
		}

		biggest = ((getDimensions()[1] / 2) - biggest / 2) - offset;

		// rendering
		for (String question : questions) {
			print(" ".repeat(biggest) + question + ": ");
		}

		// receiving input
		move(questions.size()); // go to first "question"
		erase();

		for (String question : questions) {
			print(" ".repeat(biggest) + question + ": ", false);
			answers.add(scnr.nextLine());
		}

		return answers;
	}

	public static void menu(Banners banner, LinkedHashMap<String, Runnable> options, boolean ret) {
		menu(banner, options, ret, false);
	}

	/**
	 * Renders a menu. Supports at most 8-9 entries (depending if @param exit is
	 * passed)
	 *
	 * @param banner
	 * @param options
	 * @param ret
	 * @param exit
	 */
	public static void menu(Banners banner, LinkedHashMap<String, Runnable> options, boolean ret, boolean exit) {
		// TODo: didn't know about LinkedHashMaps before writing this
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

			if (exit)
				print(" ".repeat(biggest) + "(9) 🚪 Exit");
			if (ret)
				print(" ".repeat(biggest) + "(0) ⤵️  Return");

			print(2);
			center("Option 👉 ", 6, false, false);
			String input = scnr.nextLine();
			print();

			if (input.equals("0")) {
				if (ret)
					return;
				else
					errorMenu();
			} else if (input.equals("9")) {
				if (exit)
					App.shutdown(true);
				else
					errorMenu();
			} else if (Integer.valueOf(input) <= orderedOptions.size()) {
				Map.Entry<String, Runnable> entry = orderedOptions.get(Integer.valueOf(input));
				Runnable fn = entry.getValue();
				clear();
				fn.run();
				sleep(1); // testing
			} else {
				errorMenu();
			}
		}
	}
}
