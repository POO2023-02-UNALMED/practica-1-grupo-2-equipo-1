package com.ecart.uiMain;

import com.ecart.gestorAplicacion.meta.Pictogram;

public enum Banners implements Pictogram<Banners> {
	MAIN("""
			   ▄████████  ▄████████    ▄████████    ▄████████     ███
			  ███    ███ ███    ███   ███    ███   ███    ███ ▀█████████▄
			  ███    █▀  ███    █▀    ███    ███   ███    ███    ▀███▀▀██
			 ▄███▄▄▄     ███          ███    ███  ▄███▄▄▄▄██▀     ███   ▀
			▀▀███▀▀▀     ███        ▀███████████ ▀▀███▀▀▀▀▀       ███
			  ███    █▄  ███    █▄    ███    ███ ▀███████████     ███
			  ███    ███ ███    ███   ███    ███   ███    ███     ███
			  ██████████ ████████▀    ███    █▀    ███    ███    ▄████▀
				"""),

	LOGIN("""
			██     ██ ███████ ██       ██████  ██████  ███    ███ ███████ ██
			██     ██ ██      ██      ██      ██    ██ ████  ████ ██      ██
			██  █  ██ █████   ██      ██      ██    ██ ██ ████ ██ █████   ██
			██ ███ ██ ██      ██      ██      ██    ██ ██  ██  ██ ██
			 ███ ███  ███████ ███████  ██████  ██████  ██      ██ ███████ ██
				"""),

	REGISTER("""
			██████  ███████  ██████  ██ ███████ ████████ ███████ ██████
			██   ██ ██      ██       ██ ██         ██    ██      ██   ██
			██████  █████   ██   ███ ██ ███████    ██    █████   ██████
			██   ██ ██      ██    ██ ██      ██    ██    ██      ██   ██
			██   ██ ███████  ██████  ██ ███████    ██    ███████ ██   ██
				"""),

	STORES("""
			███████ ████████  ██████  ██████  ███████ ███████
			██         ██    ██    ██ ██   ██ ██      ██
			███████    ██    ██    ██ ██████  █████   ███████
			     ██    ██    ██    ██ ██   ██ ██           ██
			███████    ██     ██████  ██   ██ ███████ ███████
							""");

	private final String banner;

	private Banners(String banner) {
		this.banner = banner;
	}

	/** Get the raw banner */
	@Override
	public String get() {
		// they are stored as String[], so they must be converted
		return String.join("\n", banner);
	}

	@Override
	public String[] split() {
		return banner.split("\n");
	}

	public static Banners getByName(String name) {
		for (Banners banner : Banners.values()) {
			if (banner.name().equalsIgnoreCase(name)) {
				return banner;
			}
		}
		return null;
	}


}
