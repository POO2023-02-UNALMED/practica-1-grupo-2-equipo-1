package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;

import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
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

	public Retval createProduct(Store store, String name, double price, String description, int quantity, Tags tag) {
		return store.createProduct(name, price, description, quantity, tag);
	}

	public Retval createStore(String name, String password, String description, Tags tag) {

		Store newStore = Store.create(name, password, description, tag);
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
		if (retval.ok()) this.stores.add(existingStore);

		return retval;
	}

	public Retval listProduct(String productName, Store store, boolean doList) {
		Product existingProduct = Product.validate(productName, store.getProducts());
		if (existingProduct == null)
			return new Retval("Error: there is no such product inside the store", false);

		existingProduct.setListed(doList);

		return new Retval("Unlisted product succesfully");
	}

	public ArrayList<Store> getStores() {
		return stores;
	}

	public void setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}

	public void deleteStore(String name) {
	  for (Store store : stores) {
			if (store.getName().equals(name)) {
				 stores.remove(store);
				 break;
			}
	  }
	}

	public static ArrayList<User> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<User> instances) {
		User.instances = instances;
	}
}
