package com.example.bonaven.sociodroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class Loginactivity extends AppCompatActivity {
    TextView info;
    Button getinfo;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    AccessToken accessToken;
    FacebookCallback facebookCallback;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_loginactivity);
        callbackManager =CallbackManager.Factory.create();
        info= (TextView) findViewById(R.id.text);
        getinfo=(Button)findViewById(R.id.login_button);
        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker= new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };
        getinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funclog();
            }
        });

    }
    public  void funclog(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken=loginResult.getAccessToken();
                Profile profile=Profile.getCurrentProfile();

                GraphRequest request=GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("response",response.toString());
                                try {
                                    Log.i("emailresponse",object.get("email").toString());
                                    info.setText(object.get("email")+"");
                                    Log.i("emailresponse",object.get("email").toString());
                                    Toast.makeText(getApplicationContext(),object.get("email").toString(),Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    intent.putExtra("product",object.toString());
                                    startActivity(intent);
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters= new Bundle();
                parameters.putString("fields","id,name,email,installed,friends{id,name,picture}");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
