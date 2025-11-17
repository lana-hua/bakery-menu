package com.example.cs213project4.menu.donut;

import com.example.cs213project4.menu.MenuItem;

/**
 * Represents a seasonal donut menu item with specific flavors and pricing.
 * Extends the MenuItem class to inherit the quantity functionality.
 * @author Sharon Chen
 */
public class SeasonalDonut extends MenuItem {
    private String flavor;
    private static final double price = 2.49;

    public static final String PUMPKIN_SPICE = "Pumpkin Spice";
    public static final String SPOOKY = "Spooky";

    /**
     * Constructs a SeasonalDonut with specified quantity and flavor.
     * @param quantity the number of seasonal donuts
     * @param flavor the flavor of the seasonal donut
     */
    public SeasonalDonut(int quantity, String flavor){
        super(quantity);
        this.flavor = flavor;
    }

    /**
     * Calculates the price of the seasonal donut order from base price and quantity.
     * @return the total price for the seasonal donut order
     */
    @Override
    public double price() {
        return price * quantity;
    }

    /**
     * Returns the base price of a singular seasonal donut.
     * @return the base price of a single seasonal donut
     */
    public static double basePrice(){
        return price;
    }
}
