package com.ecart.gestorAplicacion.entites;

<<<<<<< HEAD
import com.ecart.gestorAplicacion.meta.Retval;

=======
import java.io.Serializable;
>>>>>>> 5f7aae087139979dd1b218b3a0a0c7a581cbb7b1
import java.util.ArrayList;

public class Admin extends Person implements Serializable {
	private static ArrayList<Admin> instances = new ArrayList<>();
	private  ArrayList<Delivery> delivery = new ArrayList<>();

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

<<<<<<< HEAD
	public Retval createDelivery(String name, String password){
		Delivery newDelivery = Delivery.create(name, password, getAddress());
		if (newDelivery == null)
			return new Retval("Failed to create store, name already in use", false);
		return this.addDelivery(newDelivery);
	}

	public Retval addDelivery(Delivery delivery){
		return addDelivery(delivery.getName(), delivery.getPassword());

	}



	public Retval addDelivery(String name, String password) {
		Delivery existingDelivery = Delivery.validate(name, password);
		if (existingDelivery == null){
			return new Retval("Error: the store does not exist", false);
		}
/*
		Retval retval = Delivery.create(name, password, getAddress());
		if (retval.ok()) this.delivery.add(existingDelivery);

		return retval;
	}
*/
		return null;
=======
	public static ArrayList<Admin> getInstances() {
		return instances;
>>>>>>> 5f7aae087139979dd1b218b3a0a0c7a581cbb7b1
	}
}

