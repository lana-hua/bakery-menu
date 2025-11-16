package com.example.cs213project4.menu.donut;

import com.example.cs213project4.menu.MenuItem;

public class SeasonalDonut extends MenuItem {
    private String flavor;
    final double price = 2.49;

    public SeasonalDonut(int quantity, String flavor){
        super(quantity);
        this.flavor = flavor;
    }

    @Override
    public double price() {
        return price;
    }
}
