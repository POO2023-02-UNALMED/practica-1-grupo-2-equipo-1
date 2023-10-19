package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.github.lalyos.jfiglet.FigletFont;
import com.ecart.App;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.util.LinkedHashMap;

class Renderer {

	private static Scanner scnr = new Scanner(System.in);

	public static void card(HashMap<String, String> data, Tags tag) {

	}

	public static void dialogBox() {
	} // one

	public static void tiles() {
	} // multiple stacked

	private static void errorMenu() {
		center("Please pick one of the options", true);
		sleep(2);
	}

	public static void figletBanner(String banner) {
		figletBanner(banner, 15);
	}

	public static void figletBanner(String banner, int vcentr) {
		figletBanner(banner, vcentr, 3);
	}

	public static void figletBanner(String banner, int vcentr, int postSpaces) {
		vcenter(vcentr);

		try {
			banner = FigletFont.convertOneLine(banner);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int averageLength = averageLength(banner.split("\n"), true);

		for (String line : banner.split("\n"))
			center(line, averageLength);

		print(postSpaces);
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

	private static String generateHorizontalLine(Tags[] tags, int numCols, String subscript, String marker, String border) {
		StringBuilder line = new StringBuilder();
		int counter = 1;
		for (Tags tag : tags) {
			// very hacky, not to happy with the implementation
			int addi = 1;
			int comparer = biggestStringSize(tag.split()) + subscript.length() + tag.name().length();
			if (comparer % 2 == 0) addi = 3;
			else if (comparer % 5 == 0) addi = 2;

			line.append(border + marker.repeat(minBoxSize(tag, subscript) + addi) + border + "  ");
			if (counter == numCols)
				break;
			counter++;
		}
		print();
		return line.toString();
	}

	private static int biggestStringSize(String[] strings) {
		int biggest = 0;

		for (String line : strings) {
			if (line.length() > biggest)
				biggest = line.length();
		}

		return biggest;
	}

	public static int minBoxSize(Tags tag, String subscript) {
		int biggest = biggestStringSize(tag.split());

		subscript = tag.name() + subscript;
		if (subscript.length() > biggest)
			biggest = subscript.length();

		if (biggest % 2 != 0)
			biggest++;

		return biggest;
	}

	public static void renderTiledPattern(Tags[] tags, int numCols, String subscript) {
		int linesCount = tags[0].split().length;
		String horizontalLine = generateHorizontalLine(tags, numCols, subscript, "-", " ");
		String horizontalSpaces = generateHorizontalLine(tags, numCols, subscript, " ", "|");

		System.out.println(horizontalLine);
		for (int row = 0; row < linesCount; row++) {
			for (int col = 0; col < numCols; col++) {
				if (col > 0) {
					print("  ", false);
				}
				String[] lines = tags[col].split();
				String line = lines[row];

				int padding = 0;
				int minBoxSize = minBoxSize(tags[col], subscript);
				if (minBoxSize > line.length()) {
					padding = minBoxSize - line.length();
					padding = (int) Math.ceil(padding / 2);
					if (padding % 2 != 0)
						padding++;
				}

				print("| " + " ".repeat(padding) + line + " ".repeat(padding) + " |", false);
			}
			print();
		}

		print(horizontalSpaces);
		print(horizontalLine);
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
				print(" ".repeat(biggest) + "(" + (i + 1) + ") " + entry.getKey());
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
			} else if ((Integer.valueOf(input) - 1) <= orderedOptions.size()) {
				Map.Entry<String, Runnable> entry = orderedOptions.get(Integer.valueOf(input) - 1);
				Runnable fn = entry.getValue();
				clear();
				fn.run();
			} else {
				errorMenu();
			}
		}
	}
}
