package com.ecart.gestorAplicacion.merchandise;

public class Coupon implements ProductProp {
	private String author;
	private String code;
	private int discount; //la cantidad de porcentaje del descuento de 1 a 100
	
	public Coupon(String author, String code, int discount) {
		this.author = author;
		this.code = code;
		this.discount = discount;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public int getDiscount() {
		return this.discount;
	}

}
