package com.example.cs213project4.menu.sandwich;

/**
 * Enum of the Sandwich Protein types that includes all the possible bread types for the sandwich
 * @Author Sharon Chen
 */
public enum Protein {
    Beef("Beef", 12.99),
    Chicken("Chicken", 10.99),
    Salmon("Salmon", 14.99);

    private String protein;
    private double price;

    Protein(String protein, double price) {
        this.protein = protein;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return protein;
    }
}
