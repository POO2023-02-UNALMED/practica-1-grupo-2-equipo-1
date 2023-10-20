package com.ecart;

import static com.ecart.uiMain.TUI.menu;
import static com.ecart.uiMain.Utils.clear;

/** App's entry point */
public class App {
	public static void main(String[] args) {
		if (System.console() != null) {
			// executed before the main program exists (forcefully)
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				shutdown(false);
			}));

			menu();
		} else
			System.err.println("The system is not running interactively");
	}

	public static void shutdown(boolean force) {
		// <save db here>
		// clear();
		// if (force)
		// 	System.exit(0);
	}
}
