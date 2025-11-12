package com.example.cs213project4.menu.coffee;

public enum CupSize {
    Short("Short"),
    Tall("Tall"),
    Grande("Grande"),
    Venti("Venti");

    private String cupsize;

    /**
     * Gives the string make.
     * @param cupsize The make string.
     */
    CupSize(String cupsize) {
        this.cupsize = cupsize;
    }

}
