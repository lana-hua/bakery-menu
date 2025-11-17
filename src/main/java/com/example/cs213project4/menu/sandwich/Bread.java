package com.example.cs213project4.menu.sandwich;

public enum Bread {
    Bagel("Bagel"),
    Sourdough("Sourdough"),
    Wheat("Wheat Bread");

    private String bread;


    Bread(String bread) {
        this.bread = bread;
    }

    @Override
    public String toString() {
        return bread;
    }
}
