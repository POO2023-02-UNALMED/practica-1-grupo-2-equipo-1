package com.ecart.gestorAplicacion.merchandise;

import com.ecart.gestorAplicacion.entites.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Product {
    private String name;
    private double price;
    private String formattedPrice;
    private String description;
    private int quantity;

    private static List<Product> availableProducts = new ArrayList<>();

    private List<User> subscribers = new ArrayList<>();

    public Product(String name, double price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
/*
    public Product(String name, String priceCOP, String description, int quantity) {
        this.name = name;
        this.priceCOP = priceCOP;
        this.description = description;
        this.quantity = quantity;
    }
*/
    public Product(String name, double price, String formattedPrice, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.formattedPrice = formattedPrice;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void subscribe(User user) {
        subscribers.add(user);
        // user.suscribeToProduct(this);
    }

    public static void createRandomProducts() {
        String[] productNames = {"Product A", "Product B", "Product C", "Product D", "Product E", "Product F", "Product G", "Product H", "Product I", "Product J", "Product K", "Product L", "Product M", "Product N", "Product O"};
        double minPrice = 5000.0; // Precio mínimo de 5.000 pesos
        double maxPrice = 300000.0; // Precio máximo de 300.000 pesos
        String[] descriptions = {"Description 1", "Description 2", "Description 3", "Description 4", "Description 5", "Description 6", "Description 7", "Description 8", "Description 9", "Description 10", "Description 11", "Description 12", "Description 13", "Description 14", "Description 15"};

        //availableProducts.clear(); // Limpiar lista

        Locale colombianLocale = new Locale("es", "CO");
        NumberFormat colombianCurrencyFormat = NumberFormat.getCurrencyInstance(colombianLocale);


        DecimalFormat df = new DecimalFormat("#,###,###,###.##");
        df.setGroupingUsed(true);
        df.setGroupingSize(3);

        for (int i = 0; i < 15; i++) {
            String name = productNames[i];
            double price = minPrice + (maxPrice - minPrice) * new Random().nextDouble();
            price = Math.round(price * 100.0) / 100.0; // Redondear a dos decimales

            //Formato deseado

            String formattedPrice = colombianCurrencyFormat.format(price); // Formato de moneda colombiana

            //String formattedPrice = "COP " + df.format(price); // Agregar el símbolo de la moneda COP

            String description = descriptions[i];
            int quantity = new Random().nextInt(50); // Cantidad aleatoria

            Product product = new Product(name, price, formattedPrice, description, quantity);
            availableProducts.add(product);




/*
            Product product = new Product(name, formattedPrice, description, quantity);
            availableProducts.add(product); // Agrega el producto a la lista disponible
*/
        }
    }



    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }
}
