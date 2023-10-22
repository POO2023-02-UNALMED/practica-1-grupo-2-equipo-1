package com.ecart.gestorAplicacion.transactions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Payment implements Serializable {
    private double totalAmount;
    private boolean isOnCredit; //si es a cuotas o a credito
    private List<Double> installments; //cuotas
    private static ArrayList<Payment> instances = new ArrayList<>();

    public Payment(double totalAmount) {
        this(totalAmount, false, 0);
    }

    public Payment(double amount, boolean isOnCredit, int numberOfInstallments) {
        this.totalAmount = amount;
        this.isOnCredit = isOnCredit;
        this.installments = new ArrayList<>();
        instances.add(this);

        if (isOnCredit) {
            //calcula el valor de cada cuota
            double installmentAmount = totalAmount / numberOfInstallments;

            for (int i = 0; i < numberOfInstallments; i++) {
                installments.add(installmentAmount);
            }
        } else {
            //pago de contado, solo hay una cuota
            installments.add(totalAmount);
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isOnCredit() {
        return isOnCredit;
    }

    public List<Double> getInstallments() {
        return installments;
    }

    public static ArrayList<Payment> getInstances() {
        return instances;
    }

    public static void setInstances(ArrayList<Payment> instances) {
        Payment.instances = instances;
    }

    // Simular un proceso de pago
    public boolean processPayment() {
        if (isOnCredit) {
            System.out.println("Procesando pago fraccionado en " + installments.size() + " cuotas");
        } else {
            System.out.println("Procesando pago de contado de " + totalAmount);
        }
        return true; // Devuelve true si el pago se realizó con éxito
    }

}
