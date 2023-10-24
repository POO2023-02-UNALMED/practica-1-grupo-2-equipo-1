package com.ecart.gestorAplicacion.entites;

import com.ecart.gestorAplicacion.transactions.Order;
import com.ecart.gestorAplicacion.transactions.ShoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Delivery extends Person implements Serializable {
	private static ArrayList<Delivery> instances = new ArrayList<>();
	private ArrayList<Order> orders;
	private ArrayList<ShoppingCart> shoppingCarts;

	public Delivery(String username, String password, int[] address) {
		super(username, password, address);

		orders = new ArrayList<>();
		shoppingCarts = new ArrayList<>();
		instances.add(this);
	}

	public static Delivery validate(String username) {
		return validate(username, null, instances, false);
	}

	public static Delivery validate(String username, String password) {
		return validate(username, password, instances, true);
	}

	public static Delivery validate(String username, ArrayList<Delivery> arr) {
		return validate(username, null, arr, false);
	}

	public static Delivery validate(String username, String password, ArrayList<Delivery> arr, boolean checkPassword) {
		for (Delivery delivery : arr) {
			if (delivery.getName().equals(username)) {
				if (checkPassword) {
					if (!delivery.getPassword().equals(password))
						continue;
				}

				return delivery;
			}
		}
		return null;
	}

	public static Delivery create(String name, String password, int[] address) {
		// Delivery newDelivery = validate(name);
		// if (newDelivery != null)
		// return null;
		//
		// if(!Person.isAddressAvailable(address))
		// return null;
		//
		// newDelivery = new Delivery(name, password, getAddress());
		// return newDelivery;
		return null;
	}

	public void receiveOrder(Order order) {
		orders.add(order);
	}

	public void receiveShoppingCart(ShoppingCart shoppingCart) {
		shoppingCarts.add(shoppingCart);
	}

	public static ArrayList<Delivery> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Delivery> instances) {
		Delivery.instances = instances;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public EntregaInfo tomarOrder(Order order) {
		// int[] coordenadasOrigen = order.getDireccionOrigen();
		// int[] coordenadasDestino = order.getDireccionDestino();

		// double distanciaTotal = calcularDistancia(coordenadasOrigen, coordenadasDestino);
		//
		// double tarifaBase = 5.0; // Tarifa base por defecto
		// double tarifaPorDistancia = 1.0; // Tarifa por kilómetro
		//
		// // Calcular el precio de entrega basado en la distancia
		// double precioEntrega = calcularPrecio(distanciaTotal);
		//
		// double velocidadPromedio = 30.0;
		//
		// // duracion de la entrega
		// double tiempoEnHoras = calcularTiempo(distanciaTotal);
		//
		// // Agregar la orden a la lista de órdenes
		// receiveOrder(order);

		// Retorna la información de la entrega
		return new EntregaInfo(getName(), (double) 0, (double) 0, (double) 0);
	}

	public static class EntregaInfo {
		private String nombreRepartidor;
		private double distanciaTotal;
		private double precioEntrega;
		private double tiempoEntregaEnHoras;

		public EntregaInfo(String nombreRepartidor, double distanciaTotal, double precioEntrega,
				double tiempoEntregaEnHoras) {
			this.nombreRepartidor = nombreRepartidor;
			this.distanciaTotal = distanciaTotal;
			this.precioEntrega = precioEntrega;
			this.tiempoEntregaEnHoras = tiempoEntregaEnHoras;
		}

		public String getNombreRepartidor() {
			return nombreRepartidor;
		}

		public double getDistanciaTotal() {
			return distanciaTotal;
		}

		public double getPrecioEntrega() {
			return precioEntrega;
		}

		public double getTiempoEntregaEnHoras() {
			return tiempoEntregaEnHoras;
		}

		public void setNombreRepartidor(String nombreRepartidor) {
			this.nombreRepartidor = nombreRepartidor;
		}

		public void setDistanciaTotal(double distanciaTotal) {
			this.distanciaTotal = distanciaTotal;
		}

		public void setPrecioEntrega(double precioEntrega) {
			this.precioEntrega = precioEntrega;
		}

		public void setTiempoEntregaEnHoras(double tiempoEntregaEnHoras) {
			this.tiempoEntregaEnHoras = tiempoEntregaEnHoras;
		}
	}

	private double calcularDistancia(int[] coordenadasA, int[] coordenadasB) {
		if (coordenadasA.length != 2 || coordenadasB.length != 2) {
			throw new IllegalArgumentException("Las coordenadas deben tener exactamente 2 elementos numéricos.");
		}

		double latitudA = Math.toRadians(coordenadasA[0]);
		double longitudA = Math.toRadians(coordenadasA[1]);
		double latitudB = Math.toRadians(coordenadasB[0]);
		double longitudB = Math.toRadians(coordenadasB[1]);

		double radioTierra = 6371; // radio en kilometros

		double diferenciaLatitud = latitudB - latitudA;
		double diferenciaLongitud = longitudB - longitudA;

		double a = Math.sin(diferenciaLatitud / 2) * Math.sin(diferenciaLatitud / 2) +
				Math.cos(latitudA) * Math.cos(latitudB) *
						Math.sin(diferenciaLongitud / 2) * Math.sin(diferenciaLongitud / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double distancia = radioTierra * c;
		return distancia;
	}

	private double calcularTiempo(double distancia) {
		double velocidadPromedio = 30.0;
		return distancia / velocidadPromedio;
	}

	private double calcularPrecio(double distancia) {
		double tarifaBase = 5.0;
		double tarifaPorKilometro = 1.0;
		return tarifaBase + (distancia * tarifaPorKilometro);
	}

}
