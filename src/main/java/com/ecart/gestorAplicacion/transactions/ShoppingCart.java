package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.entites.Delivery;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Tags;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
			// temporary fix
        Product cartProduct = new Product(product.getName(), product.getPrice(), product.getDescription(), quantity, Tags.PHOTOGRAPHY);
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

