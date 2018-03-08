package com.example.hadirsamir.myshop.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hadirsamir.myshop.Models.Data;
import com.example.hadirsamir.myshop.Models.DataBaseHelper;
import com.example.hadirsamir.myshop.R;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 07/03/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {
    private ArrayList<Data> dataList = new ArrayList();
    private Context context;

    public CartAdapter(ArrayList<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public CartAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_items, parent, false);
        CartAdapter.MyHolder holder = new CartAdapter.MyHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(CartAdapter.MyHolder holder, int position) {
        Data data = dataList.get(position);
        holder.setData(data);


    }

    public void deleteItem(int position) {
        DataBaseHelper cart = new DataBaseHelper(this.context);

        // Delete cartItem from DB
        cart.cartDeleteProduct(dataList.get(position));

        // Remove from products
        dataList.remove(position);

        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TitleTextView, PriceTextView;
        private LinearLayout linear;
        private View parent;


        public MyHolder(View itemView) {
            super(itemView);


            TitleTextView = (TextView) itemView.findViewById(R.id.textView7);
            PriceTextView = (TextView) itemView.findViewById(R.id.textView8);
            linear = (LinearLayout) itemView.findViewById(R.id.CartLinear);
            linear.setOnClickListener(this);

            parent = itemView;


        }

        public void setData(final Data data) {

            TitleTextView.setText(data.getProductName());
            PriceTextView.setText(String.valueOf(data.getProductPrice()));


        }


        @Override
        public void onClick(View view) {
            deleteItem(getLayoutPosition());
        }
    }
}

