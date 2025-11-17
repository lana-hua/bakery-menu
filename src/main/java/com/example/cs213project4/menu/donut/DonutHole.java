package com.example.cs213project4.menu.donut;

import com.example.cs213project4.menu.MenuItem;

public class DonutHole extends MenuItem {
    private String flavor;
    final double price = 0.39;

    public static final String PLAIN = "Plain";
    public static final String JELLY = "Jelly";
    public static final String CHOCOLATE = "Chocolate";

    public DonutHole(int quantity, String flavor){
        super(quantity);
        this.flavor = flavor;
    }

    @Override
    public double price() {
        return price * quantity;
    }

    public String getFlavor(){
        return flavor;
    }

}
