package com.example.hadirsamir.myshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hadirsamir.myshop.JsonClasses.Reviews;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 06/03/18.
 */

public class ReviewsAdapter extends BaseAdapter{
    private ArrayList<Reviews>reviews;


    private TextView textView;
    private TextView textView2;

    public ReviewsAdapter(ArrayList<Reviews> reviews) {
        this.reviews =reviews;
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int i) {
        return reviews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);

        textView = (TextView) myview.findViewById(R.id.textView5);
        textView2 =(TextView) myview.findViewById(R.id.textView6);
        textView.setText(reviews.get(i).getUserName());
        textView2.setText(String.valueOf(reviews.get(i).getUserProductRating()));



        return myview;
    }
}
