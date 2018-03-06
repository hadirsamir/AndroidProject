package com.example.hadirsamir.myshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hadirsamir.myshop.JsonClasses.Data;
import com.example.hadirsamir.myshop.JsonClasses.Reviews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GenderListActivity extends AppCompatActivity {
    private final String url2="https://api.myjson.com/bins/e0lq5";
    private RequestQueue requestQueue2;
    private ArrayList<Data>dataArrayList_women;
    private ArrayList<Data>dataArrayList_men;
    private ArrayList<Data>dataArrayList_kids;
    private RecyclerView recyView;
    GenderListAdapter genderListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_list);



        recyView=(RecyclerView)findViewById(R.id.genderRecyc);
        dataArrayList_kids = new ArrayList<>();
        dataArrayList_men = new ArrayList<>();
        dataArrayList_women = new ArrayList<>();


        // get json data
        requestQueue2 = Volley.newRequestQueue(this);
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                try {
                    //JSONArray jsonArray = new JSONArray(response2);
                    Log.d("Json",response2.toString());
                    JSONObject jsonResponse = new JSONObject(response2);
                    JSONArray jsonArray3=jsonResponse.getJSONArray("data");
                    for(int i = 0;i<jsonArray3.length();i++) {

                        Data data =new Data();
                        JSONObject product = jsonArray3.getJSONObject(i);
                        data.setProductId(product.getString("id"));
                        data.setProductImage(product.getString("imgUrl"));
                        data.setProductPrice(product.getInt("price"));
                        data.setProductGender(product.getInt("gender"));
                        data.setProductName(product.getString("name"));
                        data.setProductType(product.getInt("type"));
                        data.setCompanyID(product.getString("companyId"));



                        JSONArray jsonArray4= product.getJSONArray("reviews");
                        ArrayList<Reviews> ReviewsArrayList;
                        ReviewsArrayList =new ArrayList<>();
                        for(int j =0 ;j<jsonArray4.length();j++){

                            Reviews reviews=new Reviews();
                            JSONObject Rev = jsonArray4.getJSONObject(j);
                            reviews.setUserName(Rev.getString("username"));
                            reviews.setUserProductRating(Rev.getDouble("rate"));
                            ReviewsArrayList.add(reviews);

                        }
                        data.setReviewsList(ReviewsArrayList);
                        if(data.getProductGender()==1)
                            dataArrayList_men.add(data);
                        else if(data.getProductGender()==2)
                            dataArrayList_women.add(data);
                        else
                            dataArrayList_kids.add(data);

                    }
                    switch (getIntent().getIntExtra("gender",0)){
                        case 1:
                            genderListAdapter = new GenderListAdapter(dataArrayList_men,GenderListActivity.this) ;
                            break;
                        case 2:
                            genderListAdapter = new GenderListAdapter(dataArrayList_women,GenderListActivity.this) ;
                            break;
                        case 3 :
                            genderListAdapter = new GenderListAdapter(dataArrayList_kids,GenderListActivity.this) ;
                            break;

                    }
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(GenderListActivity.this);
                    recyView.setLayoutManager(manager);
                    recyView.setAdapter(genderListAdapter);






                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue2.add(stringRequest2);
    }
}
