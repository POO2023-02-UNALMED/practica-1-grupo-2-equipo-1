package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.meta.Retval;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShoppingCart implements Serializable {
	private LinkedHashMap<Product, Integer> cartItems; // Integer is the amount of items to be bought

	private static final long serialVersionUID = 1L;
	private static ArrayList<ShoppingCart> instances = new ArrayList<>();

	public ShoppingCart() {
		cartItems = new LinkedHashMap<>();
		instances.add(this);
	}

	public static boolean isProductAvailable(Product product, int quantity) {
		if (quantity <= product.getQuantity() && quantity >= 0)
			return true;

		return false;
	}

	public Retval addProduct(Product product, int quantity) {
		Retval retval = new Retval("Added product to cart succesfully!");

		if (cartItems.containsKey(product))
			return new Retval("You cannot place the same product twice in your shopping cart", false);

		if (!isProductAvailable(product, quantity))
			return new Retval("There is not enough stock to add the product. Come back later!", false);

		cartItems.put(product, Integer.valueOf(quantity));

		return retval;
	}

	public static ArrayList<ShoppingCart> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<ShoppingCart> instances) {
		ShoppingCart.instances = instances;
	}

	public Retval updateItem(Product productToRemove, int newQuantity) {
		cartItems.remove(productToRemove);
		cartItems.put(productToRemove, newQuantity);

		return new Retval("Updated item succesfully!");
	}

	public Retval removeItem(Product productToRemove) {
		cartItems.remove(productToRemove);

		return new Retval("Removed item succesfully!");
	}

	public  void clearItems() {
		cartItems.clear();
	}

	public LinkedHashMap<Product, Integer> getCartItems() {
		return cartItems;
	}

	public ArrayList<Product> getCartProducts() {
		return new ArrayList<>(cartItems.keySet());
	}

	public void setCartItems(LinkedHashMap<Product, Integer> cartItems) {
		this.cartItems = cartItems;
	}
}
