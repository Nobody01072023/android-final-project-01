package com.example.pizzapulse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    public OrderAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_order, parent, false);
        }

        // Get the current order
        Order order = getItem(position);

        // Bind order data to the view
        TextView orderIdTextView = convertView.findViewById(R.id.orderId);
        TextView itemsTextView = convertView.findViewById(R.id.items);
        TextView totalPriceTextView = convertView.findViewById(R.id.totalPrice);
        TextView orderDateTextView = convertView.findViewById(R.id.orderDate);

        orderIdTextView.setText("Order ID: " + order.getOrderId());
        itemsTextView.setText("Items: " + String.join(", ", order.getItems()));
        totalPriceTextView.setText("Total: $" + order.getTotalPrice());
        orderDateTextView.setText("Date: " + order.getOrderDate());

        return convertView;
    }
}
