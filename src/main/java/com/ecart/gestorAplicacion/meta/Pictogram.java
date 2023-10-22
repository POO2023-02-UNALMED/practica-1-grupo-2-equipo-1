package com.ecart.gestorAplicacion.meta;

public interface Pictogram<T> {
	String get();
	String[] split();
	static <T> T getByName(String name) {
		return null; // Provide an appropriate implementation
	}
}
