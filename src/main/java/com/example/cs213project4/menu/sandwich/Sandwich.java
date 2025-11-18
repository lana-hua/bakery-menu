package com.example.cs213project4.menu.sandwich;

import com.example.cs213project4.menu.MenuItem;

import java.util.ArrayList;

public class Sandwich extends MenuItem {
    private Bread breadType;
    private Protein proteinType;
    private ArrayList<AddOns> addOns;

    public Sandwich() {
        super(1);
        this.breadType = Bread.Bagel;
        this.proteinType = Protein.Beef;
        this.addOns = new ArrayList<>();
    }

    public Sandwich(int quantity, Bread breadType, Protein proteinType, ArrayList<AddOns> addOnsList) {
        super(quantity);
        this.breadType = breadType;
        this.proteinType = proteinType;
        this.addOns = addOnsList;
    }

    @Override
    public double price() {
        double proteinPrice = proteinType.getPrice();
        double addOnsPrice = 0.0;

        for (int i = 0; i < addOns.size(); i++) {
            AddOns addOn = addOns.get(i);
            if (addOn == AddOns.Cheese){
                addOnsPrice +=  1.00;
            }
            else{
                addOnsPrice += 0.30;
            }
        }
        return (proteinPrice + addOnsPrice) * quantity;
    }

    @Override
    public String toString() {
        if (!addOns.isEmpty()) {
            return quantity + " " + proteinType.toString() + " " + breadType.toString() + " Sandwich with " + addOns + " for " + this.price();
        }
        return quantity + " " + proteinType.toString() + " " + breadType.toString()  + " Sandwich for " + this.price();

    }
}
