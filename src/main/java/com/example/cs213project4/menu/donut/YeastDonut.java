package com.example.cs213project4.menu.donut;

import com.example.cs213project4.menu.MenuItem;

/**
 * Represents a yeast donut menu item with specific flavors and pricing.
 * Extends the MenuItem class to inherit the quantity functionality.
 * @author Sharon Chen
 */
public class YeastDonut extends MenuItem {
    private String flavor;
    private static final double price = 1.99;

    public static final String PLAIN = "Plain";
    public static final String GLAZED = "Glazed";
    public static final String CHOCOLATE_FROSTED = "Chocolate Frosted";
    public static final String VANILLA_FROSTED = "Strawberry Frosted";
    public static final String POWDERED_SUGAR = "Powdered Sugar";
    public static final String CINNAMON_SUGAR = "Cinnamon Sugar";

    /**
     * Constructs a YeastDonut with specified quantity and flavor.
     * @param quantity the number of yeast donuts
     * @param flavor the flavor of the yeast donut
     */
    public YeastDonut(int quantity, String flavor){
        super(quantity);
        this.flavor = flavor;
    }

    /**
     * Calculates the price of the yeast donut order from base price and quantity.
     * @return the total price for the yeast donut order
     */
    @Override
    public double price() {
        return price * quantity;
    }

    /**
     * Returns the base price of a singular yeast donut.
     * @return the base price of a single yeast donut
     */
    public static double basePrice(){
        return price;
    }
}
