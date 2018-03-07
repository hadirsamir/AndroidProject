package com.example.hadirsamir.myshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hadirsamir.myshop.JsonClasses.Data;

import java.util.ArrayList;

/**
 * Created by hadirsamir on 07/03/18.
 */

public class OrderDB  extends SQLiteOpenHelper {
    private String TABLE_NAME = "Orders";
    private final static String PRODUCT_NAME = "ProductName";
    //private final static String PRODUCT_IMAGE = "ProductImage";
    private final static String PRODUCT_PRICE="ProductPrice";
   // private final  static String ORDER_QUANTITY="OrderQuantity";

    public OrderDB(Context context) {
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "CREATE TABLE "+TABLE_NAME+"("+PRODUCT_NAME+ "TEXT," +PRODUCT_PRICE+ " INTEGER  )";
        sqLiteDatabase.execSQL(q);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String q = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(q);
        onCreate(sqLiteDatabase);


    }
    public void insert(Data data){
        ContentValues contentValues =new ContentValues();
        //contentValues.put(PRODUCT_IMAGE,data.getProductImage());
        contentValues.put(PRODUCT_NAME,data.getProductName());
        contentValues.put(PRODUCT_PRICE,data.getProductPrice());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,contentValues);


    }
    public ArrayList<Data> getdata(){
        ArrayList<Data> dataArrayList =new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();// can get write and read from DB
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do{
                //String productimage = cursor.getString(0);
                String productname = cursor.getString(0);
                int productprice = cursor.getInt(1);
                dataArrayList.add(new Data(productname,productprice));

            }while (cursor.moveToNext());
        }
        return dataArrayList;

    }

    }




