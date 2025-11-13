package com.example.cs213project4.menu;

public abstract class MenuItem {
    protected int quantity;
    public abstract double price();

    public MenuItem(int quantity){
        this.quantity = quantity;
    }
}
