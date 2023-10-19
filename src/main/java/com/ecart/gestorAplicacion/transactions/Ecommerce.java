package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ecommerce {
    private String cedula;
    private ShoppingCart shoppingCart;
    private Order order;

    public Ecommerce(String cedula) {
        this.cedula = validateCedula(cedula);
        this.shoppingCart = new ShoppingCart();
        this.order = new Order();
    }

    public void runShoppingProcess() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Product> productList = Product.getAvailableProducts();

            System.out.println("Lista de Productos Disponibles:");
            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);
                System.out.println((i + 1) + ". " + product.getName() + " - Precio: $" + product.getFormattedPrice());
            }

            System.out.println("¿Qué producto desea comprar? (Ingrese el número del producto)");
            int productNumber = Integer.parseInt(scanner.nextLine());

            if (productNumber < 1 || productNumber > productList.size()) {
                System.out.println("Número de producto inválido. Por favor, elija un número válido.");
                continue;
            }

            Product selectedProduct = productList.get(productNumber - 1);

            // Mostrar la descripción del producto seleccionado.
            System.out.println("Descripción: " + selectedProduct.getDescription());

            System.out.println("¿Cuántos productos desea comprar?");
            int cantidad = Integer.parseInt(scanner.nextLine());
            // Funcionalidad incorrecta
            // Corregir añadir el producto al array de productos disponibles

            shoppingCart.addProduct(selectedProduct, cantidad);
            order.addProduct(selectedProduct, cantidad);

            System.out.println("¿Desea agregar otro producto? (s/n)");
            String respuesta = scanner.nextLine();

            if (respuesta.equals("n")) {
                break;
            }
        }

        double totalCompra = order.calculateTotal();
        double totalDescuentos = shoppingCart.calculateTotal() - totalCompra;
        double iva = totalCompra * 0.19;

        // Generar la factura de la compra
        String factura = order.generateInvoice(iva, totalDescuentos);
        // Imprimir la factura
        System.out.println(factura);

        System.out.println("El total de la compra es: " + totalCompra);
        System.out.println("El total de descuentos es: " + totalDescuentos);
        System.out.println("El IVA es: " + iva);

        System.out.println("¿Desea acumular puntos con su compra? (s/n)");
        String respuestaPuntos = scanner.nextLine();

        if (respuestaPuntos.equals("s")) {

            // de manera local, de manera auxiliar
            // Esta como prueba
            String pointsMessage = order.getPoints(totalCompra);
            System.out.println(pointsMessage);

        }

        System.out.println("¿Cómo desea pagar?\n1. Efectivo\n2. Tarjeta");
        String tipoPago = scanner.nextLine();

        if (tipoPago.equals("1")) {
            handleCashPayment(totalCompra);
        } else if (tipoPago.equals("2")) {
            handleCardPayment();
        } else {
            System.out.println("Opción inválida. Por favor, seleccione efectivo o tarjeta.");
        }
    }


    private void handleCashPayment(double totalCompra) {
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

    private void handleCardPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, ingrese el número de tarjeta:");
        String numeroTarjeta = scanner.nextLine();

        System.out.println("Por favor, ingrese la fecha de expiración de la tarjeta:");
        String fechaExpiracion = scanner.nextLine();

        System.out.println("Por favor, ingrese el código CVV:");
        String codigoCVV = scanner.nextLine();

        System.out.println("Gracias por su compra.");
    }

    private String validateCedula(String cedula) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (cedula.matches("\\d{8,}")) {
                return cedula;
            }
            if (cedula.matches(".*[a-zA-Z].*")){
                System.out.println("La cédula no debe contener letras.\nPor favor, ingrese su cédula nuevamente:");
            }
            else {
                System.out.println("La cédula debe contener al menos 8 números.\nPor favor, ingrese su cédula nuevamente:");
            }
            cedula = scanner.nextLine();
        }
    }
/*
    private List<Product> createProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Producto A", 10.0, 0));
        products.add(new Product("Producto B", 15.0, 5));
        products.add(new Product("Producto C", 20.0, 3));
        return products;
    }
*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la tienda en línea.");
        System.out.println("Por favor, ingrese su cédula:");
        String cedula = scanner.nextLine();

        Product.createRandomProducts();

        Ecommerce ecommerce = new Ecommerce(cedula);
        ecommerce.runShoppingProcess();
    }
}

