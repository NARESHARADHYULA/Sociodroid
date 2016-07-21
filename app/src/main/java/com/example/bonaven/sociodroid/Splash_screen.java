package com.example.bonaven.sociodroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                nextactivity();
                finish();
            }
        },3000);
    }
    public void nextactivity(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
