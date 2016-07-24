package com.example.bonaven.sociodroid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private  Profile profile;
    private  String imageplaceholder="http://placehold.it/350x150";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String data=getIntent().getStringExtra("product");
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
        //buidprofile(profile);
    }
    public void buidprofile(Profile profile){
       ImageView profilepic= (ImageView) findViewById(R.id.profilepic);
        String imagurl=profile+"";
        Toast.makeText(getApplicationContext(),imagurl,Toast.LENGTH_LONG).show();
//        Picasso.with(this)
//                .load()
//                .placeholder(R.drawable.imageplaceholder)
//                .error(R.drawable.imageplaceholder)
//                .into(profilepic);
    }

}
