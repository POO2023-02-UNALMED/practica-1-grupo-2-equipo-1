package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

import com.ecart.App;

public final class Input {
	private static Scanner scnr = new Scanner(System.in);

	/**
	 * Conditionally inquire for input
	 *
	 * @param interrogatives
	 * @param condition
	 * @return
	 */
	public static String conditionalInquiry(String[] interrogatives, Predicate<String> condition) {
		String input = "";
		while (true) {
			boolean nl = true;
			for (int i = 0; i < interrogatives.length; i++) {
				if (i == interrogatives.length - 1)
					nl = false;
				center(interrogatives[i], true, nl);
			}

			input = scnr.nextLine();
			if (condition.test(input))
				erase(interrogatives.length);
			else
				break;
		}

		erase(interrogatives.length);
		return input;
	}

	public static String[] questionnaire(String[] questions) {
		return questionnaire(questions, 20);
	}

	/**
	 * Receive multiple lines of input as answers to @param questions
	 *
	 * @param questions
	 * @param offset
	 * @return
	 */
	public static String[] questionnaire(String[] questions, int offset) {
		String[] answers = new String[questions.length];

		// left-align all of the options
		int biggest = getBiggestStringSize(questions);
		biggest = ((getDimensions()[1] / 2) - biggest / 2) - offset;

		// print the questions
		for (int i = 0; i < questions.length; i++)
			print(" ".repeat(biggest) + questions[i] + ": ");

		// receiving input
		move(questions.length); // go to first question
		erase();

		for (int i = 0; i < questions.length; i++) {
			print(" ".repeat(biggest) + questions[i] + ": ", false);
			answers[i] = scnr.nextLine();
		}

		return answers;
	}

	public static void menu(String banner, LinkedHashMap<String, Runnable> options, boolean ret) {
		menu(banner, options, ret, false, false, 15);
	}

	public static void menu(String banner, LinkedHashMap<String, Runnable> options, boolean ret, boolean exit) {
		menu(banner, options, ret, exit, false, 15);
	}

	public static void menu(String banner, LinkedHashMap<String, Runnable> options, boolean ret, boolean exit,
			boolean useFiglet) {
		menu(banner, options, ret, exit, useFiglet, 15);
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
	public static void menu(String banner, LinkedHashMap<String, Runnable> options, boolean ret, boolean exit,
			boolean useFiglet, int vcentr) {

		List<Map.Entry<String, Runnable>> optionsList = new ArrayList<>(options.entrySet());

		while (true) {
			clear();

			// banner
			vcenter(vcentr);
			if (useFiglet)
				Renderer.figletBanner(banner, 0, 0);
			else
				Renderer.centerBanner(Banners.getByName(banner));

			print(4);

			// left-align all of the options
			int biggest = 0;
			for (Map.Entry<String, Runnable> entry : options.entrySet()) {
				if (entry.getKey().length() > biggest)
					biggest = entry.getKey().length();
			}

			biggest = (getDimensions()[1] / 2) - biggest / 2;

			int j = 1;
			for (Map.Entry<String, Runnable> entry : options.entrySet()) {
				print(" ".repeat(biggest) + "(" + j + ") " + entry.getKey());
				j++;
			}

			if (exit)
				print(" ".repeat(biggest) + "(9) 🚪 Exit");
			if (ret)
				print(" ".repeat(biggest) + "(0) ⤵️  Return");

			print(2);
			center("Option 👉 ", 6, false, false);
			String input = scnr.nextLine();
			print();

			int pickedOption = Integer.valueOf(input) - 1;

			if (input.equals("0") || input.equals("")) {
				if (ret)
					return;
				else
					errorMenu();
			} else if (input.equals("9")) {
				if (exit)
					App.shutdown(true);
				else
					errorMenu();
			} else if (pickedOption <= options.size()) {
				Map.Entry<String, Runnable> entry = optionsList.get(pickedOption);
				Runnable fn = entry.getValue();

				clear();
				fn.run();

			} else {
				errorMenu();
			}
		}
	}

	private static void errorMenu() {
		center("Please pick one of the options", true);
		sleep(2);
	}
}
