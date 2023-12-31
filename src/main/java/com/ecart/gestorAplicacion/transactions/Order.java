package com.ecart.gestorAplicacion.transactions;

import java.io.Serializable;
import java.util.*;

import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Product;

public class Order implements Serializable {
	private LinkedHashMap<Product, Integer> selectedProducts;
	private double totalPrice;
	private double payedSoFar;
	private double deliveryPrice;

	private int id;
	private User destinationUser;
	private User deliveryUser;
	private boolean delivered;
	private boolean payedFullPrice;

	private static final long serialVersionUID = 1L;
	private static ArrayList<Order> instances = new ArrayList<>();

	public Order(LinkedHashMap<Product, Integer> selectedProducts, User destinationUser, double totalPrice) {
		this.selectedProducts = new LinkedHashMap<>();
		this.selectedProducts.putAll(selectedProducts);
		this.delivered = false;
		this.totalPrice = totalPrice;
		this.payedSoFar = 0;
		this.deliveryPrice = 0;
		this.destinationUser = destinationUser;
		this.id = instances.size();

		instances.add(this);
	}

	public static Order validate(int id, boolean excludeDelivered) {
		return validate(id, excludeDelivered, instances);
	}

	public static Order validate(int id, boolean excludeDelivered, ArrayList<Order> arr) {
		for (Order order : arr) {
			if (excludeDelivered && order.getDeliveryUser() != null) continue;
			if (order.getId() == id) return order;
		}

		return null;
	}

	public ArrayList<Product> getOrderProducts() {
		return new ArrayList<>(selectedProducts.keySet());
	}

	public LinkedHashMap<Product, Integer> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(LinkedHashMap<Product, Integer> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public static ArrayList<Order> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Order> instances) {
		Order.instances = instances;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void abonar(double abono) {
		this.payedSoFar += abono;
	}

	public double getPayedSoFar() {
		return payedSoFar;
	}

	public void setPayedSoFar(double payedSoFar) {
		this.payedSoFar = payedSoFar;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public User getDeliveryUser() {
		return deliveryUser;
	}

	public void setDeliveryUser(User deliveryUser) {
		this.deliveryUser = deliveryUser;
	}

	public User getDestinationUser() {
		return destinationUser;
	}

	public void setDestinationUser(User destinationUser) {
		this.destinationUser = destinationUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPayedFullPrice() {
		return payedFullPrice;
	}

	public void setPayedFullPrice(boolean payedFullPrice) {
		this.payedFullPrice = payedFullPrice;
	}

	public double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
}
