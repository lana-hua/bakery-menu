package com.example.cs213project4.menu.coffee;

import com.example.cs213project4.menu.MenuItem;

import java.util.ArrayList;

public class Coffee extends MenuItem {
    private CupSize size;
    private ArrayList<AddIns> addIns;
    final double addInPrice = 0.25;

    public Coffee(){
        super(1);
        this.size = CupSize.Short;
        this.addIns = new ArrayList<>();
    }

    @Override
    public double price() {
        return (size.price() + addIns.size()*addInPrice) * quantity;
    }
}
