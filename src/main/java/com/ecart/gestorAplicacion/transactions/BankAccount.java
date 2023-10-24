package com.ecart.gestorAplicacion.transactions;

import java.io.Serializable;
import java.util.ArrayList;

import com.ecart.gestorAplicacion.meta.Retval;

public class BankAccount implements Serializable {
	private double balance;
	private String cvv;

	private static final long serialVersionUID = 1L;
	private static ArrayList<BankAccount> instances = new ArrayList<>();

	public BankAccount(String cvv) {
		this.balance = 0.0;
		this.cvv = cvv;

		instances.add(this);
	}

	public boolean validate(String password) {
		if (this.getCVV().equals(password))
			return true;
		return false;
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

	public Retval deposit(double amount) {
		Retval retval = new Retval("Failed to deposit money", false);

		if (amount > 0) {
			this.balance += amount;
			retval = new Retval("Deposited money successfully!");
		}

		return retval;
	}

	public Retval withdraw(double amount) {
		Retval retval = new Retval("Failed to withdraw money from account. Not enough balance", false);

		if (amount > 0 && amount <= balance) {
			this.balance -= amount;
			retval = new Retval("Withdrew money successfully!");
		}

		return retval;
	}

	public static ArrayList<BankAccount> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<BankAccount> instances) {
		BankAccount.instances = instances;
	}
}
