package com.example.pizzapulse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAddressActivity extends AppCompatActivity {

    private EditText areaEditText, cityEditText;
    private Button saveAddressButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        dbHelper = new DatabaseHelper(this);

        areaEditText = findViewById(R.id.areaEditText);
        cityEditText = findViewById(R.id.cityEditText);
        saveAddressButton = findViewById(R.id.saveAddressButton);

        saveAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String area = areaEditText.getText().toString().trim();
                String city = cityEditText.getText().toString().trim();
                long result = dbHelper.addAddress(area, city);
                if (result != -1) {
                    Toast.makeText(AddAddressActivity.this, "Address saved successfully", Toast.LENGTH_SHORT).show();
                    areaEditText.setText("");
                    cityEditText.setText("");
                } else {
                    Toast.makeText(AddAddressActivity.this, "Failed to save address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}