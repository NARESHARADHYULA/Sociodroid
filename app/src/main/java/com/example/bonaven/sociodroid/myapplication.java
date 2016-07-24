package com.example.bonaven.sociodroid;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bonaven on 24/7/16.
 */
public class myapplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        printhashkey();
    }
    public void printhashkey(){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.bonaven.sociodroid",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
