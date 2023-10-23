package com.ecart.gestorAplicacion.meta;

import com.ecart.gestorAplicacion.entites.Person;

public class Notification {
	private Person userFrom;
	private String message;

	public Notification(Person userFrom, String message) {
		this.userFrom = userFrom;
		this.message = message;
	}

	public Person getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(Person userFrom) {
		this.userFrom = userFrom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
