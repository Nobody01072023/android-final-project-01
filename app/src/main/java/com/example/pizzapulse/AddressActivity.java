package com.example.pizzapulse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddressActivity extends AppCompatActivity {
    private Button addAddressButton, viewAddressListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addAddressButton = findViewById(R.id.addAddressButton);
        viewAddressListButton = findViewById(R.id.viewAddressListButton);

        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));
            }
        });

        viewAddressListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, ViewAddressListActivity.class));
            }
        });
    }
}