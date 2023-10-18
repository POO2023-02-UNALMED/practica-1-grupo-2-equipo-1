package com.ecart.gestorAplicacion.merchandise;

import java.util.UUID;
import java.util.ArrayList;

import com.ecart.gestorAplicacion.ID;

public class Store implements ID {
	private String name;
	private String passcode; // for joining the business
	private ArrayList<ID> products;
	private ArrayList<ID> members;
	private UUID id;

	private static ArrayList<Store> instances = new ArrayList<>();

	public Store(String name, String passcode) {
		this.name = name;
		this.passcode = passcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public static Store getById(UUID id) {
		for (Store user : instances) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public static Store getByCredentials(String name, String passcode) {
		for (Store store : instances) {
			if (store.getName().equals(name) && store.getPasscode().equals(passcode)) {
				return store;
			}
		}
		return null;
	}
}
