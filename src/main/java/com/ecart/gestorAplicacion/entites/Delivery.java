package com.ecart.gestorAplicacion.entites;

import com.ecart.gestorAplicacion.transactions.Order;
import com.ecart.gestorAplicacion.transactions.ShoppingCart;

import java.util.ArrayList;

public class Delivery extends Person {
    private static ArrayList<Delivery> instances = new ArrayList<Delivery>();
    private ArrayList<Order> orders;
    private ArrayList<ShoppingCart> shoppingCarts;

    public Delivery(String username, String password) {
        super(username, password);
        instances.add(this);
        orders = new ArrayList<>();
        shoppingCarts = new ArrayList<>();
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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

}