package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ecommerce {
    private String cedula;
    private ShoppingCart shoppingCart;
    private Order order;
    private List<Product> productList;

    public Ecommerce(String cedula) {
        this.cedula = validateCedula(cedula);
        this.shoppingCart = new ShoppingCart();
        this.order = new Order();
        this.productList = createProductList(); // Crea una lista de productos aquí.
    }

    public void runShoppingProcess() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayProductList(); // Muestra la lista de productos.

            System.out.println("¿Qué producto desea comprar? (Ingrese el número del producto)");
            int productNumber = Integer.parseInt(scanner.nextLine());

            if (productNumber < 1 || productNumber > productList.size()) {
                System.out.println("Número de producto inválido. Por favor, elija un número válido.");
                continue;
            }

            Product selectedProduct = productList.get(productNumber - 1);

            System.out.println("¿Cuántos productos desea comprar?");
            int cantidad = Integer.parseInt(scanner.nextLine());

            shoppingCart.addProduct(selectedProduct.getName(), selectedProduct.getPrice(), cantidad);
            order.addProduct(selectedProduct.getName(), selectedProduct.getPrice(), cantidad);

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

    private void displayProductList() {
        System.out.println("Lista de Productos:");
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - Precio: $" + product.getPrice());
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

    private List<Product> createProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Producto A", 10.0, 0));
        products.add(new Product("Producto B", 15.0, 5));
        products.add(new Product("Producto C", 20.0, 3));
        return products;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la tienda en línea.");
        System.out.println("Por favor, ingrese su cédula:");
        String cedula = scanner.nextLine();

        Ecommerce ecommerce = new Ecommerce(cedula);
        ecommerce.runShoppingProcess();
    }
}

