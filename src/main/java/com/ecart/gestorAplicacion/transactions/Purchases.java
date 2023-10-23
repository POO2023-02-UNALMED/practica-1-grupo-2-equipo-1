package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.entites.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Purchases implements Serializable {
    private List<String> purchases = new ArrayList<>();
    private static ArrayList<Purchases> instances = new ArrayList<>();

    private String ccPurchaser;

    public Purchases(String order, String ccPurchaser) {
        purchases.add(order);
        this.ccPurchaser = ccPurchaser;
        instances.add(this);
    }

    public List<String> getPurchases() {
        return purchases;
    }

    public String getCcPurchaser() {
        return ccPurchaser;
    }

    public void setCcPurchaser(String ccPurchaser) {
        this.ccPurchaser = ccPurchaser;
    }

    public static ArrayList<Purchases> getInstances() {
        return instances;
    }

    public static void setInstances(ArrayList<Purchases> instances) {
        Purchases.instances = instances;
    }
}
