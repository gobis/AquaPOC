package com.aqua.aquapoc.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.pondModel;
import com.aqua.aquapoc.model.pondValuesModel;
import com.aqua.aquapoc.network.NetworkStateReceiver;
import com.aqua.aquapoc.network.SendToServer;
import com.aqua.aquapoc.ui.adapter.pondListAdapter;
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

public class PondListActivity  extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    ListView pondListView;
    pondListAdapter mPondAdapter;
    SendToServer mServer;

    String TAG  = getClass().getSimpleName();

    int mPondID ;
    ProgressBarUtils mProgressbarUtils;

    ArrayList<pondModel> pondModelList;
    ArrayList<pondValuesModel> pondValuesModelList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pond_list);


        pondListView = (ListView)findViewById(R.id.pondlist);
        mPondAdapter = new pondListAdapter(this);
        pondListView.setAdapter(mPondAdapter);
        mProgressbarUtils = ProgressBarUtils.getInstance();

        mServer = SendToServer.getInstance();

        getSupportActionBar().setTitle("Ponds");


        pondModelList = getIntent().getParcelableArrayListExtra(utils.POND_INFO);
        populateUI();


        pondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPondID = (int)id;
                new GetpondValuesDataFromServer().execute();

            }
        });



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





    private class GetpondValuesDataFromServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String response = null ;
            try {
                ProgressBar(true);
                if(null != pondValuesModelList) pondValuesModelList.clear();

                response =   mServer.pondValues(mPondID);
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

            // if response has some value other than null , currently we are assuming we got some correct data
            updateModel(response);

            if(null != pondValuesModelList && pondValuesModelList.size() > 0 ){
             // navigate to next screen
             MoveToNextScreen();

            }
            ProgressBar(false);
        }
    }

    private void populateUI(){
        mPondAdapter.setData(pondModelList);
        mPondAdapter.notifyDataSetChanged();
    }

    private void updateModel(String response){
        if(response != null){

            Gson gson = new Gson();
            Type type = new TypeToken<List<pondValuesModel>>() {}.getType();
            //  String json = gson.toJson(list, type);
            System.out.println(response);
            pondValuesModelList = gson.fromJson(response, type);

            Log.i(TAG,pondValuesModelList.toString());

        }else{
            showToast("invalid response from server");
        }
    }


    @Override
    public void networkAvailablityStatus(boolean networkStatus) {

    }

    private void ProgressBar(final boolean visibility) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (visibility) mProgressbarUtils.showProgressBar(PondListActivity.this);
                else mProgressbarUtils.hideProgressBar();
            }
        });

    }


    private void MoveToNextScreen(){

        Intent intent = new Intent(PondListActivity.this,TrendActivity.class);
        intent.putParcelableArrayListExtra(utils.POND_TREND,pondValuesModelList);
        startActivity(intent);


    }



    private void showToast(String msg){
        Toast.makeText(PondListActivity.this,msg,Toast.LENGTH_SHORT).show();
    }



}
