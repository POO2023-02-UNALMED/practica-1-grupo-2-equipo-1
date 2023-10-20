package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.entites.Delivery;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        Product cartProduct = new Product(product.getName(), product.getPrice(), product.getDescription(), quantity);
        cartItems.add(cartProduct);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {
        double total = 0;

        for (Product product : cartItems) {
            total += product.getPrice() * product.getQuantity();
        }

        return total;
    }
}

