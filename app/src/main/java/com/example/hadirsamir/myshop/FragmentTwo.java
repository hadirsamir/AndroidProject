package com.example.hadirsamir.myshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hadirsamir.myshop.JsonClasses.Branch;
import com.example.hadirsamir.myshop.JsonClasses.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by hadirsamir on 25/02/18.
 */

public class FragmentTwo extends Fragment {
    private  View view;
    private ListView listView;
    private LocationAdapter adapter;
    private ArrayList<Location> list;
    private final String url1 = " https://api.myjson.com/bins/130t4t";
    private RequestQueue requestQueue;
    private   static String id;
    private Location location ;
    private TextView textView;
    private ArrayList<Branch> branchArrayList;






    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.secfrag, container, false);
        listView = (ListView) view.findViewById(R.id.branchlist);
        textView =(TextView)view.findViewById(R.id.textView4);
        DetailsActivity detailsActivity = (DetailsActivity)getActivity();
        id = detailsActivity.data.getCompanyID();
        requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("jason",response);
                   JSONArray jsonArray = new JSONArray(response);
                    location = new Location();

                     branchArrayList = new ArrayList<>();

                    for (int i =0 ; i<jsonArray.length();i++){
                        JSONObject branchObject =jsonArray.getJSONObject(i);
                        String brandID = branchObject.getString("id");
                        if(id.equals(brandID)){


                            JSONArray jsonArray1= branchObject.getJSONArray("branches");
                            for ( int j=0;j<jsonArray1.length();j++){
                                Branch branch = new Branch();
                                JSONObject branchObject1 =jsonArray1.getJSONObject(j);
                                branch.setAddresse(branchObject1.getString("address"));
                                branch.setLong(branchObject1.getString("lng"));
                                branch.setLat(branchObject1.getString("lat"));
                                branchArrayList.add(branch);


                            }


                            location.setBrandName(branchObject.getString("name"));
                            location.setBranchesNames(branchArrayList);
                           // Log.d("list",location.getBranchesNames().get(i).getLat());
                            textView.setText(location.getBrandName());
                            adapter = new LocationAdapter(location);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String uri = "http://maps.google.com/maps?q=loc:"+location.getBranchesNames().get(i).getLat()+","+location.getBranchesNames().get(i).getLong()+" ( adress1 )";

                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                    getActivity().startActivity(intent);


                                }
                            });



                        }




                   }
                } catch (JSONException e) {
                   e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("json",error.toString());


            }
        });

        requestQueue.add(stringRequest);





        return view;
    }

}
