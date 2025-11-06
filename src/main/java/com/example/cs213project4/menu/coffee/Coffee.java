package com.example.cs213project4.menu.coffee;

import com.example.cs213project4.menu.MenuItem;

import java.util.ArrayList;

public class Coffee extends MenuItem {
    private CupSize size;
    private ArrayList<AddIns> addIns;

    @Override
    public double price() {
        return 0;
    }
}
