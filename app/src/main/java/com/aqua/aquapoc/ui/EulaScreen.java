package com.aqua.aquapoc.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.eulaModel;
import com.aqua.aquapoc.model.pondModel;
import com.aqua.aquapoc.model.siteModel;
import com.aqua.aquapoc.network.SendToServer;
import com.aqua.aquapoc.utility.ProgressBarUtils;
import com.aqua.aquapoc.utility.utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iningosu on 1/3/2017.
 */

public class EulaScreen  extends AppCompatActivity {



    TextView mEulaDes;

    ArrayList<eulaModel> eulaModelList;
    int mUserId ;
    int mEULAid;
    SendToServer mServer;

    String TAG  = getClass().getSimpleName();

    ProgressBarUtils mProgressbarUtils;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eula);

        mProgressbarUtils = ProgressBarUtils.getInstance();

        eulaModelList = getIntent().getParcelableArrayListExtra(utils.EULA_INFO);

        mUserId = getIntent().getIntExtra(utils.USER_ID,-1);

        mEulaDes = (TextView) findViewById(R.id.eula_des);

        mEulaDes.setText(eulaModelList.get(0).getDescription());
        mEULAid = eulaModelList.get(0).getEULA_ID();
        mServer = SendToServer.getInstance();


        getSupportActionBar().setTitle("EULA");



    }


    public void onAccept(View view){

         new PutEulaAcceptanceToServer().execute();


    }


    private class PutEulaAcceptanceToServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String response = null ;
            try {
                ProgressBar(true);
                response =   mServer.eulaAcceptance(mUserId,mEULAid);
            }catch (IOException ioe){
                Log.e(TAG,ioe.toString());
            }catch (Exception e){
                Log.e(TAG,e.toString());
            }

            return response;
        }



        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (null != response) {
                    if(response.contains(utils.EULA_ACCEPT_TEXT)){
                        new GetSiteDataFromServer().execute();
                    }else{
                        ProgressBar(false);
                    }
            }else{
                ProgressBar(false);
            }
        }
    }



    ArrayList<siteModel> siteModelList ;
    /**
     *  worker thread responsible for getting sites
     */
    private class GetSiteDataFromServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String response = null ;
            try {
                response =   mServer.sites(mUserId);
            }catch (IOException ioe){
                Log.e(TAG,ioe.toString());
            }catch (Exception e){
                Log.e(TAG,e.toString());
            }

            return response;
        }



        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (null != response) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<siteModel>>() {
                    }.getType();

                    System.out.println(response);
                    siteModelList = gson.fromJson(response, type);

                    MoveToNextScreen();
            }
            ProgressBar(false);
        }
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

        finish();

        Intent siteintent = new Intent(EulaScreen.this,SiteListActivity.class);
        siteintent.putParcelableArrayListExtra(utils.SITE_INFO,siteModelList);
        startActivity(siteintent);

    }

    private void ProgressBar(final boolean visibility) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (visibility) mProgressbarUtils.showProgressBar(EulaScreen.this);
                else mProgressbarUtils.hideProgressBar();
            }
        });

    }





}
