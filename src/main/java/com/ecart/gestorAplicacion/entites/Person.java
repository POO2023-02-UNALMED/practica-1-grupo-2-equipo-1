package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;

public abstract class Person {
	private String username;
	private String password = "";
	private static ArrayList<Person> instances = new ArrayList<>();

	public Person(String username, String password) {
		this.username = username;
		this.password = password;

		instances.add(this);
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

	public static ArrayList<Person> getInstances() {
		return instances;
	}

	public static ArrayList<String> getUserNames() {
		ArrayList<String> names = new ArrayList<String>();

		for (Person e : Person.getInstances()) {
			names.add(e.getUsername());
		}

		return names;
	}
}
