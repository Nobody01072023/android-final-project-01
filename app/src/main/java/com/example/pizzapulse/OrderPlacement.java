package com.example.pizzapulse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderPlacement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placement);

        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        // Creating a new order (for example purposes, using hardcoded values)
        String orderId = String.valueOf(System.currentTimeMillis());
        List<String> items = Arrays.asList("Pizza", "Coke");
        double totalPrice = 19.99;
        String orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // Create a new Order object
        Order newOrder = new Order(orderId, items, totalPrice, orderDate);

        // Add the order to the in-memory list
        OrderService.addOrder(newOrder);

        // Navigate to the Order History screen
        Intent intent = new Intent(OrderPlacement.this, OrderHistoryActivity.class);
        startActivity(intent);
    }
}
