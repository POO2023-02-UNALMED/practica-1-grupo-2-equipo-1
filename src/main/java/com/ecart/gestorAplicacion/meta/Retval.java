package com.ecart.gestorAplicacion.meta;

/**
 * Generic return value holder
 */
public class Retval {
	private String message;
	private boolean status;

	public Retval(String message) {
		this(message, true);
	}

	public Retval(String message, boolean status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public boolean getStatus() {
		return status;
	}
}
