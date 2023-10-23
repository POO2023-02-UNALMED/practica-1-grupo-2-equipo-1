package com.ecart.gestorAplicacion.merchandise;

import java.io.Serializable;
import java.util.ArrayList;

public class Review implements ProductProp, Serializable {
    private String autor;
    private String comment;
    private int rating;
    private static ArrayList<Review> instances = new ArrayList<>();

    public Review(String autor, String comment, int rating) {
        this.autor = autor;
        this.comment = comment;
        this.rating = rating;
        instances.add(this);
    }
    
    public String getAuthor() {
    	return this.autor;
    }

    @Override
    public String getContent() {
        return this.autor;
    }

    public String getComment() {
    	return this.comment;
    }
    
    public int getRating() {
    	return this.rating;
    }

    public static ArrayList<Review> getInstances() {
        return instances;
    }

    public static void setInstances(ArrayList<Review> instances) {
        Review.instances = instances;
    }
}