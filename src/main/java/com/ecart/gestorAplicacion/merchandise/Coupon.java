package com.ecart.gestorAplicacion.merchandise;

import java.io.Serializable;
import java.util.ArrayList;

public class Coupon implements ProductProp, Serializable {
	private String author;
	private String code;
	private int discount; //la cantidad de porcentaje del descuento de 1 a 100
	private static ArrayList<Coupon> instances = new ArrayList<>();
	
	public Coupon(String author, String code, int discount) {
		this.author = author;
		this.code = code;
		this.discount = discount;
		instances.add(this);
	}


	public String getAuthor() {
		return this.author;
	}

	@Override
	public String getContent() {
		return this.code;
	}

	public String getCode() {
		return this.code;
	}
	
	public int getDiscount() {
		return this.discount;
	}

	public static ArrayList<Coupon> getInstances() {
		return instances;
	}

	public static void setInstances(ArrayList<Coupon> instances) {
		Coupon.instances = instances;
	}
}
