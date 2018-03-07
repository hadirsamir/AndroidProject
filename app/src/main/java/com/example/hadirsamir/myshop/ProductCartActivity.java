package com.example.hadirsamir.myshop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hadirsamir.myshop.JsonClasses.Data;

import java.util.ArrayList;

public class ProductCartActivity extends AppCompatActivity {
    private RecyclerView CartRecyclerView;
    ArrayList<Data> DbList ;
    cartAdapter adapter;
    OrderDB orderDB;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart);
        CartRecyclerView=(RecyclerView)findViewById(R.id.CartRec);
        orderDB = new OrderDB(this);
        DbList=orderDB.getdata();




        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        CartRecyclerView.setLayoutManager(manager);
        adapter=new cartAdapter(DbList,ProductCartActivity.this);
        CartRecyclerView.setAdapter(adapter);




    }
}
