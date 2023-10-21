package com.ecart.gestorAplicacion.merchandise;

import com.ecart.gestorAplicacion.meta.Pictogram;

public enum Tags implements Pictogram<Tags> {
	PHOTOGRAPHY("""
			  ▄▄░▄███▄    .
			▄▀▀▀▀░▄▄▄░▀▀▀▀▄
			█▒▒▒▒█░░░█▒▒▒▒█
			█▒▒▒▒▀▄▄▄▀▒▒▒▒█
			▀▄▄▄▄▄▄▄▄▄▄▄▄▄▀
							"""),

	PLUSHIES("""
			───▄▀▀▀▄▄▄▄▄▄▄▀▀▀▄───
			───█▒▒░░░░░░░░░▒▒█───
			────█░░█░░░░░█░░█────
			─▄▄──█░░░▀█▀░░░█──▄▄─
			█░░█─▀▄░░░░░░░▄▀─█░░█
							"""),

	MUSIC("""
			░░█▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ .
			██▀▀▀██▀▀▀▀▀▀██▀▀▀██
			█▒▒▒▒▒█▒▀▀▀▀▒█▒▒▒▒▒█
			█▒▒▒▒▒█▒████▒█▒▒▒▒▒█
			██▄▄▄██▄▄▄▄▄▄██▄▄▄██
										"""),

	INSTRUMENTS("""
			░▄▀▀▀▀▄░░▄▄
			█░░░░░░▀▀░░█░░░░░░▄░▄
			█░║░░░░██░████████████
			█░░░░░░▄▄░░█░░░░░░▀░▀
			░▀▄▄▄▄▀░░▀▀
										"""),

	GAMES("""
			─▄▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▄.
			█░░░█░░░░░░░░░░▄▄░██░█
			█░▀▀█▀▀░▄▀░▄▀░░▀▀░▄▄░█
			█░░░▀░░░▄▄▄▄▄░░██░▀▀░█
			─▀▄▄▄▄▄▀─────▀▄▄▄▄▄▄▀.
										"""),

	LEGO("""
			────────▄█▀▄       .
			──────▄██▀▀▀▀▄     .
			────▄███▀▀▀▀▀▀▀▄   .
			──▄████▀▀▀▀▀▀▀▀▀▀▄ .
			▄█████▀▀▀▀▀▀▀▀▀▀▀▀▀▄
										"""),

	MATERIALS("""
			░░░░░░░░░░░░░░░░▄▓▄  .
			░░░░▄█▄░░░░░░░░▄▓▓▓▄ .
			░░▄█████▄░░░░░▄▓▓▓▓▓▄.
			░▀██┼█┼██▀░░░▄▓▓▓▓▓▓▓▄
			▄▄███████▄▄▄▄▄▄▄▄█▄▄▄▄
										"""),

	WATCHES("""
			╦╦╦╦╦╦▄▀▀▀▀▀▀▄╦╦╦╦╦╦
			▒▓▒▓▒█╗░░▐░░░╔█▒▓▒▓▒
			▒▓▒▓▒█║░░▐▄▄░║█▒▓▒▓▒
			▒▓▒▓▒█╝░░░░░░╚█▒▓▒▓▒
			╩╩╩╩╩╩▀▄▄▄▄▄▄▀╩╩╩╩╩╩
										"""),

	ANIMALS("""
			─────▀▄▀─────▄─────▄
			──▄███████▄──▀██▄██▀
			▄█████▀█████▄──▄█  .
			███████▀████████▀  .
			─▄▄▄▄▄▄███████▀    .
										""");

	private final String icon;

	private Tags(String icon) {
		this.icon = icon;
	}

	/** Get the raw banner */
	@Override
	public String get() {
		// they are stored as String[], so they must be converted
		return String.join("\n", icon);
	}

	@Override
	public String[] split() {
		return icon.split("\n");
	}

	public static Tags getByName(String name) {
		for (Tags tag : Tags.values()) {
			if (tag.name().equalsIgnoreCase(name)) {
				return tag;
			}
		}
		return null;
	}
}
