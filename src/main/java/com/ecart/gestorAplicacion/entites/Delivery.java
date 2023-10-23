package com.ecart.gestorAplicacion.entites;

import com.ecart.gestorAplicacion.transactions.Order;
import com.ecart.gestorAplicacion.transactions.ShoppingCart;

import java.io.Serializable;
import java.util.ArrayList;

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
        //     return null;
        //
        // if(!Person.isAddressAvailable(address))
        //     return null;
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

}
