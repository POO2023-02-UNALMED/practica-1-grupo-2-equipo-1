package com.ecart.gestorAplicacion.entites;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ecart.gestorAplicacion.meta.Entity;
import com.ecart.gestorAplicacion.meta.Notification;

public abstract class Person extends Entity implements Serializable {
	private int[] address;
	private static ArrayList<int[]> addresses = new ArrayList<>();
	private ArrayList<Notification> notifications = new ArrayList<>();

	public Person(String name, String password, int[] address) {
		super(name, password);
		this.address = address;

		addresses.add(address);
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

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	public static void sendNotification(Person toPerson, String message) {
		Notification notification = new Notification(toPerson, message);
		toPerson.addNotification(notification);

	}

	public void addNotification(Notification notification) {
		notifications.add(notification);
	}

	public void deleteNotification(Notification notification) {

		notifications.remove(notification);

	}

	public void receiveNotification(Notification notification) {
		notifications.add(notification);
	}
}
