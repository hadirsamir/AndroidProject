package com.example.hadirsamir.myshop.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hadirsamir.myshop.Models.DataBaseHelper;
import com.example.hadirsamir.myshop.Models.OrderItem;
import com.example.hadirsamir.myshop.R;

public class Order extends AppCompatActivity {
private ListView view;
private TextView titleView;
private DataBaseHelper orderDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);
        Intent intent = getIntent();
        Integer order_id = intent.getIntExtra("orderID", -1);
        view = (ListView) findViewById(R.id.productsList);
        titleView = (TextView) findViewById(R.id.orderID);

        titleView.setText("Order No." + order_id.toString());

        orderDB = new DataBaseHelper(this);
        OrderItem s = orderDB.getOrder(order_id);

        OrderAdapter adapter = new OrderAdapter(this, s);

        view.setAdapter(adapter);
    }
}
