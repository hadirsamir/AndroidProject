package com.example.hadirsamir.myshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private SignInButton btn;
    private GoogleApiClient googleApi;
    private static final int REQ_CODE =9001;
    private SharedPreferences sharedPreferences;
    private  String name , imgUrl,email;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        //shared pref file
        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        GoogleSignInOptions googleSignIn = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApi = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignIn).build();


        btn =(SignInButton) findViewById(R.id.google_button);
        btn.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.google_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.google_button:
                signIn();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void signIn(){

        Intent intent =Auth.GoogleSignInApi.getSignInIntent(googleApi);
        startActivityForResult(intent,REQ_CODE);


    }
    private void handleresult(GoogleSignInResult result){
        if(result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            name = account.getDisplayName();
            email = account.getEmail();
            imgUrl = account.getPhotoUrl().toString();
            updateUI(true);
        }
        else {
            Toast.makeText(this,result.toString(),Toast.LENGTH_LONG).show();
            Log.d("Google test",result.toString());
        }

    }
    private void  updateUI(boolean logresult){
        if(logresult == true)

        {
            editor= sharedPreferences.edit();
            editor.putString("name",name);
            editor.putString("email",email);
            editor.putString("img",imgUrl);
            editor.putBoolean("firstLogin", true);
            editor.commit();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);


        }
        else {
            Toast.makeText(this,"Sorry can'tlog",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleresult(result);

        }
    }
}
