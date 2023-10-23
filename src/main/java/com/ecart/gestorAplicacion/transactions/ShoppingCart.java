package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.meta.Retval;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShoppingCart implements Serializable {
	private LinkedHashMap<Integer, Product> cartItems;
	private static ArrayList<ShoppingCart> instances = new ArrayList<>();

	public ShoppingCart() {
		cartItems = new LinkedHashMap<>();
		instances.add(this);
	}

	public Retval addProduct(Product product, int quantity) {
		Retval retval = new Retval("Added product to cart succesfully!");

		if (quantity > product.getQuantity() || quantity <= 0)
			return new Retval("There is not enough stock to add the product. Come back later!", false);

		cartItems.put(Integer.valueOf(quantity), product);

		return retval;
	}

	public static ArrayList<ShoppingCart> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<ShoppingCart> instances) {
		ShoppingCart.instances = instances;
	}

	public double calculateTotal() {
		double total = 0;

		// for (Product product : cartItems) {
		// 	total += product.getPrice() * product.getQuantity();
		// }
		//
		return total;
	}

	public LinkedHashMap<Integer, Product> getCartItems() {
		return cartItems;
	}

	public void setCartItems(LinkedHashMap<Integer, Product> cartItems) {
		this.cartItems = cartItems;
	}
}
