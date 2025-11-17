package com.example.cs213project4.menu.coffee;

import com.example.cs213project4.menu.MenuItem;

import java.util.ArrayList;

public class Coffee extends MenuItem {
    private CupSize size;
    private ArrayList<AddIns> addIns;
    final double addInPrice = 0.25;
    private int coffeprice;

    public Coffee() {
        super(1);
        this.size = CupSize.Short;
        this.addIns = new ArrayList<>();
    }

    public Coffee(int quantity, CupSize size, ArrayList<AddIns> addins) {
        super(quantity);
        this.size = size;
        this.addIns = addins;
    }

    public void setCoffeprice(int coffeprice) {
        this.coffeprice = coffeprice;
    }

    @Override
    public double price() {
        return (size.price() + addIns.size()*addInPrice) * quantity;
    }

    @Override
    public String toString() {
        return quantity + size.toString() + "coffee with" + addIns;
    }
}
