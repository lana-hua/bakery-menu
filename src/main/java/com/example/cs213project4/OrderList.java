package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import com.example.cs213project4.menu.Order;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class OrderList {
    private int numOfOrders = 0;
    private ObservableList<Order> orders;
    private double totalCost;

    public OrderList() {
        this.orders = FXCollections.observableArrayList();

        orders.addListener((ListChangeListener<Order>) change -> {
            totalCost = calculateTotalPrice();
        });
    }

    public double getTotalCost() {
        return totalCost;
    }

    public ObservableList<Order> getOrders() {
        return orders;
    }

    public int getNumOfOrders() {
        return numOfOrders;
    }

    public ObservableList<Order> getItems() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
        totalCost = calculateTotalPrice();
        numOfOrders++;

    }

    public void removeItem(Order order) {
        orders.remove(order);
    }

    private double calculateTotalPrice() {
        double total = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            total += orders.get(i).getTotalCost();
        }
        return total;
    }


}
