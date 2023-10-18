package com.ecart.gestorAplicacion.merchandise;

public enum Tags {
	PHOTOGRAPHY("""
			  ▄▄░▄███▄
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
			░░█▀▀▀▀▀▀▀▀▀▀▀▀▀▀█
			██▀▀▀██▀▀▀▀▀▀██▀▀▀██
			█▒▒▒▒▒█▒▀▀▀▀▒█▒▒▒▒▒█
			█▒▒▒▒▒█▒████▒█▒▒▒▒▒█
			██▄▄▄██▄▄▄▄▄▄██▄▄▄██
							""");

	private final String icon;

	private Tags(String icon) {
		this.icon = icon;
	}

	/** Get the raw banner */
	public String get() {
		// they are stored as String[], so they must be converted
		return String.join("\n", icon);
	}

	public String[] split() {
		return icon.split("\n");
	}
}
