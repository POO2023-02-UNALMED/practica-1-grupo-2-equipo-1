package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;
import java.util.List;

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

        //double factorConversion = 4198.48;

        return total;
    }

    public String getPoints(double totalCompra) {

        int puntos = (int) (totalCompra / 700);
/*
        //convertir de manera auxiliar
        String puntoString = Integer.toString(puntos);
        int longitud = puntoString.length();
*/
        String message = "¡Felicitaciones! Ha ganado " + puntos + " puntos con esta compra.";
        int messageLength = message.length();


        int cellWidth = messageLength +4;  // Agregar espacio para los bordes y espacios adicionales

        // Línea superior e inferior de la celda

        String border = "+" + "-".repeat(cellWidth) + "+";



        // Ajustar el mensaje al ancho de la celda y centrarlo
        int padding = (cellWidth - messageLength) / 2;
        String formattedMessage = "|" + " ".repeat(padding) + message + " ".repeat(padding) + "|";

        return border + "\n" + formattedMessage + "\n" + border;
    }


    public int getPoints() {
        return points;
    }
}
