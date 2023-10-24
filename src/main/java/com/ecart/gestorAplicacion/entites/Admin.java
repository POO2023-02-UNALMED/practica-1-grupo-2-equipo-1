package com.ecart.gestorAplicacion.entites;

import com.ecart.gestorAplicacion.meta.Retval;

import java.io.Serializable;

import java.util.ArrayList;

public class Admin extends Person {
	private static ArrayList<Admin> instances = new ArrayList<>();
	private ArrayList<Delivery> deliveries = new ArrayList<>();

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

		if (!Person.isAddressAvailable(address))
			return null;

		newAdmin = new Admin(name, password, address);
		return newAdmin;
	}

	public Retval createDelivery(String name, String password, int[] address) {
		Delivery newDelivery = Delivery.create(name, password, address);

		if (newDelivery == null)
			return new Retval("Failed to create delivery, name already in use", false);

		return this.addDelivery(newDelivery);
	}

	public Retval addDelivery(Delivery delivery) {
		return addDelivery(delivery.getName());
	}

	public Retval addDelivery(String name) {
		Delivery existingDelivery = Delivery.validate(name);
		if (existingDelivery == null)
			return new Retval("Error: the delivery does not exist", false);

		deliveries.add(existingDelivery);

		return new Retval("Added delivery succesfully");
	}

	public static ArrayList<Admin> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Admin> instances) {
		Admin.instances = instances;
	}
}
