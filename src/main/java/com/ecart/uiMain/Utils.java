package com.ecart.uiMain;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

/** TUI Utilities */
public final class Utils {

	/** Clear the terminal screen */
	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/** Move up a given number of _lines_ */
	public static void move(int lines) { // note: lines = 0 means 1 up
		System.out.print(String.format("\033[%dA", lines));
	}

	public static void erase() {
		System.out.print("\033[2K\033[1G"); // current line
	}

	/** Erase _totalLines_ amount of previous lines in screen */
	public static void erase(int totalLines) {
		// reference:
		// https://www.quora.com/How-do-you-clear-a-line-from-a-Java-programs-output-screen

		for (int i = 0; i <= totalLines; i++) {
			System.out.print("\033[2K\033[1G"); // del current line, move cursor to beginning
			if (i != totalLines)
				move(0); // move up, but if its the last line, stay put
		}
	}

	/** Sleep the current's thread execution for _seconds_ */
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}

	public static void print() {
		print("", true);
	}

	public static void print(int totalNL) {
		print("\n".repeat(totalNL), false);
	}

	public static void print(String str) {
		print(str, true);
	}

	public static int averageLength(String[] str, boolean halve) {
		float average = 0;
		for (String line : str)
			average += line.length();

		return (int) ((average / str.length) / (halve ? 2 : 1));
	}

	public static String getBiggestString(String[] strings) {
		String biggest = "";

		for (String line : strings) {
			if (line.length() > biggest.length())
				biggest = line;
		}

		return biggest;
	}

	public static String[] lhmKeysToArray(LinkedHashMap<String, String> lhm) {
		Set<String> keySet = lhm.keySet();
		return keySet.toArray(new String[keySet.size()]);
	}

	public static int getBiggestStringSize(String[] strings) {
		return getBiggestString(strings).length();
	}

	/** Print to STDOUT */
	public static void print(String str, boolean nl) {
		System.out.print(str + (nl ? "\n" : ""));
	}

	public static void center(String str) {
		center(str, 0, false, true, false);
	}

	public static void center(String str, int offset) {
		center(str, offset, false, true, false);
	}

	public static void center(String str, boolean true_center) {
		center(str, 0, true_center, true, false);
	}

	public static void center(String str, boolean true_center, boolean nl) {
		center(str, 0, true_center, nl, false);
	}

	public static void center(String str, int offset, boolean true_center, boolean nl) {
		center(str, offset, true_center, nl, false);
	}

	/** Center text horizontally in the screen */
	public static String center(String str, int offset, boolean true_center, boolean nl, boolean getSpace) {
		String space = " ".repeat(
				(getDimensions()[1] / 2)
						- offset
						- (true_center ? str.length() / 2 : 0));

		if (getSpace)
			return space;
		else
			print(space + str, nl);

		return "";
	}


	/**
	 * Center text vertically in the screen
	 *
	 * @param screenSection divide the screen in Halfs (2), Thirds (3), Fourths (4), etc.
	 * @param amount
	 */
	public static void vcenter(int screenSection, int amount) {

		int rows = getDimensions()[0];
		rows = rows > 100 ? 9 : (rows / screenSection) - amount; // extreme case for virtual terminals (e.g. IDE's)

		System.out.print("\n".repeat(rows < 0 ? 0 : rows));
	}

	/** Get the terminal's width and height */
	public static int[] getDimensions() {
		int[] dimensions = { -1, -1 }; // default
		Process process;

		try {
			if (System.getProperty("os.name").contains("Win")) {
				process = new ProcessBuilder("cmd", "/c", "mode").redirectErrorStream(true).start();
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

				String line;
				while ((line = reader.readLine()) != null) {
					if (line.toLowerCase().contains("rows") || line.contains("neas")) { // "neas" because tildes are broken
						dimensions[0] = Integer.parseInt(line.replaceAll("\\D", ""));
					} else if (line.toLowerCase().contains("columns") || line.toLowerCase().contains("columnas")) {
						dimensions[1] = Integer.parseInt(line.replaceAll("\\D", ""));
					}
				}
			} else {
				process = new ProcessBuilder("sh", "-c", "stty size </dev/tty").start();
				String rawDimensions = String.valueOf(new String(process.getInputStream().readAllBytes()).trim());

				dimensions = Arrays.stream(rawDimensions.split("\\s+"))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

		return dimensions;
	}
}
