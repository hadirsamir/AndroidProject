package com.example.hadirsamir.myshop.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hadirsamir.myshop.Models.DataBaseHelper;
import com.example.hadirsamir.myshop.R;

public class History extends AppCompatActivity {
    private ListView HistoryList;
    private HistoryAdapter historyAdapter;
    DataBaseHelper order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyAdapter = new HistoryAdapter(this);
        HistoryList = (ListView) findViewById(R.id.historylist);
        HistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                order = new DataBaseHelper(History.this);
                Integer order_id = order.getOrders().get(i).getOrderID();

                Intent intent = new Intent(History.this, Order.class);

                intent.putExtra("orderID", order_id);
                startActivity(intent);
            }
        });
        HistoryList.setAdapter(historyAdapter);

    }
}