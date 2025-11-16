package com.example.cs213project4.menu.donut;

import com.example.cs213project4.menu.MenuItem;

public class CakeDonut extends MenuItem {
    private String flavor;
    final double price = 2.19;

    public CakeDonut(int quantity, String flavor){
        super(quantity);
        this.flavor = flavor;
    }

    @Override
    public double price() {
        return price;
    }
}
