package com.example.cs213project4;

import com.example.cs213project4.menu.MenuItem;
import com.example.cs213project4.menu.Order;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.PrintWriter;

/**
 * Represents a collection of placed orders.
 * Maintains a list of orders, the total cost of all orders.
 * Allows user to add, remove, and export orders.
 * @Author Lana Huang
 */
public class OrderList {
    private int numOfOrders = 0;
    private ObservableList<Order> orders;
    private double totalCost;

    /**
     * Constructs a new and empty OrderList.
     */
    public OrderList() {
        this.orders = FXCollections.observableArrayList();

        orders.addListener((ListChangeListener<Order>) change -> {
            totalCost = calculateTotalPrice();
        });
    }

    /**
     * Returns the total cost of all orders.
     * @return total cost of all orders
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the observable list of orders.
     * @return ObservableList of Order objects
     */
    public ObservableList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds a new order to the list.
     * Updates the total cost and increments the number of orders.
     * @param order the Order to add
     */
    public void addOrder(Order order) {
        orders.add(order);
        totalCost = calculateTotalPrice();
        numOfOrders++;

    }

    /**
     * Removes an order from the list.
     * @param order the Order to remove
     */
    public void removeItem(Order order) {
        orders.remove(order);
    }

    /**
     * Exports all orders to a text file.
     * Prints the order number, menu items in order, and total cost.
     * @param file the File to write the orders to
     */
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

    /**
     * Calculates the total cost of all orders in the list.
     * @return the total cost of all orders
     */
    private double calculateTotalPrice() {
        double total = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            total += orders.get(i).getTotalCost();
        }
        return total;
    }
}
