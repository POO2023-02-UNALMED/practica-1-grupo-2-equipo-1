package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.entites.Delivery;

import java.util.ArrayList;
import java.util.List;

public class Order {
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

    public String generateInvoice(double iva, double descuentos) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Factura de Compra:\n");
        invoice.append("+-------------------------------------------------+\n");
        invoice.append("| Producto                   | Cantidad | Precio  |\n");
        invoice.append("+-------------------------------------------------+\n");

        for (Product product : selectedProducts) {
            String productName = product.getName();
            String quantity = "1"; // Por ahora, asumimos que siempre es 1 unidad
            String price = product.getFormattedPrice();

            // Asegurarse de que los valores estén alineados a la derecha
            String formattedProduct = String.format("| %-26s | %8s | %8s |\n", productName, quantity, price);

            invoice.append(formattedProduct);
        }

        invoice.append("+-------------------------------------------------+\n");

        // Asegurarse de que los valores estén alineados a la derecha en el resumen
        String subtotalLabel = "Subtotal:";
        String ivaLabel = "IVA (19%):";
        String descuentosLabel = "Descuentos:";
        String totalLabel = "Total:";

        String formattedSubtotal = String.format("| %-27s | %7s | %8s |\n", subtotalLabel, "", getFormattedSubtotal());
        String formattedIVA = String.format("| %-27s | %7s | %8s |\n", ivaLabel, "", getFormattedIVA(iva));
        String formattedDescuentos = String.format("| %-27s | %7s | %8s |\n", descuentosLabel, "", getFormattedDescuentos(descuentos));
        String formattedTotal = String.format("| %-27s | %7s | %8s |\n", totalLabel, "", getFormattedTotal(iva, descuentos));

        invoice.append(formattedSubtotal);
        invoice.append(formattedIVA);
        invoice.append(formattedDescuentos);
        invoice.append(formattedTotal);

        invoice.append("+-------------------------------------------------+\n");

        return invoice.toString();
    }



    private String getFormattedSubtotal() {
        // Calcular el subtotal (total sin IVA ni descuentos)
        double subtotal = calculateTotal();
        // Pesos colombianos
        return String.format("$%.2f", subtotal);
    }


    private String getFormattedIVA(double iva) {
        // Formatear el IVA en pesos colombianos u otro formato deseado
        return String.format("$%.2f", iva);
    }

    private String getFormattedDescuentos(double descuentos) {
        // Formatear los descuentos en pesos colombianos u otro formato deseado
        return String.format("$%.2f", descuentos);
    }

    private String getFormattedTotal(double iva, double descuentos) {
        // Calcular el total con IVA y descuentos
        double total = calculateTotal(iva, descuentos);
        // Formatear el total en pesos colombianos u otro formato deseado
        return String.format("$%.2f", total);
    }

    private double calculateTotal(double iva, double descuentos) {
        double Subtotal = calculateTotal();
        double sumaFinal = Subtotal + iva - descuentos;
        return sumaFinal;

    }


    private String getFormattedTotal() {
        // Aquí puedes aplicar el formato de moneda colombiana u otro formato que desees
        return String.format("$%.2f", calculateTotal());
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

    public void payment() {
        Payment paymentShoppingCart = new Payment(calculateTotal());
    }

    public void installmentPayment(int numberOfInstallments) {
        Payment installPaymentShoppingcart = new Payment(calculateTotal(), true, numberOfInstallments);
    }

    public void sendOrder(Delivery delivery) {
        delivery.receiveOrder(this);
    }
}

