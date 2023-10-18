package com.ecart.gestorAplicacion.entites;

import java.util.UUID;

import com.ecart.gestorAplicacion.ID;

public abstract class Person implements ID {
	private String username;
	private String password = "";
	private UUID id;

	public Person(String username, String password) {
		this.username = username;
		this.password = password;

		this.id = UUID.randomUUID();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public UUID getId() {
		return id;
	}
}
