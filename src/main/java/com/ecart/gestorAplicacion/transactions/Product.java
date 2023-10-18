package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private double discount;

    // Lista estática de productos disponibles
    private static List<Product> availableProducts = new ArrayList<>();

    public Product(String name, double price, int quantity, double discount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        // Agregar el producto a la lista de productos disponibles al crear una instancia.
        availableProducts.add(this);
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // Obtener la lista de productos disponibles
    public static List<Product> getAvailableProducts() {
        return availableProducts;
    }
}


