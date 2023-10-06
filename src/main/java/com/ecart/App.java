package com.ecart;

import static com.ecart.uiMain.TUI.menu;

/** App's entry point */
public class App {
	public static void main(String[] args) {
		if (System.console() != null)
			menu();
		else
			System.err.println("The system is not running interactively");
	}
}
