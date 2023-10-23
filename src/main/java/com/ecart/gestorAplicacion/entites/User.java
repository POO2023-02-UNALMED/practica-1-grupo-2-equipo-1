package com.ecart.gestorAplicacion.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.gestorAplicacion.transactions.ShoppingCart;

public class User extends Person {
	private ArrayList<Store> stores;
	private ShoppingCart shoppingCart;

	private static ArrayList<User> instances = new ArrayList<>();

	public User(String username, String password, int[] address) {
		super(username, password, address);

		this.shoppingCart = new ShoppingCart();
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

	public static User create(String name, String password, int[] address) {
		User newUser = validate(name);
		if (newUser != null)
			return null;

		if (!Person.isAddressAvailable(address))
			return null;

		newUser = new User(name, password, address);
		return newUser;
	}

	public Retval createProduct(Store store, String name, double price, String description, int quantity, Tags tag) {
		return store.createProduct(name, price, description, quantity, tag, this);
	}

	// guia

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
		if (retval.ok())
			this.stores.add(existingStore);

		return retval;
	}

	public Retval addToShoppingCart(String productName, int quantity) {
		Product existingProduct = Product.validate(productName);

		Retval retval = shoppingCart.addProduct(existingProduct, quantity);

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

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public LinkedHashMap<String, Product> suggestProducts(Tags[] tags, int maxPrice){
		ArrayList<Store> allStores = Store.getInstances();
		ArrayList<Store> userStores = this.getStores();
		ArrayList<Store> availableStores = new ArrayList<>();
		LinkedHashMap<String, Product> recommendedProducts = new LinkedHashMap<>();

		for (Store store : allStores) {
			if (!userStores.contains(store))
				availableStores.add(store);
		}


		for (Store store : availableStores) {
			for (Product product : store.getProducts()) {
				boolean tagsMatch = true;
				for (Tags tag : tags) {
					boolean found = false;
					for (Tags productTag : product.getTags()) {
						if (tag.equals(productTag)) {
							found = true;
							break;
						}
					}
					if (!found) {
						tagsMatch = false;
						break;
					}
				}
				if (tagsMatch) {
					recommendedProducts.put(product.getName(), product);
				}
			}
		}

		return recommendedProducts;
	}

}
/*
	public LinkedHashMap<String, Product> suggestProducts(Tags[] tags, int maxPrice) {
		return stores.stream()
				.filter(product -> product.getProducts() <= maxPrice &&
						containsAllTags(product, tags) //&&
						//!product.getStore().equals(ownStore))
				.collect(Collectors.toMap(Product::getName, product -> product, (existing, replacement) -> existing, LinkedHashMap::new)));
	}


	private boolean containsAllTags(Product product, Tags[] tags) {
		for (Tags tag : tags) {
			boolean found = false;
			for (Tags productTag : product.getTags()) {
				if (tag.equals(productTag)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}
}
 */

