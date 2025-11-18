package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import com.example.cs213project4.menu.Order;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.PrintWriter;

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
    public void exportToFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {

            writer.println("All Orders");

            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);

                writer.println("\nOrder #" + order.getOrderNumber());

                ObservableList<MenuItem> items = order.getItems();
                for (int j = 0; j < items.size(); j++) {
                    MenuItem item = items.get(j);
                    writer.println(item.toString());
                }

                writer.println("\nOrder Total: $" + String.format("%.2f", order.getTotalCost()));
            }

            writer.println("\nGrand Total: $" + String.format("%.2f", totalCost));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
