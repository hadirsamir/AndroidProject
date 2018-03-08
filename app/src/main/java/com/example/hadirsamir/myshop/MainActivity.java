package com.example.hadirsamir.myshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hadirsamir.myshop.Models.Data;
import com.example.hadirsamir.myshop.JsonClasses.Reviews;
import com.example.hadirsamir.myshop.Views.Cart;
import com.example.hadirsamir.myshop.Views.History;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences preferences ;
    private ImageView ProfilePic;
    private TextView UserName,UserEmail;
    private View HeaderView;
    private SharedPreferences.Editor editor;
    private final String url2="https://api.myjson.com/bins/e0lq5";
    private RequestQueue requestQueue2;


    private ArrayList<Data>dataArrayList_women;
    private ArrayList<Data>dataArrayList_men;
    private ArrayList<Data>dataArrayList_kids;
    private TextView MenText,WomenText,ChildText;
    private RecyclerView MenView,WomenView,ChildrenView;
    private RecycleAdapter recycleAdapter,WomAdapter,KidAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataArrayList_kids= new ArrayList<>();
        dataArrayList_men= new ArrayList<>();
        dataArrayList_women= new ArrayList<>();
        preferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        MenView=(RecyclerView)findViewById(R.id.MenRec);
        WomenView=(RecyclerView)findViewById(R.id.WomenRec);
        ChildrenView=(RecyclerView)findViewById(R.id.ChildRec);
        MenText=(TextView)findViewById(R.id.Men);
        WomenText=(TextView)findViewById(R.id.Women);
        ChildText = (TextView)findViewById(R.id.Child);




        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        MenView.setLayoutManager(manager);
        WomenView.setLayoutManager(manager1);
        ChildrenView.setLayoutManager(manager2);





        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        HeaderView = navigationView.getHeaderView(0);
        ProfilePic=(ImageView)HeaderView.findViewById(R.id.imageView);
        UserName=(TextView)HeaderView.findViewById(R.id.name);
        UserEmail=(TextView)HeaderView.findViewById(R.id.email);
        Picasso.with(this).load(preferences.getString("img",null)).into(ProfilePic);
        UserName.setText(preferences.getString("name",null));
        UserEmail.setText(preferences.getString("email",null));



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
                    for(Integer i = 0;i<jsonArray3.length();i++) {

                        Data data =new Data();
                        JSONObject product = jsonArray3.getJSONObject(i);
                        data.setProductId(i.toString());
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
                        if(data.getProductGender() == 1)
                            dataArrayList_men.add(data);
                        else if(data.getProductGender() == 2)
                            dataArrayList_women.add(data);
                        else
                            dataArrayList_kids.add(data);

                    }

                    //Log.d("Json class",data.getReviewsList().get(0).getUserName());
                    recycleAdapter=new RecycleAdapter(dataArrayList_men,MainActivity.this);

                    WomAdapter=new RecycleAdapter(dataArrayList_women,MainActivity.this);
                    KidAdapter=new RecycleAdapter(dataArrayList_kids,MainActivity.this);

                    MenView.setAdapter(recycleAdapter);
                    WomenView.setAdapter(WomAdapter);
                    ChildrenView.setAdapter(KidAdapter);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {
            Intent cartintent = new Intent(this,Cart.class);
            startActivity(cartintent);
        } else if (id == R.id.nav_history) {
            Intent hisintent =new Intent(this,History.class);
            startActivity(hisintent);

        } else if (id == R.id.nav_logOut) {
            editor=preferences.edit();
            editor.putBoolean("firstLogin",false);
            editor.commit();
            Intent intent =new Intent(this,SplashActivity.class);
            startActivity(intent);
            finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void action (View v){
        Intent intent =new Intent(this,GenderListActivity.class);
        int id = v.getId();
        switch (id){
            case R.id.Men:
                intent.putExtra("gender",1) ;
                break;
            case R.id.Women:
                intent.putExtra("gender",2) ;
                break;
            case R.id.Child :
                intent.putExtra("gender",3) ;
                break;

        }

        startActivity(intent);

    }
}
