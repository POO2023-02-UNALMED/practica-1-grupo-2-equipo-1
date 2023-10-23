package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.entites.Delivery;

import java.io.Serializable;
import java.util.*;
import java.text.NumberFormat;

import com.ecart.gestorAplicacion.entites.Person;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;

public class Order implements Serializable {
	private Map<Product, Integer> selectedProducts;
	private int points;
	private static ArrayList<Order> instances = new ArrayList<>();

	public Order() {
		selectedProducts = new HashMap<>();
		instances.add(this);
	}

	public void addProduct(Product product, int quantity) {
		if (selectedProducts.containsKey(product)) {
			// Si el producto ya está en el pedido, aumenta la cantidad
			int currentQuantity = selectedProducts.get(product);
			selectedProducts.put(product, currentQuantity + quantity);
		} else {
			// Si el producto no está en el pedido, agrégalo con la cantidad especificada
			selectedProducts.put(product, quantity);
		}
	}

	public Map<Product, Integer> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(Map<Product, Integer> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public double calculateTotal() {
		double total = 0;

		for (Map.Entry<Product, Integer> entry : selectedProducts.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			total += product.getPrice() * quantity;
		}

		// double factorConversion = 4198.48;

		return total;
	}

	public String generateInvoice(double iva, double descuentos) {
		StringBuilder invoice = new StringBuilder();
		int productColumnWidth = 40; // Ancho de la columna de productos
		int quantityColumnWidth = 10; // Ancho de la columna de cantidad
		int priceColumnWidth = 15; // Ancho de la columna de precio
		int lineLength = productColumnWidth + quantityColumnWidth + priceColumnWidth + 10;

		// Línea superior de la factura
		String horizontalLine = "+" + "-".repeat(lineLength - 2) + "+";

		invoice.append("Factura de Compra:\n");
		invoice.append(horizontalLine).append("\n");
		invoice.append(String.format(
				"| %-" + productColumnWidth + "s | %" + quantityColumnWidth + "s | %" + priceColumnWidth + "s |\n",
				"Producto", "Cantidad", "Precio"));
		invoice.append(horizontalLine).append("\n");

		for (Map.Entry<Product, Integer> entry : selectedProducts.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();

			String productName = product.getName();
			String quantityStr = String.valueOf(quantity);
			String price = "0";

			// commented in the mean time
			// String price = product.getFormattedPrice();

			// Verificar si "price" es nulo y proporcionar un valor predeterminado
			if (price == null) {
				price = "0.00"; // Puedes establecer cualquier valor predeterminado que desees
			}

			invoice.append(String.format(
					"| %-" + productColumnWidth + "s | %" + quantityColumnWidth + "s | %" + priceColumnWidth + "s |\n",
					productName, quantity, price));
			invoice.append(horizontalLine).append("\n");
		}

		// Asegurarse de que los valores estén alineados a la derecha en el resumen
		String subtotalLabel = "Subtotal:";
		String ivaLabel = "IVA (19%):";
		String descuentosLabel = "Descuentos:";
		String totalLabel = "Total:";

		String formattedSubtotal = String.format(
				"| %-" + productColumnWidth + "s | %" + quantityColumnWidth + "s | %" + priceColumnWidth + "s |\n",
				subtotalLabel, "", formatPriceInColombianPesos(getFormattedSubtotal()));
		String formattedIVA = String.format(
				"| %-" + productColumnWidth + "s | %" + quantityColumnWidth + "s | %" + priceColumnWidth + "s |\n",
				ivaLabel, "", formatPriceInColombianPesos(getFormattedIVA(iva)));
		String formattedDescuentos = String.format(
				"| %-" + productColumnWidth + "s | %" + quantityColumnWidth + "s | %" + priceColumnWidth + "s |\n",
				descuentosLabel, "", formatPriceInColombianPesos(getFormattedDescuentos(descuentos)));
		String formattedTotal = String.format(
				"| %-" + productColumnWidth + "s | %" + quantityColumnWidth + "s | %" + priceColumnWidth + "s |\n",
				totalLabel, "", formatPriceInColombianPesos(getFormattedTotal(iva, descuentos)));

		invoice.append(formattedSubtotal);
		invoice.append(horizontalLine).append("\n");
		invoice.append(formattedIVA);
		invoice.append(horizontalLine).append("\n");
		invoice.append(formattedDescuentos);
		invoice.append(horizontalLine).append("\n");
		invoice.append(formattedTotal);
		invoice.append(horizontalLine).append("\n");

		return invoice.toString();
	}

	// Formatear el precio en pesos colombianos
	private String formatPriceInColombianPesos(double price) {
		Locale colombianLocale = new Locale("es", "CO");
		NumberFormat colombianCurrencyFormat = NumberFormat.getCurrencyInstance(colombianLocale);
		return colombianCurrencyFormat.format(price);
	}

	private double getFormattedSubtotal() {
		// Calcular el subtotal (total sin IVA ni descuentos)
		double subtotal = calculateTotal();
		// Pesos colombianos
		// return String.format("$%.2f", subtotal);
		return subtotal;
	}

	private double getFormattedIVA(double iva) {
		// Formatear el IVA en pesos colombianos u otro formato deseado
		// return String.format("$%.2f", iva);
		return iva;
	}

	private double getFormattedDescuentos(double descuentos) {
		// Formatear los descuentos en pesos colombianos u otro formato deseado
		// return String.format("$%.2f", descuentos);
		return descuentos;
	}

	private double getFormattedTotal(double iva, double descuentos) {
		// Calcular el total con IVA y descuentos
		double total = calculateTotal(iva, descuentos);
		// Formatear el total en pesos colombianos u otro formato deseado
		// return String.format("$%.2f", total);
		return total;
	}

	private double calculateTotal(double iva, double descuentos) {
		double Subtotal = calculateTotal();
		return Subtotal + iva - descuentos;

	}

	private String getFormattedTotal() {
		// Aquí puedes aplicar el formato de moneda colombiana u otro formato que desees
		return String.format("$%.2f", calculateTotal());
	}

	public String getPoints(double totalCompra) {

		int puntos = (int) (totalCompra / 700);
		/*
		 * //convertir de manera auxiliar
		 * String puntoString = Integer.toString(puntos);
		 * int longitud = puntoString.length();
		 */
		String message = "¡Felicitaciones! Ha ganado " + puntos + " puntos con esta compra.";
		int messageLength = message.length();

		int cellWidth = messageLength + 4; // Agregar espacio para los bordes y espacios adicionales

		// Línea superior e inferior de la celda

		String border = "+" + "-".repeat(cellWidth) + "+";

		// Ajustar el mensaje al ancho de la celda y centrarlo
		int padding = (cellWidth - messageLength) / 2;
		String formattedMessage = "|" + " ".repeat(padding) + message + " ".repeat(padding) + "|";

		return border + "\n" + formattedMessage + "\n" + border;
	}

	public int getPoints() {
		return points;
	}

	public void payment() {
		Payment paymentShoppingCart = new Payment(calculateTotal());
	}

	public void installmentPayment(int numberOfInstallments) {
		Payment installPaymentShoppingcart = new Payment(calculateTotal(), true, numberOfInstallments);
	}

	public void sendOrder(Delivery delivery) {
		delivery.receiveOrder(this);
	}

	public static ArrayList<Order> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Order> instances) {
		Order.instances = instances;
	}

	public int[] getDireccionOrigen() {
		return Store.getStoreAddress();
	}

	public int[] getDireccionDestino() {
		return new int[] {};
		// return User.getAddress();
	}
}
