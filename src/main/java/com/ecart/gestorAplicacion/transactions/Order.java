package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.transactions.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    private List<Product> selectedProducts;
    private int points;

    public Order() {
        selectedProducts = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        // Agregar el producto con la cantidad al pedido
        Product orderProduct = new Product(product.getName(), product.getPrice(), product.getDescription(), quantity);
        selectedProducts.add(orderProduct);
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public double calculateTotal() {
        double total = 0;

        for (Product product : selectedProducts) {
            total += product.getPrice() * product.getQuantity();
        }

        return total;
    }

    public void getPoints(double totalCompra) {
        double factorConversion = 4198.48;
        int puntos = (int) (totalCompra * factorConversion / 700);

        System.out.println("¡Felicitaciones! Ha ganado " + puntos + " puntos con esta compra.");
    }

    public int getPointsEarned() {
        return points;
    }
}
