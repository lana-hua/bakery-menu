package com.example.cs213project4.menu.sandwich;

import com.example.cs213project4.menu.coffee.AddIns;

public enum AddOns {
    Cheese("Cheese", 1.00),
    Lettuce("Lettuce", 0.30),
    Onion("Onion", 0.30),
    Tomato("Tomato", 0.30);

    private String addOn;
    private double price;

    AddOns(String addOn, double price) {
        this.addOn = addOn;
        this.price = price;
    }

    public String getAddOn() {
        return addOn;
    }

    public static AddIns fromString(String text) {
        for (AddIns a : AddIns.values()) {
            if (a.getAddIns().equalsIgnoreCase(text)) {
                return a;
            }
        }
        return null;
    }
}