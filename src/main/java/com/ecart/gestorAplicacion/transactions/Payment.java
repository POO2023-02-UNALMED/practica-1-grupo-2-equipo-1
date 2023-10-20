package com.ecart.gestorAplicacion.transactions;

import java.util.ArrayList;
import java.util.List;

public class Payment {
    private double totalAmount;
    private boolean isOnCredit; //si es a cuotas o a credito
    private List<Double> installments; //cuotas

    public Payment(double totalAmount) {
        this(totalAmount, false, 0);
    }

    public Payment(double amount, boolean isOnCredit, int numberOfInstallments) {
        this.totalAmount = amount;
        this.isOnCredit = isOnCredit;
        this.installments = new ArrayList<>();

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
