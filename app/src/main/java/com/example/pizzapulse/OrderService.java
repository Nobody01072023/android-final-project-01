package com.example.pizzapulse;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static List<Order> orders = new ArrayList<>();

    // Add a new order to the list
    public static void addOrder(Order order) {
        orders.add(order);
    }

    // Get all orders
    public static List<Order> getOrders() {
        return orders;
    }
}
