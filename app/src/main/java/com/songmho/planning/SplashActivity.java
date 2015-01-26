package com.songmho.planning;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * Created by songmho on 2015-01-01.
 */
public class SplashActivity extends Activity {

    int SPLASH_TIME=1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler();
    }

    private void handler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                login();
                finishSplash();
            }
        },SPLASH_TIME);
    }

    private void login() {
        SharedPreferences pref_login=getSharedPreferences("login_info",MODE_PRIVATE);
        if(pref_login!=null) {
            String email = pref_login.getString("email", "");
            String password = pref_login.getString("password", "");
            ParseUser.logInInBackground(email,password,new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    finishSplash();
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            });
        }
        else{
            finishSplash();
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finish();
        }
    }

    private void finishSplash() {
        overridePendingTransition(0,android.R.anim.fade_in);
    }
}
