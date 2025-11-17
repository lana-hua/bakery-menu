package com.example.cs213project4.menu.coffee;

public enum AddIns {
    Cream("Whipped Cream"),
    Milk("2% Milk"),
    Vanilla("Vanilla"),
    Caramel("Caramel"),
    Mocha("Mocha");

    private String addIns;
    /**
     * Gives the string make.
     * @param addIns The make string.
     */
    AddIns(String addIns) {
        this.addIns = addIns;
    }

    public String getAddIns() {
        return addIns;
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
