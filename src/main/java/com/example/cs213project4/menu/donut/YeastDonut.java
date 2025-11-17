package com.example.cs213project4.menu.donut;

import com.example.cs213project4.menu.MenuItem;

public class YeastDonut extends MenuItem {
    private String flavor;
    final double price = 1.99;

    public static final String PLAIN = "Plain";
    public static final String GLAZED = "Glazed";
    public static final String CHOCOLATE_FROSTED = "Chocolate Frosted";
    public static final String VANILLA_FROSTED = "Vanilla Frosted";
    public static final String POWDERED_SUGAR = "Powdered Sugar";
    public static final String CINNAMON_SUGAR = "Cinnamon Sugar";

    public YeastDonut(int quantity, String flavor){
        super(quantity);
        this.flavor = flavor;
    }

    @Override
    public double price() {
        return price;
    }
}
