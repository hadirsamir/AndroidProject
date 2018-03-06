package com.example.hadirsamir.myshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hadirsamir.myshop.JsonClasses.Branch;
import com.example.hadirsamir.myshop.JsonClasses.Location;

/**
 * Created by hadirsamir on 26/02/18.
 */

public class LocationAdapter extends BaseAdapter {
  Location location ;


    private TextView textView;

    public LocationAdapter(Location location) {
        this.location =location;
    }

    @Override
    public int getCount() {
        return location.getBranchesNames().size();
    }

    @Override
    public Object getItem(int i) {
        return location.getBranchesNames().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup,false);
        Branch branch = location.getBranchesNames().get(i);
        textView = (TextView) myview.findViewById(R.id.textView2);
        textView.setText(branch.getAddresse());



        return myview;
    }
}
