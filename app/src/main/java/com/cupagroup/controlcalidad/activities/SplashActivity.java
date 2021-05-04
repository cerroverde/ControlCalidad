package com.cupagroup.controlcalidad.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cupagroup.controlcalidad.R;

import static com.cupagroup.controlcalidad.sync.SyncManager.syncAll;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String mShareFile = "pref_data";
    private Long mSessionID;
    private ProgressBar mProgressBar;
    private Handler mHandler;

    private int mProgressBarStatus = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler = new Handler();
        mPreferences = this.getApplication()
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);
        mSessionID =  mPreferences.getLong("sessionId", 99);

        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        syncAll(SplashActivity.this, mSessionID);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressBarStatus < 100){
                    mProgressBarStatus++;
                    android.os.SystemClock.sleep(50);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressBarStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mSessionID == 99){
                            if (syncAll(SplashActivity.this, mSessionID)){
                                Log.i("Sync","Synchronization successfully");
                            }else {
                                Log.e("Sync","Synchronization unsuccessfully");
                            }
                        }else{
                            Log.i("SessionINFO", "Session is active with number: "+mSessionID);
                        }

                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
        }).start();


        /*
        * Antigua forma de hacer un delay
        *
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSessionID == 99){
                    if (syncAll(SplashActivity.this, mSessionID)){
                        Log.i("Sync","Synchronization successfully");
                    }else {
                        Log.e("Sync","Synchronization unsuccessfully");
                    }
                }else{
                    Log.i("SessionINFO", "Session is active with number: "+mSessionID);
                }

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
        */
    }
}
