package com.example.hadirsamir.myshop.Views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.hadirsamir.myshop.Models.Data;
import com.example.hadirsamir.myshop.Models.DataBaseHelper;
import com.example.hadirsamir.myshop.R;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private Button checkbtn;
    ArrayList<Data> dbList;

    CartAdapter adapter;

    DataBaseHelper cartDB;
    DataBaseHelper orderDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_product_cart);

        cartRecyclerView = (RecyclerView) findViewById(R.id.CartRec);
        checkbtn = (Button) findViewById(R.id.button3);

        cartDB = new DataBaseHelper(this);
        orderDB = new DataBaseHelper(this);

        dbList = cartDB.cartGetdata();


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        cartRecyclerView.setLayoutManager(manager);
        adapter = new CartAdapter(dbList, Cart.this);

        cartRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        ArrayList<Integer> ids = new ArrayList<>();
        Double sum = 0.0;

        for (Data data : dbList) {
            sum += Double.parseDouble(String.valueOf(data.getProductPrice()));
        }

        final ArrayList<Data> products = dbList;
        final Double cost = sum;

        AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
        builder.setTitle("Confirm checkout?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                orderDB.addOrder(products, cost);
                orderDB.cartDeleteProducts();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("NO", null);

        builder.setMessage("Your Bill = " + sum);
        builder.show();
    }
}
