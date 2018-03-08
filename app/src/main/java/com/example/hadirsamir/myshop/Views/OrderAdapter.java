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

/**
 * Created by hadirsamir on 08/03/18.
 */

public class OrderAdapter extends BaseAdapter {
    DataBaseHelper orderDB;
    OrderItem order;
    Context context;

    OrderAdapter(Context context, OrderItem order) {
        this.context = context;
        orderDB = new DataBaseHelper(this.context);
        this.order = order;
    }

    @Override
    public int getCount() {
        return order.getProduct_names().size();
    }

    @Override
    public Object getItem(int i) {
        return order.getProduct_names().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_items,viewGroup,false);

        TextView nameView = myview.findViewById(R.id.textView7);
        TextView costView = myview.findViewById(R.id.textView8);

        nameView.setText(order.getProduct_names().get(i));
        costView.setText(order.getProduct_prices().get(i).toString());

        return myview;
    }
}