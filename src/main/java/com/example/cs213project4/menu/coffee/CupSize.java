package com.example.cs213project4.menu.coffee;

public enum CupSize {
    Short(2.39),
    Tall(2.99),
    Grande(3.59),
    Venti(4.19);

    private final double price;
    /**
     * Gives the string size.
     * @param price The size string.
     */
    CupSize(double price) {
        this.price = price;
    }

    public double getCupPrice() {
        return price;
    }
}
