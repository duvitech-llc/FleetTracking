package com.siriusgps.fleettracking;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SplashScreenActivity extends Activity {

    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        final TextView textView = (TextView)findViewById(R.id.loading_messages);
        textView.setText("Loading...");
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, "onCreate");
        /****** Create Thread that will sleep for 5 seconds *************/
        Thread background = new Thread() {
            public void run() {

                // After 5 seconds redirect to another intent
                Intent i=new Intent(getBaseContext(),SetupAccountActivity.class);

                try {
                    // Thread will sleep for 5 seconds
                    for(int x = 0; x <=100; x++) {
                        sleep(25);
                        progressBar.setProgress(x);
                    }


                    startActivity(i);
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }
}
