package com.example.cs213project4.menu.donut;

import com.example.cs213project4.menu.MenuItem;

public class SeasonalDonut extends MenuItem {
    final double price = 2.49;

    @Override
    public double price() {
        return price;
    }
}
