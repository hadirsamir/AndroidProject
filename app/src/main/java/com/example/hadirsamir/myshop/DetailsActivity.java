package com.example.hadirsamir.myshop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hadirsamir.myshop.JsonClasses.Data;

public class DetailsActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    public Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        data = (Data) getIntent().getExtras().getSerializable("dataset");

    }
}
