package com.ecart.gestorAplicacion.entites;

import java.io.Serializable;
import java.util.ArrayList;

import com.ecart.gestorAplicacion.meta.Entity;

public class Person extends Entity implements Serializable {
	private int[] address;
	private static ArrayList<int[]> addresses = new ArrayList<>();
	private static ArrayList<Person> instances = new ArrayList<>();

	public Person(String name, String password, int[] address) {
		super(name, password);
		this.address = address;

		addresses.add(address);
		instances.add(this);
	}

	public static boolean isAddressAvailable(int[] wantedAddress) {
		if (wantedAddress[0] < 0 || wantedAddress[0] > 100)
			return false;

		if (wantedAddress[1] < 0 || wantedAddress[1] > 100)
			return false;

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
