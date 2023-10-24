package com.ecart.baseDatos;

import com.ecart.gestorAplicacion.entites.Person;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Coupon;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Review;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.transactions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Serializador {
    static File rutaTemp = new File("src\\baseDatos\\temp");

    public static void serializarUser(ArrayList<User> instancesUser) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\User.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesUser);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializarProducts(ArrayList<Product> instancesProduct) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\Product.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesProduct);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializarStore(ArrayList<Store> instancesStore) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\Store.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesStore);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializarCoupon(ArrayList<Coupon> instancesCoupon) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\Coupon.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesCoupon);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializarReview(ArrayList<Review> instancesReview) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\Review.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesReview);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializarBankAccount(ArrayList<BankAccount> instancesBankAccount) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\BankAccount.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesBankAccount);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializarOrder(ArrayList<Order> instancesOrder) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\Order.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesOrder);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializarShoppingCart(ArrayList<ShoppingCart> instancesShoppingCart) {
        try {
            FileOutputStream f = new FileOutputStream(new File(rutaTemp.getAbsolutePath()+"\\ShoppingCart.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(instancesShoppingCart);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveObjects() {
        serializarUser(User.getInstances());
        serializarProducts(Product.getInstances());
        serializarStore(Store.getInstances());
        serializarCoupon(Coupon.getInstances());
        serializarReview(Review.getInstances());
        serializarBankAccount(BankAccount.getInstances());
        serializarOrder(Order.getInstances());
        serializarShoppingCart(ShoppingCart.getInstances());
    }
}
