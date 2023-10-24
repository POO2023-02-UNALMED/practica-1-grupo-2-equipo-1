package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Entity;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.gestorAplicacion.transactions.BankAccount;
import com.ecart.gestorAplicacion.transactions.Order;
import com.ecart.gestorAplicacion.transactions.ShoppingCart;

public class User extends Person {
	private ArrayList<Store> stores;
	private ArrayList<Order> orders;
	private ShoppingCart shoppingCart;

	private static ArrayList<User> instances = new ArrayList<>();

	public User(String username, String password, int[] address) {
		super(username, password, address);

		this.shoppingCart = new ShoppingCart();
		this.stores = new ArrayList<>();
		this.orders = new ArrayList<>();

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

	public Retval deliverOrder(Order order, User finalUser) {
		order.setDeliveryUser(this);
		double deliveryPrice = getDeliveryPrice(order, finalUser);
		order.setDelivered(true);
		order.setTotalPrice(deliveryPrice + order.getTotalPrice());
		order.setDeliveryPrice(deliveryPrice);

		Retval retval = new Retval("Delivered order! You will get payed '" + String.valueOf(deliveryPrice) + "' once the user pays");

		return retval;
	}

	private double getDeliveryPrice(Order order, User finalUser) {
		LinkedHashMap<Product, Integer> selectedProducts = order.getSelectedProducts();
		ArrayList<int[]> route = new ArrayList<>();
		double totalPrice = 0;

		int[] currentAddress = this.getAddress();
		route.add(currentAddress);

		for (Product product : selectedProducts.keySet()) {
			int[] productAddress = product.getProductHolder().getAddress();
			int distance = calculateDistance(currentAddress, productAddress);

			// standard rate of delivery
			double price = (double) distance / 5 * 2;
			totalPrice += price;

			route.add(productAddress);

			currentAddress = productAddress;
		}

		// calculate the distance and price from the last product to finalUser
		int[] finalUserAddress = finalUser.getAddress();
		int lastProductIndex = route.size() - 1;
		int lastProductDistance = calculateDistance(route.get(lastProductIndex), finalUserAddress);
		double lastProductPrice = (double) lastProductDistance / 5 * 2;
		totalPrice += lastProductPrice;

		route.add(finalUserAddress);

		return totalPrice;
	}

	private int calculateDistance(int[] address1, int[] address2) {
		int deltaX = Math.abs(address1[0] - address2[0]);
		int deltaY = Math.abs(address1[1] - address2[1]);
		return deltaX + deltaY;
	}

	public Retval abonarOrder(Order orderToPay, double abono) {
		double toPay = abono;
		if (abono > (orderToPay.getTotalPrice() - orderToPay.getPayedSoFar())) {
			toPay = orderToPay.getTotalPrice() - orderToPay.getPayedSoFar();
		}

		if (this.getBankAccount().getBalance() < abono)
			return new Retval("You do not have enough money in your bank account. Deposit some!", false);

		orderToPay.abonar(toPay);
		this.getBankAccount().withdraw(toPay);

		if (orderToPay.getPayedSoFar() >= orderToPay.getTotalPrice()) {
			orderToPay.setPayedFullPrice(true);

			this.makePayment(orderToPay.getDeliveryUser(), orderToPay.getDeliveryPrice());

			for (Map.Entry<Product, Integer> entry : orderToPay.getSelectedProducts().entrySet()) {
				this.makePayment(entry.getKey().getProductHolder(), entry.getValue() * entry.getKey().getPrice());
			}
		}

		return new Retval("Abono money succesfully!");
	}

	public Retval makePayment(User toUser, double moneyToPay) {
		toUser.getBankAccount().deposit(moneyToPay);
		return new Retval("Deposited money to " + toUser.getName());
	}

	public Retval placeOrder() {
		StringBuilder troublesomeProducts = new StringBuilder();
		Retval retval = new Retval("Placed your order succesfully! Make sure to pay it after it being delivered");

		LinkedHashMap<Product, Integer> userOrder = this.getShoppingCart().getCartItems();

		// validate the actualOrder
		for (Map.Entry<Product, Integer> entry : userOrder.entrySet()) {
			Product product = entry.getKey();
			int orderedQuantity = entry.getValue();
			if (!ShoppingCart.isProductAvailable(product, orderedQuantity)) {
				troublesomeProducts.append(
						product.getName() + "(available: " + product.getQuantity() + ", ordered: " + orderedQuantity + ")");
			}
		}

		if (!troublesomeProducts.toString().equals("")) {
			retval = new Retval("Error, you are ordering unstocked items: " + troublesomeProducts.toString());
		} else {
			double totalPrice = 0;
			for (Map.Entry<Product, Integer> entry : userOrder.entrySet()) {
				totalPrice += entry.getKey().getPrice() * entry.getValue();
			}

			// substract the quantities from the products
			for (Map.Entry<Product, Integer> entry : userOrder.entrySet()) {
				int oldQuantity = entry.getKey().getQuantity();
				entry.getKey().setQuantity(oldQuantity - entry.getValue());
			}

			Order newOrder = new Order(userOrder, this, totalPrice);
			this.orders.add(newOrder);
			this.getShoppingCart().clearItems();
		}

		return retval;
	}

	public ArrayList<Product> suggestProducts(Tags[] tags, Double maxPrice) {
		ArrayList<Product> recommendedProducts = new ArrayList<>();

		ArrayList<Store> allStores = Store.getInstances();
		ArrayList<Store> userStores = this.getStores();
		ArrayList<Store> availableStores = new ArrayList<>();

		List<Tags> tagsList = Arrays.asList(tags);

		for (Store store : allStores) {
			if (!userStores.contains(store))
				availableStores.add(store);
		}

		for (Store store : availableStores) {
			for (Product product : store.getProducts()) {
				if (tagsList.contains(product.getTag()) == true && product.getPrice() <= maxPrice)
					recommendedProducts.add(product);
			}
		}

		return recommendedProducts;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
}
