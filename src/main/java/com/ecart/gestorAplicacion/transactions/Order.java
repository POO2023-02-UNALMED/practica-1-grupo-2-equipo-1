package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;
import java.util.Scanner;

class Order {
    private ArrayList<Product> products;

    public Order() {
        products = new ArrayList<>();
    }

    public void addProduct(String name, double price, int quantity) {
        //Product product = new Product(name, price, quantity);
        //products.add(product);
    }

    public double calculateTotal() {
        double total = 0;

        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }

        return total;
    }

    public void getPoints() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Cuántos puntos desea acumular?");
        int puntos = Integer.parseInt(scanner.nextLine());

        // Lógica para acumular puntos
    }
}

