package com.example.pizzapulse;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    private ListView listViewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        // Initialize ListView
        listViewOrders = findViewById(R.id.listViewOrders);

        // Get the order history from the Intent
        ArrayList<String> orderHistory = getIntent().getStringArrayListExtra("orderHistory");

        // Set up an adapter to display the orders
        if (orderHistory != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderHistory);
            listViewOrders.setAdapter(adapter);
        }
    }
}
