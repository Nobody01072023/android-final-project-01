package com.example.pizzapulse;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private Spinner itemSpinner;
    private EditText quantityEditText;
    private Button placeOrderButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        dbHelper = new DatabaseHelper(this);

        itemSpinner = findViewById(R.id.itemSpinner);
        quantityEditText = findViewById(R.id.quantityEditText);
        placeOrderButton = findViewById(R.id.placeOrderButton);

        List<String> items = dbHelper.getAllMenuItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItem = itemSpinner.getSelectedItem().toString();
                int itemId = items.indexOf(selectedItem) + 1; // Assuming item IDs start from 1
                String quantityStr = quantityEditText.getText().toString().trim();

                if (quantityStr.isEmpty()) {
                    Toast.makeText(OrderActivity.this, "Please enter quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quantity = Integer.parseInt(quantityStr);
                long result = dbHelper.placeOrder(1, itemId, quantity); // Assuming user ID is 1

                if (result != -1) {
                    Toast.makeText(OrderActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
                    quantityEditText.setText("");
                    itemSpinner.setSelection(0); // Reset spinner selection to first item
                } else {
                    Toast.makeText(OrderActivity.this, "Failed to place order", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}