package com.aqua.aquapoc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aqua.aquapoc.AquaApp;
import com.aqua.aquapoc.R;

/**
 * Created by iningosu on 1/3/2017.
 */

public class SplashScreen extends AppCompatActivity {


    Handler mUiHandler ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        mUiHandler = new Handler();
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MoveToNextScreen();
            }
        },3000);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void MoveToNextScreen(){

        // check if your is already logged in

      /*  if(AquaApp.isLoggedIn){
            Intent intent = new Intent(SplashScreen.this,SiteListActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
            startActivity(intent);
        }*/

        Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
        startActivity(intent);

        finish();

    }


}
