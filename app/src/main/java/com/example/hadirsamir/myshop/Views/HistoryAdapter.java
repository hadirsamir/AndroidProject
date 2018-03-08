package com.example.hadirsamir.myshop.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hadirsamir.myshop.Models.DataBaseHelper;
import com.example.hadirsamir.myshop.Models.OrderItem;
import com.example.hadirsamir.myshop.R;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 07/03/18.
 */

public class HistoryAdapter extends BaseAdapter {
    DataBaseHelper orderDB;

    ArrayList<OrderItem> ordersList;
    Context context;

    HistoryAdapter(Context context) {
        this.context = context;
        orderDB = new DataBaseHelper(this.context);
        this.ordersList = orderDB.getOrders();
    }

    @Override
    public int getCount() {
        return ordersList.size();
    }

    @Override
    public Object getItem(int i) {
        return ordersList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list,viewGroup,false);
        final OrderItem orderItems = ordersList.get(i);
        TextView nameView = myview.findViewById(R.id.textView9);
        TextView costView = myview.findViewById(R.id.textView11);

        nameView.setText("Order no. ");
        costView.setText(ordersList.get(i).getOrderID().toString());

        return myview;
    }
}
