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

    public static AddOns fromString(String text) {
        for (AddOns a : AddOns.values()) {
            if (a.getAddOn().equalsIgnoreCase(text)) {
                return a;
            }
        }
        return null;
    }
}