package com.example.pizzapulse;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewAddressListActivity extends AppCompatActivity {

    private ListView addressListView;
    private ArrayAdapter<String> adapter;
    private List<String> addressList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_address_list);

        dbHelper = new DatabaseHelper(this);

        addressListView = findViewById(R.id.addressListView);
        addressList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addressList);
        addressListView.setAdapter(adapter);

        loadAddresses();
    }

    private void loadAddresses() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ADDRESS_AREA,
                DatabaseHelper.COLUMN_ADDRESS_CITY
        };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_ADDRESS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        addressList.clear();

        while (cursor.moveToNext()) {
            String area = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS_AREA));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS_CITY));
            String address = area + ", " + city;
            addressList.add(address);
        }

        adapter.notifyDataSetChanged();
        cursor.close();
    }
}