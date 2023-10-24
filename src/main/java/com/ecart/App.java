package com.ecart;

import static com.ecart.uiMain.TUI.mainMenu;
import static com.ecart.uiMain.Utils.clear;

import com.ecart.baseDatos.Deserializador;
import com.ecart.baseDatos.Serializador;

/** App's entry point */
public class App {
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			shutdown(false);
		}));

		Deserializador.loadObjects();
		mainMenu();
	}

	public static void shutdown(boolean force) {
		Serializador.saveObjects();
		clear();

		if (force)
			System.exit(0);
	}
}
