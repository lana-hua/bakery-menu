package com.example.cs213project4.menu.sandwich;

import com.example.cs213project4.menu.coffee.AddIns;

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

    public String getProtein() {
        return protein;
    }

    public double getPrice() {
        return price;
    }
}
