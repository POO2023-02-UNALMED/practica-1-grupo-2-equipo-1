package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;
import java.util.UUID;

import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.transactions.Product;

public class User extends Person {
	private static ArrayList<User> instances = new ArrayList<>();
	private ArrayList<Store> stores = new ArrayList<>();
	private ArrayList<Product> suscribedProducts = new ArrayList<>();

	public User(String username, String password) {
		super(username, password);

		instances.add(this);
	}

	public static ArrayList<String> getUsernames() {
		ArrayList<String> names = new ArrayList<String>();

		for (Person e : instances) {
			names.add(e.getUsername());
		}

		return names;
	}

	public static User getById(UUID id) {
		for (User user : instances) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public static User getByCredentials(String username, String password) {
		for (User user : instances) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public static ArrayList<User> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<User> instances) {
		User.instances = instances;
	}

	public ArrayList<Product> getSuscribedProducts() {
		return suscribedProducts;
	}

	public boolean createStore(String name, String passcode, String description) {
		Store store = new Store(name, passcode, description);
		stores.add(store);
		store.addMember(this);

		return true;
	}

	public void joinStore() {

	}

	public ArrayList<Store> getStores() {
		return stores;
	}

	public void setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}

	public void suscribeToProduct(Product product) {
		suscribedProducts.add(product);
	}

}
