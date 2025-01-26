package com.example.pizzapulse;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private Button addMenuItemButton, viewMenuListButton;
    private ListView menuListView;
    private List<String> menuItems;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        addMenuItemButton = findViewById(R.id.addMenuItemButton);
        viewMenuListButton = findViewById(R.id.viewMenuListButton);
        menuListView = findViewById(R.id.menuListView);

        // Initialize menu items list and adapter
        menuItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
        menuListView.setAdapter(adapter);

        // Example: Populate initial menu items (replace with actual data from database)
        menuItems.add("Margherita Pizza");
        menuItems.add("Pepperoni Pizza");
        menuItems.add("Spaghetti Carbonara");
        adapter.notifyDataSetChanged();

        addMenuItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem();
            }
        });

        viewMenuListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenuListVisibility();
            }
        });
    }

    private void addMenuItem() {
        // Example: Add new menu item (replace with actual logic to add to database)
        String newItem = "New Menu Item";
        menuItems.add(newItem);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, newItem + " added to menu", Toast.LENGTH_SHORT).show();
    }

    private void toggleMenuListVisibility() {
        // Toggle visibility of menuListView
        if (menuListView.getVisibility() == View.VISIBLE) {
            menuListView.setVisibility(View.GONE);
        } else {
            menuListView.setVisibility(View.VISIBLE);
        }
    }
}