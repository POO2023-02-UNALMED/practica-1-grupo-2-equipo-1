package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;
import java.util.UUID;

public class Admin extends Person {
	private static ArrayList<Admin> instances = new ArrayList<>();

	public Admin(String username, String password) {
		super(username, password);

		instances.add(this);
	}

	public static Admin getById(UUID id) {
		for (Admin user : instances) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public static Admin getByCredentials(String username, String password) {
		for (Admin user : instances) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
}

