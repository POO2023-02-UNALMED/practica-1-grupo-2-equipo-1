package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;

import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.meta.Retval;

public class User extends Person {
	private ArrayList<Store> stores;

	private static ArrayList<User> instances = new ArrayList<>();

	public User(String username, String password) {
		super(username, password);

		this.stores = new ArrayList<>();
		instances.add(this);
	}

	public static User validate(String username) {
		return validate(username, null, instances, false);
	}

	public static User validate(String username, String password) {
		return validate(username, password, instances, true);
	}

	public static User validate(String username, ArrayList<User> arr) {
		return validate(username, null, arr, false);
	}

	public static User validate(String username, String password, ArrayList<User> arr, boolean checkPassword) {
		for (User user : arr) {
			if (user.getName().equals(username)) {
				if (checkPassword) {
					if (!user.getPassword().equals(password))
						continue;
				}

				return user;
			}
		}
		return null;
	}

	public Retval createStore(String name, String password, String description) {
		Store newStore = Store.create(name, password, description);
		if (newStore == null)
			return new Retval("Failed to create store, name already in use", false);

		return this.addStore(newStore);
	}

	public Retval addStore(Store store) {
		return addStore(store.getName(), store.getPassword());
	}

	// join a store, but named it "addStore()" to keep it consistent
	public Retval addStore(String name, String password) {
		Store existingStore = Store.validate(name, password);
		if (existingStore == null)
			return new Retval("Error: the store does not exist", false);

		Retval retval = existingStore.addUser(this.getName());

		return retval;
	}

	// public boolean createStore(String name, String passcode, String description)
	// {
	// Store newStore = Store.createStore(name, passcode, description);
	// if (newStore == null) return false;
	//
	// stores.add(newStore);
	// newStore.addMember(this);
	//
	// return true;
	// }

	// public static ArrayList<String> getUsernames() {
	// ArrayList<String> names = new ArrayList<String>();
	//
	// for (Person e : instances) {
	// names.add(e.getUsername());
	// }
	//
	// return names;
	// }
	//
	// public static User getById(UUID id) {
	// for (User user : instances) {
	// if (user.getId().equals(id)) {
	// return user;
	// }
	// }
	// return null;
	// }
	//
	// public static User getByCredentials(String username, String password) {
	// for (User user : instances) {
	// if (user.getUsername().equals(username) &&
	// user.getPassword().equals(password)) {
	// return user;
	// }
	// }
	// return null;
	// }
	//
	// public static ArrayList<User> getInstances() {
	// return instances;
	// }
	//
	// public static void setInstances(ArrayList<User> instances) {
	// User.instances = instances;
	// }
	//
	// public boolean createStore(String name, String passcode, String description)
	// {
	// Store newStore = Store.createStore(name, passcode, description);
	// if (newStore == null) return false;
	//
	// stores.add(newStore);
	// newStore.addMember(this);
	//
	// return true;
	// }
	//
	// public void joinStore(String name, String passcode) {
	// Store existingStore = Store.getByCredentials(name, passcode);
	// // if (existingStore == null)
	// }
	//
	// public ArrayList<Store> getStores() {
	// return stores;
	// }
	//
	// public void setStores(ArrayList<Store> stores) {
	// this.stores = stores;
	// }
}
