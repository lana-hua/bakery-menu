package com.example.cs213project4.menu.coffee;

public enum CupSize {
    Short("Short"),
    Tall("Tall"),
    Grande("Grande"),
    Venti("Venti");

    private String cupsize;
    final double SHORT_PRICE = 2.39;
    final double TALL_PRICE = 2.99;
    final double GRANDE_PRICE = 3.59;
    final double VENTI_PRICE = 4.19;

    /**
     * Gives the string make.
     * @param cupsize The make string.
     */
    CupSize(String cupsize) {
        this.cupsize = cupsize;
    }

    public double price(){
        return switch (this) {
            case Short -> SHORT_PRICE;
            case Tall -> TALL_PRICE;
            case Grande -> GRANDE_PRICE;
            case Venti -> VENTI_PRICE;
        };
    }
}
