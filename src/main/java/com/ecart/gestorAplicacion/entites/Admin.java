package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;

public class Admin extends Person {
	private static ArrayList<Admin> instances = new ArrayList<>();

	public Admin(String username, String password, int[] address) {
		super(username, password, address);

		instances.add(this);
	}
}

