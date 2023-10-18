package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;

class ShoppingCart {
    private ArrayList<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        products.add(product);
    }

    public double calculateTotal() {
        double total = 0;

        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }

        return total;
    }
}

