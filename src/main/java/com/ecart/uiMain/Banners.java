package com.ecart.uiMain;

public enum Banners {
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
	public String get() {
		// they are stored as String[], so they must be converted
		return String.join("\n", banner);
	}

	public String[] split() {
		return banner.split("\n");
	}

	public static Banners getBannerByName(String name) {
		for (Banners banner : Banners.values()) {
			if (banner.name().equalsIgnoreCase(name)) {
				return banner;
			}
		}
		return null;
	}
}
