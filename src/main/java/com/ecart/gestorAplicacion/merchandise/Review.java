package com.ecart.gestorAplicacion.merchandise;

public class Review implements ProductProp{
    private String autor;
    private String comment;
    private int rating;

    public Review(String autor, String comment, int rating) {
        this.autor = autor;
        this.comment = comment;
        this.rating = rating;
    }
    
    public String getAuthor() {
    	return this.autor;
    }
    
    public String getComment() {
    	return this.comment;
    }
    
    public int getRating() {
    	return this.rating;
    }
}