package com.ecart.gestorAplicacion.entites;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends Person implements Serializable {
	private static ArrayList<Admin> instances = new ArrayList<>();
	private  ArrayList<Delivery> deliveries = new ArrayList<>();

	public Admin(String username, String password, int[] address) {
		super(username, password, address);

		instances.add(this);
	}

	public static Admin validate(String username) {
		return validate(username, null, instances, false);
	}

	public static Admin validate(String username, String password) {
		return validate(username, password, instances, true);
	}

	public static Admin validate(String username, ArrayList<Admin> arr) {

		return validate(username, null, arr, false);
	}

	public static Admin validate(String username, String password, ArrayList<Admin> arr, boolean checkPassword) {
		for (Admin admin : arr) {
			if (admin.getName().equals(username)) {
				if (checkPassword) {
					if (!admin.getPassword().equals(password))
						continue;
				}

				return admin;
			}
		}
		return null;
	}

	public static Admin create(String name, String password, int[] address) {
		Admin newAdmin = validate(name);
		if (newAdmin != null)
			return null;

		if(!Person.isAddressAvailable(address))
			return null;

		newAdmin = new Admin(name, password, address);
		return newAdmin;
	}

	public static ArrayList<Admin> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Admin> instances) {
		Admin.instances = instances;
	}
}

