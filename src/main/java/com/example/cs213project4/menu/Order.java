package com.example.cs213project4.menu;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Order {
    private static int nextOrderNumber = 1;  // auto-increment order numbers
    private double totalCost;

    private final int orderNumber;
    private ObservableList<MenuItem> items;

    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.items = FXCollections.observableArrayList();

        items.addListener((ListChangeListener<MenuItem>) change -> {
            totalCost = calculateTotalPrice();
        });
    }

    public Order(Order other) {
        this.orderNumber = other.orderNumber;  // keep same order #
        this.items = FXCollections.observableArrayList();

        for (MenuItem item : other.items) {
            this.items.add(item);   // shallow copy is fine if MenuItem is immutable
        }

        this.totalCost = other.totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ObservableList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalCost = calculateTotalPrice();
    }

    public void clear() {
        items.clear();
    }

    private double calculateTotalPrice() {
        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).price();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order #" + orderNumber + " (" + items.size() + " items)";
    }
}
