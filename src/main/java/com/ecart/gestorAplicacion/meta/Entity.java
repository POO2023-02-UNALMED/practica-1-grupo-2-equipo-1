package com.ecart.gestorAplicacion.meta;

/**
 * For unique objects that "hold" other object
 * */
public abstract class Entity {
	private String name;
	private String password = "";

	public Entity() {}

	public Entity(String username, String password) {
		this.name = username;
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
