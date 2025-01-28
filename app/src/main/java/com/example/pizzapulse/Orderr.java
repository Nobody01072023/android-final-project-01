package com.example.pizzapulse;

import java.util.List;

public class Orderr {
    private String orderId;
    private List<String> items;
    private double totalPrice;
    private String orderDate;

    public Orderr(String orderId, List<String> items, double totalPrice, String orderDate) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
