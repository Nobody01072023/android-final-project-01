package com.example.pizzapulse;

import java.util.List;

public class Order {

    private int id;
    private int userId;
    private int itemId;
    private int quantity;

    public Order(int id, int userId, int itemId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Order(String orderId, List<String> items, double totalPrice, String orderDate) {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTotalPrice() {
        return null;
    }

    public String getOrderId() {
        return null;
    }

    public CharSequence getItems() {
        return null;
    }

    public String getOrderDate() {
        return null;
    }
}