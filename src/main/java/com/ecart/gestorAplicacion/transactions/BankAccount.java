package com.ecart.gestorAplicacion.transactions;

import java.util.Random;

public class BankAccount {
    private double balance;
    private String cvv;

    public BankAccount() {
        // saldo en cero al crear la cuenta
        this.balance = 0.0;
        // Generar un código de seguridad CVV
        this.cvv = generateCVV();
    }

    public double getBalance() {
        return balance;
    }

    public String getCVV() {
        return cvv;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Depósito exitoso. Nuevo saldo: " + balance);
        } else {
            System.out.println("El monto del depósito debe ser mayor que cero.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Retiro exitoso. Nuevo saldo: " + balance);
        } else {
            System.out.println("Monto no válido o saldo insuficiente.");
        }
    }

    public boolean validateCVV(String enteredCVV) {
        return cvv.equals(enteredCVV);
    }


    private String generateCVV() {
        Random random = new Random();
        int cvvNumber = random.nextInt(1000); // Generar un número aleatorio de 0 a 999
        return String.format("%03d", cvvNumber);
    }
}


