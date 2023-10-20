package com.ecart.gestorAplicacion.merchandise;

public class Reviews {
    private String autor;
    private String comment;

    private int rating;

    public Reviews(String autor, String comment, int rating) {
        this.autor = autor;
        this.comment = comment;
        this.rating = rating;
    }
}