package com.example.hadirsamir.myshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        if (sharedPreferences.getBoolean("firstLogin", false)) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }

        else
        {

            editor = sharedPreferences.edit();
            editor.putBoolean("firstLogin", false);
            editor.commit();
            Intent intent = new Intent(this,LogInActivity.class);
            startActivity(intent);

        }
    }
}
