package com.ecart.gestorAplicacion.transactions;

import java.util.Scanner;

public class Ecommerce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart shoppingCart = new ShoppingCart();
        Order order = new Order();

        System.out.println("Bienvenido a la tienda en línea.");
        System.out.println("Por favor, ingrese su cédula:");
        String cedula = scanner.nextLine();

        while (true) {
            System.out.println("¿Qué producto desea comprar?");
            String producto = scanner.nextLine();

            System.out.println("¿Cuál es el precio del producto?");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.println("¿Cuántos productos desea comprar?");
            int cantidad = Integer.parseInt(scanner.nextLine());

            shoppingCart.addProduct(producto, precio, cantidad);
            order.addProduct(producto, precio, cantidad);

            System.out.println("¿Desea agregar otro producto? (s/n)");
            String respuesta = scanner.nextLine();

            if (respuesta.equals("n")) {
                break;
            }
        }

        double totalCompra = order.calculateTotal();
        double totalDescuentos = shoppingCart.calculateTotal() - totalCompra;
        double iva = totalCompra * 0.19;

        System.out.println("El total de la compra es: " + totalCompra);
        System.out.println("El total de descuentos es: " + totalDescuentos);
        System.out.println("El IVA es: " + iva);

        System.out.println("¿Desea acumular puntos con su compra? (s/n)");
        String respuestaPuntos = scanner.nextLine();

        if (respuestaPuntos.equals("s")) {
            order.getPoints();
        }

        System.out.println("¿Cómo desea pagar? (efectivo/tarjeta)");
        String tipoPago = scanner.nextLine();

        if (tipoPago.equals("efectivo")) {
            handleCashPayment(totalCompra);
        } else if (tipoPago.equals("tarjeta")) {
            handleCardPayment();
        } else {
            System.out.println("Opción inválida. Por favor, seleccione efectivo o tarjeta.");
        }
    }

    private static void handleCashPayment(double totalCompra) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, ingrese el valor a pagar:");
        double valorPago = Double.parseDouble(scanner.nextLine());

        while (valorPago < totalCompra) {
            System.out.println("El valor ingresado es insuficiente. Por favor, ingrese un valor mayor o igual a " + totalCompra);
            valorPago = Double.parseDouble(scanner.nextLine());
        }

        double cambio = valorPago - totalCompra;
        System.out.println("Gracias por su compra. Su cambio es: " + cambio);
    }

    private static void handleCardPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, ingrese el número de tarjeta:");
        String numeroTarjeta = scanner.nextLine();

        System.out.println("Por favor, ingrese la fecha de expiración de la tarjeta:");
        String fechaExpiracion = scanner.nextLine();

        System.out.println("Por favor, ingrese el código CVV:");
        String codigoCVV = scanner.nextLine();

        System.out.println("Gracias por su compra.");
    }
}

