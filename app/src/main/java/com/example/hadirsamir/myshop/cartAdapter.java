package com.example.hadirsamir.myshop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hadirsamir.myshop.JsonClasses.Data;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 07/03/18.
 */

public class cartAdapter   extends RecyclerView.Adapter<cartAdapter.MyHolder> {
    private ArrayList<Data> dataList = new ArrayList();
    private Context context;

    public cartAdapter(ArrayList<Data> dataList , Context context) {
        this.dataList = dataList;
        this.context=context;
    }

    @Override
    public cartAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.cart_items,parent,false);
        cartAdapter.MyHolder holder =new cartAdapter.MyHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(cartAdapter.MyHolder holder, int position) {
        Data data = dataList.get(position);
        holder.setData(data);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {


        private TextView TitleTextView,PriceTextView;
        private View parent;



        public MyHolder(View itemView) {
            super(itemView);


            TitleTextView=(TextView)itemView.findViewById(R.id.textView7) ;
            PriceTextView =(TextView)itemView.findViewById(R.id.textView8);
            parent = itemView;



        }

        public void setData(final Data data) {

            TitleTextView.setText(data.getProductName());
            PriceTextView.setText(String.valueOf(data.getProductPrice()));



        }
    }
}

