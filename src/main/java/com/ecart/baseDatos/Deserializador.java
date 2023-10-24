package com.ecart.baseDatos;

import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Coupon;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Review;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.transactions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
public class Deserializador {
    static File rutaTemp = new File("src\\baseDatos\\temp");

    public static void deserializarUser() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\User.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            User.setInstances((ArrayList<User>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarProducts() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\Product.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            Product.setInstances((ArrayList<Product>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarStore() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\Store.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            Store.setInstances((ArrayList<Store>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarCoupon() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\Coupon.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            Coupon.setInstances((ArrayList<Coupon>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarReview() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\Review.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            Review.setInstances((ArrayList<Review>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarBankAccount() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\BankAccount.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            BankAccount.setInstances((ArrayList<BankAccount>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarOrder() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\Order.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            Order.setInstances((ArrayList<Order>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarShoppingCart() {
        try {
            FileInputStream f = new FileInputStream(new File(rutaTemp.getAbsolutePath() + "\\ShoppingCart.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            ShoppingCart.setInstances((ArrayList<ShoppingCart>) o.readObject());
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException e) {
            System.out.println("Error flujo de inicializacion");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadObjects() {
        deserializarUser();
        deserializarProducts();
        deserializarStore();
        deserializarCoupon();
        deserializarReview();
        deserializarBankAccount();
        deserializarOrder();
        deserializarShoppingCart();
    }












}
