package com.example.hadirsamir.myshop.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 08/03/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    // Cart table
    private final String CART_TABLE_NAME = "Cart";

    private final static String PRODUCT_ID = "product_id";
    private final static String PRODUCT_NAME = "product_name";
    private final static String PRODUCT_PRICE = "product_price";


    // Orders table
    private final String ORDERS_TABLE_NAME = "Orders";

    private final static String ORDER_ID = "order_id";
    private final static String ORDER_COST = "order_cost";
    private static Integer order_count = 1;


    public DataBaseHelper(Context context) {
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + CART_TABLE_NAME + "(" + PRODUCT_ID + " integer, " + PRODUCT_NAME + " TEXT, " + PRODUCT_PRICE + " integer)";

        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + ORDERS_TABLE_NAME + "(" + ORDER_ID + " integer, " + PRODUCT_ID + " integer, " + PRODUCT_NAME + " TEXT, " + PRODUCT_PRICE + " TEXT, " + ORDER_COST + " double)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + CART_TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

        query = "DROP TABLE IF EXISTS " + ORDERS_TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    // Cart Functions
    public void cartInsert(Data data) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRODUCT_ID, data.getProductId());
        contentValues.put(PRODUCT_NAME, data.getProductName());
        contentValues.put(PRODUCT_PRICE, data.getProductPrice());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(CART_TABLE_NAME, null, contentValues);
    }

    public ArrayList<Data> cartGetdata() {
        ArrayList<Data> dataArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + CART_TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();// can get write and read from DB

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Integer productID = cursor.getInt(0);
                String productName = cursor.getString(1);
                Integer productPrice = cursor.getInt(2);

                dataArrayList.add(new Data(productID.toString(), productName, productPrice));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return dataArrayList;

    }

    public void cartDeleteProduct(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + CART_TABLE_NAME + " WHERE " + PRODUCT_ID + " = " + data.getProductId() + "");
        db.close();
    }

    public void cartDeleteProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + CART_TABLE_NAME);
        db.close();
    }


    // Order Functions
    public void addOrder(ArrayList<Data> products, Double order_cost) {
        ContentValues contentValues = new ContentValues();

        for (Data product : products) {
            contentValues.put(ORDER_ID, order_count);
            contentValues.put(PRODUCT_ID, product.getProductId());
            contentValues.put(PRODUCT_NAME, product.getProductName());
            contentValues.put(PRODUCT_PRICE, product.getProductPrice());
            contentValues.put(ORDER_COST, order_cost);

            SQLiteDatabase db = getWritableDatabase();
            db.insert(ORDERS_TABLE_NAME, null, contentValues);
        }

        order_count++;
    }

    public ArrayList<OrderItem> getOrders() {
        ArrayList<OrderItem> orders = new ArrayList<>();

        String query = "SELECT DISTINCT " + ORDER_ID + " "
                + " FROM " + ORDERS_TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();// can get write and read from DB

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Integer orderID = cursor.getInt(0);

                orders.add(new OrderItem(orderID, 0.0));

            } while (cursor.moveToNext());
        }
        return orders;

    }

    public OrderItem getOrder(Integer order_id) {
        ArrayList<Integer> product_ids = new ArrayList<>();
        ArrayList<String> product_names = new ArrayList<>();
        ArrayList<Double> product_prices = new ArrayList<>();

        Double order_cost = 0.0;

        String query = "SELECT * FROM " + ORDERS_TABLE_NAME + " WHERE " + ORDER_ID + " = " + order_id.toString();

        SQLiteDatabase db = getWritableDatabase();// can get write and read from DB

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                //String productimage = cursor.getString(0);
                Integer orderID = cursor.getInt(0);
                Integer productID = cursor.getInt(1);
                String productName = cursor.getString(2);
                Double productPrice = cursor.getDouble(3);
                order_cost = cursor.getDouble(4);

                product_ids.add(productID);
                product_names.add(productName);
                product_prices.add(productPrice);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return new OrderItem(order_id, order_cost, product_ids, product_names, product_prices);
    }

}
