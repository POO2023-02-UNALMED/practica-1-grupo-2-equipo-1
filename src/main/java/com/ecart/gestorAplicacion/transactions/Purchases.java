package com.ecart.gestorAplicacion.transactions;

import com.ecart.gestorAplicacion.entites.User;

import java.util.ArrayList;
import java.util.List;

public class Purchases {
    private List<String> purchases = new ArrayList<>();

    private String ccPurchaser;

    public Purchases(String order, String ccPurchaser) {
        purchases.add(order);
        this.ccPurchaser = ccPurchaser;
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




}
