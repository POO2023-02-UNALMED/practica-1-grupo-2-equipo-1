package com.ecart.gestorAplicacion.entites;

import java.util.ArrayList;

import com.ecart.gestorAplicacion.meta.Entity;

public class Person extends Entity {
	private int[] address;
	private static ArrayList<int[]> addresses = new ArrayList<>();

	public Person(String name, String password, int[] address) {
		super(name, password);
		this.address = address;

		addresses.add(address);
	}


	public static boolean isAddressAvailable(int[] wantedAddress) {
		for (int[] address : addresses) {
			if (address[0] == wantedAddress[0] && address[1] == wantedAddress[1])
				return false;
		}
		return true;
	}


	public int[] getAddress() {
		return address;
	}


	public void setAddress(int[] address) {
		this.address = address;
	}


	public static ArrayList<int[]> getAddresses() {
		return addresses;
	}

	public static void setAddresses(ArrayList<int[]> addresses) {
		Person.addresses = addresses;
	}
}
