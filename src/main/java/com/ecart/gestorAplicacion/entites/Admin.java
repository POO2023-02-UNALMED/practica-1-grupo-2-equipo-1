package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;

public class Admin extends Person {
	private static ArrayList<Admin> instances = new ArrayList<>();

	public Admin(String username, String password) {
		super(username, password);

		instances.add(this);
	}
}

