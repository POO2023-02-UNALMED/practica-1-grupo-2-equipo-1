package com.ecart.gestorAplicacion.merchandise;
import java.util.Date;

import scala.collection.mutable.HashMap;

class Product {
    private String name;
    private double price;
    private int totalStock;
    private int availableStock;
    private Date lastImportDate;
    private boolean isDefective;
    private Map<String, Double> discounts;
    private List<String> customerReviews;
    private int totalSales;
    private List<ProductVariant> variants;

    public Product(String name, double price, int totalStock) {
        this.name = name;
        this.price = price;
        this.totalStock = totalStock;
        this.availableStock = totalStock;
        this.lastImportDate = null;
        this.isDefective = false;
        this.discounts = new HashMap<>();
        this.customerReviews = new ArrayList<>();
        this.totalSales = 0;
        this.variants = new ArrayList<>();
    }

    public void addVariant(ProductVariant variant) {
        variants.add(variant);
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void applyDiscount(String label, double discountPercentage) {
        discounts.put(label, discountPercentage);
    }

    public void importProduct(int quantity) {
        this.totalStock += quantity;
        this.availableStock += quantity;
        this.lastImportDate = new Date();
    }

    public void setDefective(boolean isDefective) {
        this.isDefective = isDefective;
    }

    public boolean isAvailable() {
        return availableStock > 0 && !isDefective;
    }

    public double getCurrentDiscount() {
        double currentDiscount = 0.0;
        for (double discount : discounts.values()) {
            currentDiscount += discount;
        }
        return currentDiscount;
    }

    public void addCustomerReview(String review) {
        customerReviews.add(review);
    }

    public List<String> getCustomerReviews() {
        return customerReviews;
    }

    public void recordSale() {
        totalSales++;
        availableStock--;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public double calculateTotalRevenue() {
        return totalSales * price;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public int getTotalStock() {
        return totalStock;
    }
}
