package com.cupagroup.controlcalidad.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.cupagroup.controlcalidad.sync.SyncManager.syncAll;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String mShareFile = "pref_data";
    private Long mSessionID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreferences = this.getApplication()
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);
        mSessionID = mPreferences.getLong("sessionId", 99);

        syncAll(SplashActivity.this, mSessionID);

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
        }, 5000);
    }
}
