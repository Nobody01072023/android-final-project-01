package com.example.pizzapulse;
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
}