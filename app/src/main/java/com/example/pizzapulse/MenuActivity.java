package com.example.pizzapulse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private CheckBox chkMargherita, chkPepperoni, chkBBQ, chkVeggie;
    private RadioGroup radioGroupDrinks;
    private TextView txtSelectedItems, txtQuantity, txtPrice, txtRating;
    private Button btnSub, btnAdd, btnOrder;
    private List<String> orderHistory;

    private int quantity = 1;
    private final int pricePerItem = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize UI components
        chkMargherita = findViewById(R.id.chk_margherita);
        chkPepperoni = findViewById(R.id.chk_pepperoni);
        chkBBQ = findViewById(R.id.chk_bbq);
        chkVeggie = findViewById(R.id.chk_veggie);
        txtSelectedItems = findViewById(R.id.txt_selected_items);
        txtQuantity = findViewById(R.id.txt_quantity);
        txtPrice = findViewById(R.id.txt_price);
        btnSub = findViewById(R.id.btn_sub);
        btnAdd = findViewById(R.id.btn_add);
        btnOrder = findViewById(R.id.btn_order);
        txtRating = findViewById(R.id.txt_rating);
        radioGroupDrinks = findViewById(R.id.radio_group_drinks);

        // Initialize order history list
        orderHistory = new ArrayList<>();

        // Quantity adjustment buttons
        btnAdd.setOnClickListener(v -> updateQuantity(1));
        btnSub.setOnClickListener(v -> updateQuantity(-1));

        // Order button click listener
        btnOrder.setOnClickListener(v -> placeOrder());

        // Checkbox listeners to update selected items
        chkMargherita.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedItems());
        chkPepperoni.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedItems());
        chkBBQ.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedItems());
        chkVeggie.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedItems());
    }

    private void updateQuantity(int change) {
        quantity = Math.max(1, quantity + change);
        txtQuantity.setText(String.valueOf(quantity));
        updatePrice();
    }

    private void updatePrice() {
        int selectedItems = 0;
        if (chkMargherita.isChecked()) selectedItems++;
        if (chkPepperoni.isChecked()) selectedItems++;
        if (chkBBQ.isChecked()) selectedItems++;
        if (chkVeggie.isChecked()) selectedItems++;

        int totalPrice = quantity * selectedItems * pricePerItem;
        txtPrice.setText("Price: BDT " + totalPrice);
    }

    private void updateSelectedItems() {
        StringBuilder selectedItems = new StringBuilder("Selected: ");
        if (chkMargherita.isChecked()) selectedItems.append("Margherita, ");
        if (chkPepperoni.isChecked()) selectedItems.append("Pepperoni, ");
        if (chkBBQ.isChecked()) selectedItems.append("BBQ Chicken, ");
        if (chkVeggie.isChecked()) selectedItems.append("Veggie, ");

        if (selectedItems.length() > 9) {
            selectedItems.setLength(selectedItems.length() - 2);
        } else {
            selectedItems = new StringBuilder("No item selected");
        }

        txtSelectedItems.setText(selectedItems.toString());
        updatePrice();
    }

    private void placeOrder() {
        String selectedDrink = "";
        int selectedDrinkId = radioGroupDrinks.getCheckedRadioButtonId();
        if (selectedDrinkId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedDrinkId);
            selectedDrink = selectedRadioButton.getText().toString();
        }

        // Store order details in the order history
        String orderDetails = txtSelectedItems.getText() + "\nDrink: " + selectedDrink + "\nQuantity: " + quantity + "\n" + txtPrice.getText();
        orderHistory.add(orderDetails);

        // Display confirmation
        Toast.makeText(this, "Order Placed!\n" + orderDetails, Toast.LENGTH_LONG).show();

        // Navigate to the OrderHistoryActivity
        Intent intent = new Intent(MenuActivity.this, OrderHistoryActivity.class);
        intent.putStringArrayListExtra("orderHistory", new ArrayList<>(orderHistory)); // Pass the order history to the new activity
        startActivity(intent);
    }
}
