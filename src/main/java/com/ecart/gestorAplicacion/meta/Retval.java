package com.ecart.gestorAplicacion.meta;

/**
 * Generic return value holder
 */
public class Retval {
	private String message;
	private boolean ok;

	public Retval() {
		this("", false);
	}

	public Retval(String message) {
		this(message, true);
	}

	public Retval(String message, boolean ok) {
		this.message = message;
		this.ok = ok;
	}

	public String getMessage() {
		return message;
	}

	public boolean ok() {
		return ok;
	}
}
