package com.example.cs213project4.menu;

import com.example.cs213project4.menu.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Order {

    private static int nextOrderNumber = 1;  // auto-increment order numbers

    private int orderNumber;
    private ObservableList<MenuItem> items;

    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.items = FXCollections.observableArrayList();
    }

    public int getOrderNumber() {
        return orderNumber;
    }


    public ObservableList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (MenuItem item : items) {
            total += item.price();   // MUST call overridden method
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order #" + orderNumber + " (" + items.size() + " items)";
    }
}
