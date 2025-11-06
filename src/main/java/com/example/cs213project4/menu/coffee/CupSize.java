package com.example.cs213project4.menu.coffee;

public enum CupSize {
    SHORT("SHORT"),
    TALL("TALL"),
    GRANDE("GRANDE"),
    VENTI("VENTI");

    private String cupsize;

    /**
     * Gives the string make.
     * @param cupsize The make string.
     */
    CupSize(String cupsize) {
        this.cupsize = cupsize;
    }

}
