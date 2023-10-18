package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Product {
    private String name;
    private double price;
    private String description;
    private int quantity;

    private static List<Product> availableProducts = new ArrayList<>();

    public Product(String name, double price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
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

    public static List<Product> getAvailableProducts() {
        return availableProducts;
    }


    public static void createRandomProducts() {
        String[] productNames = {"Product A", "Product B", "Product C", "Product D", "Product E", "Product F", "Product G", "Product H", "Product I", "Product J", "Product K", "Product L", "Product M", "Product N", "Product O"};
        double minPrice = 10.0;
        double maxPrice = 100.0;
        String[] descriptions = {"Description 1", "Description 2", "Description 3", "Description 4", "Description 5", "Description 6", "Description 7", "Description 8", "Description 9", "Description 10", "Description 11", "Description 12", "Description 13", "Description 14", "Description 15"};

        for (int i = 0; i < 15; i++) {
            String name = productNames[i];
            double price = minPrice + (maxPrice - minPrice) * new Random().nextDouble();
            price = Math.round(price * 100.0) / 100.0; // Redondear a dos decimales
            String description = descriptions[i];
            int quantity = new Random().nextInt(50); // Cantidad aleatoria

            new Product(name, price, description, quantity);
        }
    }
}
