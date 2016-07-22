package com.example.bonaven.sociodroid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {
    private ProfileTracker mProfileTracker;
    private CallbackManager mcallbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mcallbackManager=CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email","user_friends");
        loginButton.registerCallback(mcallbackManager,mCallback);

        }
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Log.i("accessToken",accessToken+"");
           if (Profile.getCurrentProfile() == null) {
                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                        // profile2 is the new profile
                        Log.i("profile", profile2.getFirstName());
                        Toast.makeText(getApplicationContext(),profile2.getFirstName(),Toast.LENGTH_LONG).show();
                        mProfileTracker.stopTracking();
                    }
                };
            }
            else{
                Profile profile = Profile.getCurrentProfile();
                Log.i("profile", profile.getFirstName());
               Toast.makeText(getApplicationContext(),profile.getFirstName(),Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(getApplicationContext(), "errorr", Toast.LENGTH_SHORT).show();
            Log.i("exception",error.toString());
        }
    };

       @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("requestcode",requestCode+"");
        Log.i("resultCode",resultCode+"");
        Log.i("data",data+"");
        mcallbackManager.onActivityResult(requestCode,resultCode,data);
    }

}
