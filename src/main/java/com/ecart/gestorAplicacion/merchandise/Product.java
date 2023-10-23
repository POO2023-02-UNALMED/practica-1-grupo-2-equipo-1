package com.ecart.gestorAplicacion.merchandise;

import com.ecart.gestorAplicacion.entites.User;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Product implements Serializable {
	private String name;
	private double price;
	private String description;
	private int quantity;
	private Tags tag;
	private boolean listed;
	private User productHolder;

	// private List<User> subscribers = new ArrayList<>();
	// private List<Coupon> coupons = new ArrayList<>();
	// private List<Review> reviews = new ArrayList<>();

	private static ArrayList<Product> instances = new ArrayList<>();

	public Product(String name, double price, String description, int quantity, Tags tag, User productHolder) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.tag = tag;
		this.listed = true;
		this.productHolder = productHolder;

		instances.add(this);
	}

	public static Product create(String name, double price, String description, int quantity, Tags tag, User productHolder) {
		return create(name, price, description, quantity, tag, productHolder, instances);
	}

	/**
	 * Conditional constructor
	 *
	 * @param name
	 * @param price
	 * @param description
	 * @param quantity
	 * @return
	 */
	public static Product create(String name, double price, String description, int quantity, Tags tag, User productHolder,
			ArrayList<Product> arr) {
		Product newProduct = validate(name, arr);
		if (newProduct != null)
			return null;

		newProduct = new Product(name, price, description, quantity, tag, productHolder);

		instances.add(newProduct);
		return newProduct;
	}

	public static Product validate(Product product) {
		return validate(product.getName(), instances);
	}

	public static Product validate(String productName) {
		return validate(productName, instances);
	}

	public static Product validate(String name, ArrayList<Product> arr) {
		for (Product product : arr) {
			if (product.getName().equals(name)) {
				return product;
			}
		}

		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Tags getTag() {
		return tag;
	}

	public void setTag(Tags tag) {
		this.tag = tag;
	}

	public boolean isListed() {
		return listed;
	}

	public void setListed(boolean listed) {
		this.listed = listed;
	}

	public static ArrayList<Product> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Product> instances) {
		Product.instances = instances;
	}

}
