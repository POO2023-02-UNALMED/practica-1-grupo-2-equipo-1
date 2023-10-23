package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.entites.Delivery;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Tags;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
    private List<Product> cartItems;
    private static ArrayList<ShoppingCart> instances = new ArrayList<>();

    public ShoppingCart() {
        cartItems = new ArrayList<>();
        instances.add(this);
    }

    public void addProduct(Product product, int quantity) {
			// temporary fix
        // Product cartProduct = new Product(product.getName(), product.getPrice(), product.getDescription(), quantity, Tags.PHOTOGRAPHY);
        // cartItems.add(cartProduct);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public static ArrayList<ShoppingCart> getInstances() {
        return instances;
    }

    public double calculateTotal() {
        double total = 0;

        for (Product product : cartItems) {
            total += product.getPrice() * product.getQuantity();
        }

        return total;
    }
}

