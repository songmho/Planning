package com.songmho.planning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by songmho on 2015-01-01.
 */
public class SplashActivity extends Activity {

    int SPLASH_TIME=2000;

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
                finishSplash();
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            }
        },SPLASH_TIME);
    }

    private void finishSplash() {
        overridePendingTransition(0,android.R.anim.fade_in);
        finish();
    }
}
