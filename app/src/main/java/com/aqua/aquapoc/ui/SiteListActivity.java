package com.aqua.aquapoc.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aqua.aquapoc.AquaApp;
import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.pondModel;
import com.aqua.aquapoc.model.siteModel;
import com.aqua.aquapoc.model.userModel;
import com.aqua.aquapoc.network.NetworkStateReceiver;
import com.aqua.aquapoc.network.SendToServer;
import com.aqua.aquapoc.ui.adapter.siteListAdapter;
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

public class SiteListActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {



    ListView siteListView ;
    TextView mInternetChk;


    siteListAdapter mSiteAdapter;
    SendToServer mServer;
    boolean mIsNetworkAvailable;


    String TAG  = getClass().getSimpleName();

    int mUserID ;

    ProgressBarUtils mProgressbarUtils;
    private NetworkStateReceiver networkStateReceiver;


    ArrayList<siteModel> siteModelList;
    ArrayList<pondModel> pondModelList;

    int mPondID ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.site_list);


        AquaApp.isLoggedIn = true ;

        siteModelList = getIntent().getParcelableArrayListExtra(utils.SITE_INFO);


        siteListView = (ListView)findViewById(R.id.sitelist);
        mInternetChk = (TextView) findViewById(R.id.internet_check);


        mSiteAdapter = new siteListAdapter(this);
        siteListView.setAdapter(mSiteAdapter);
        mProgressbarUtils = ProgressBarUtils.getInstance();

        mServer = SendToServer.getInstance();

        getSupportActionBar().setTitle("Sites");
        populateUI();


        // now get data from server and update UI
       /* if(mUserID != -1 ) {
            new GetDataFromServer().execute();
        }else{
            showToast("wrong user id ");
        }*/

        siteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mIsNetworkAvailable) {
                    mPondID  = (int)id ;
                    new GetPondDataFromServer().execute();
                }
                else showToast("no network");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(null == networkStateReceiver) networkStateReceiver = new NetworkStateReceiver();

        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

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
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }




    private class GetPondDataFromServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String response = null ;
            try {
                ProgressBar(true);
                response =   mServer.ponds(mPondID);
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

            updateModel(response);
            if(null != pondModelList && pondModelList.size() > 0 ){
               MoveToNextScreen();
            }

            ProgressBar(false);

        }
    }


    private void updateModel(String response){
        if(response != null){
            Gson gson = new Gson();
            Type type = new TypeToken<List<pondModel>>() {}.getType();
            //  String json = gson.toJson(list, type);
            System.out.println(response);
            pondModelList = gson.fromJson(response, type);

            Log.i(TAG,pondModelList.toString());

        }else{
            showToast("invalid response from server");
        }
    }



    private void populateUI(){
        mSiteAdapter.setData(siteModelList);
        mSiteAdapter.notifyDataSetChanged();
    }

    private void showToast(String msg){
        Toast.makeText(SiteListActivity.this,msg,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void networkAvailablityStatus(boolean networkStatus) {
        mInternetChk.setVisibility(networkStatus?View.GONE:View.VISIBLE);

        mIsNetworkAvailable = networkStatus ;


    }

    private void ProgressBar(final boolean visibility) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (visibility) mProgressbarUtils.showProgressBar(SiteListActivity.this);
                else mProgressbarUtils.hideProgressBar();
            }
        });

    }


    private void MoveToNextScreen(){

        Intent intent = new Intent(SiteListActivity.this,PondListActivity.class);
        intent.putParcelableArrayListExtra(utils.POND_INFO,pondModelList);
        startActivity(intent);


    }


   /* private class GetSiteDataFromServer extends AsyncTask<Void, Void, String> {

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


            if (response != null) {

                Gson gson = new Gson();
                Type type = new TypeToken<List<siteModel>>() {
                }.getType();

                System.out.println(response);
                siteModelList = gson.fromJson(response, type);

                MoveToNextScreen(SCREEN.SITE_SCREEN);
            }

            ProgressBar(false);

        }
    }*/


}
