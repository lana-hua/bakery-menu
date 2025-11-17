package com.example.cs213project4.menu.coffee;

import com.example.cs213project4.menu.MenuItem;

import java.util.ArrayList;

public class Coffee extends MenuItem {
    private CupSize size;
    private ArrayList<AddIns> addIns;
    final double addInPrice = 0.25;

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

    @Override
    public String toString() {
        if (addIns.size() != 0) {
            return quantity + " " + size.toString() + " coffee with " + addIns + " for " + this.price();
        }
        return quantity + " " + size.toString() + " coffee for " + this.price();

    }

    @Override
    public double price() {
        double coffeeprice = (this.size.getCupPrice() + addIns.size()*addInPrice)*quantity;
        return coffeeprice;
    }
}
