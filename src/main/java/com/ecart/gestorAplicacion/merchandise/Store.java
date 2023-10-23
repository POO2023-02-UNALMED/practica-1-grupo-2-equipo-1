package com.ecart.gestorAplicacion.merchandise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.meta.Entity;
import com.ecart.gestorAplicacion.meta.Retval;

public class Store extends Entity implements Serializable {
	private String description;
	private Tags tag;
	private static int[] storeAddress;
	private ArrayList<Product> products;
	private ArrayList<User> members;

	private static ArrayList<Store> instances = new ArrayList<>();

	public Store(String name, String password, String description, Tags tag) {
		super(name, password);
		this.description = description;
		this.tag = tag;

		this.members = new ArrayList<>();
		this.products = new ArrayList<>();
		instances.add(this);
	}

	/**
	 * Conditional constructor
	 *
	 * @param name
	 * @param passcode
	 * @param description
	 * @return
	 */
	public static Store create(String name, String passcode, String description, Tags tag) {
		Store newStore = validate(name);
		if (newStore != null)
			return null;

		newStore = new Store(name, passcode, description, tag);

		// instances.add(newStore);
		return newStore;
	}

	public static Store validate(String name) {
		return validate(name, null, instances, false);
	}

	public static Store validate(String name, String password) {
		return validate(name, password, instances, true);
	}

	public static Store validate(String name, ArrayList<Store> arr) {
		return validate(name, null, arr, false);
	}

	public static Store validate(String name, String password, ArrayList<Store> arr, boolean checkPassword) {
		for (Store store : arr) {
			if (store.getName().equals(name)) {
				if (checkPassword) {
					if (!store.getPassword().equals(password))
						continue;
				}

				return store;
			}
		}
		return null;
	}

	public Retval addUser(User user) {
		return addUser(user.getName());
	}

	public Retval addUser(String name) {
		User newUser = User.validate(name, members);
		if (newUser != null)
			return new Retval("Failed to join store. User is already a member of the store", false);

		this.members.add(newUser);

		return new Retval("Joined store successfully");
	}

	public Retval createProduct(String name, double price, String description, int quantity, Tags tag,
			User productHolder) {
		Product newProduct = Product.create(name, price, description, quantity, tag, productHolder, this.getProducts());
		if (newProduct == null)
			return new Retval("Failed create product. The product already exists inside of the store", false);

		this.products.add(newProduct);

		return new Retval("Created product successfully!");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tags getTag() {
		return tag;
	}

	public void setTag(Tags tag) {
		this.tag = tag;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

	public static ArrayList<Store> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Store> instances) {
		Store.instances = instances;
	}

	public static int[] getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(int[] storeAddress) {
		Store.storeAddress = storeAddress;
	}
}
