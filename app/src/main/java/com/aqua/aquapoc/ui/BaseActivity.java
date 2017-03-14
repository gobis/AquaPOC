package com.aqua.aquapoc.ui;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aqua.aquapoc.R;


/**
 * Created by iningosu on 2/21/2017.
 */

public class BaseActivity extends AppCompatActivity {

    LinearLayout fullLayout;



    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        /**
         * This is going to be our actual root layout.
         */
        fullLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        /**
         * layout related to respective screen
         */
        FrameLayout activityContainer = (FrameLayout) fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(fullLayout);

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


    protected void showSnackBarError(final String msg, final String action ,final sbActionCallback callback) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Snackbar snackbar = Snackbar
                        .make(fullLayout, msg, Snackbar.LENGTH_LONG);

                if (null != action) {
                    snackbar.setAction(action, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (null != callback) {
                                callback.actionButtonClicked();
                            }
                        }
                    });

                    snackbar.setActionTextColor(ContextCompat.getColor(getBaseContext(), R.color.warning_status_red));

                }


                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);

                sbView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.snackbar_bg_color));

                snackbar.show();


            }
        });

    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}



