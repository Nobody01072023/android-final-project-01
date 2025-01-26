package com.example.pizzapulse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "StudentTrack.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names for Address
    public static final String TABLE_ADDRESS = "address";
    public static final String COLUMN_ADDRESS_ID = "_id";
    public static final String COLUMN_ADDRESS_AREA = "area";
    public static final String COLUMN_ADDRESS_CITY = "city";

    // Table and column names for Menu
    public static final String TABLE_MENU = "menu";
    public static final String COLUMN_MENU_ID = "_id";
    public static final String COLUMN_MENU_ITEM = "item";

    // Table and column names for Order
    public static final String TABLE_ORDER = "order_table";
    public static final String COLUMN_ORDER_ID = "_id";
    public static final String COLUMN_ORDER_USER_ID = "user_id";
    public static final String COLUMN_ORDER_ITEM_ID = "item_id";
    public static final String COLUMN_ORDER_QUANTITY = "quantity";

    // SQL statement to create Address table
    private static final String SQL_CREATE_ADDRESS_TABLE =
            "CREATE TABLE " + TABLE_ADDRESS + " (" +
                    COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_ADDRESS_AREA + " TEXT," +
                    COLUMN_ADDRESS_CITY + " TEXT)";

    // SQL statement to create Menu table
    private static final String SQL_CREATE_MENU_TABLE =
            "CREATE TABLE " + TABLE_MENU + " (" +
                    COLUMN_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_MENU_ITEM + " TEXT)";

    // SQL statement to create Order table
    private static final String SQL_CREATE_ORDER_TABLE =
            "CREATE TABLE " + TABLE_ORDER + " (" +
                    COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_ORDER_USER_ID + " INTEGER," +
                    COLUMN_ORDER_ITEM_ID + " INTEGER," +
                    COLUMN_ORDER_QUANTITY + " INTEGER)";

    // SQL statement to drop Address table
    private static final String SQL_DROP_ADDRESS_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_ADDRESS;

    // SQL statement to drop Menu table
    private static final String SQL_DROP_MENU_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_MENU;

    // SQL statement to drop Order table
    private static final String SQL_DROP_ORDER_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_ORDER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(SQL_CREATE_ADDRESS_TABLE);
        db.execSQL(SQL_CREATE_MENU_TABLE);
        db.execSQL(SQL_CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables if they exist
        db.execSQL(SQL_DROP_ADDRESS_TABLE);
        db.execSQL(SQL_DROP_MENU_TABLE);
        db.execSQL(SQL_DROP_ORDER_TABLE);
        // Recreate tables
        onCreate(db);
    }

    // Method to add a new address
    public long addAddress(String area, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS_AREA, area);
        values.put(COLUMN_ADDRESS_CITY, city);
        long newRowId = db.insert(TABLE_ADDRESS, null, values);
        db.close();
        return newRowId;
    }

    // Method to retrieve all addresses
    public List<String> getAllAddresses() {
        List<String> addressList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ADDRESS, null);

        if (cursor.moveToFirst()) {
            do {
                String area = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_AREA));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_CITY));
                String address = area + ", " + city;
                addressList.add(address);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return addressList;
    }

    // Method to add a new menu item
    public long addMenuItem(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MENU_ITEM, item);
        long newRowId = db.insert(TABLE_MENU, null, values);
        db.close();
        return newRowId;
    }

    // Method to retrieve all menu items
    public List<String> getAllMenuItems() {
        List<String> menuList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU, null);

        if (cursor.moveToFirst()) {
            do {
                String menuItem = cursor.getString(cursor.getColumnIndex(COLUMN_MENU_ITEM));
                menuList.add(menuItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return menuList;
    }

    // Method to place an order
    public long placeOrder(int userId, int itemId, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_USER_ID, userId);
        values.put(COLUMN_ORDER_ITEM_ID, itemId);
        values.put(COLUMN_ORDER_QUANTITY, quantity);
        long newRowId = db.insert(TABLE_ORDER, null, values);
        db.close();
        return newRowId;
    }
}