package com.ecart.gestorAplicacion.meta;

import com.ecart.gestorAplicacion.transactions.BankAccount;

/**
 * For unique objects that "hold" other object
 * */
public abstract class Entity {
	private String name;
	private String password = "";
	private BankAccount bankAccount;

	public Entity(String username, String password) {
		this.name = username;
		this.password = password;
		this.bankAccount = new BankAccount(password);
		this.bankAccount.setBalance((double) 100);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
}
