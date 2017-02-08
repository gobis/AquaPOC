package com.aqua.aquapoc.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.eulaModel;
import com.aqua.aquapoc.model.siteModel;
import com.aqua.aquapoc.model.userModel;
import com.aqua.aquapoc.network.SendToServer;
import com.aqua.aquapoc.network.response.SiteModelList;
import com.aqua.aquapoc.utility.ProgressBarUtils;
import com.aqua.aquapoc.utility.utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iningosu on 1/3/2017.
 */

public class LoginActivity extends AppCompatActivity {


    EditText emailField;
    EditText passwordField;

    String mUserMail;
    String mUserPwd;


    int mUserId;

    SendToServer mServer;
    String TAG  = getClass().getSimpleName();

    ProgressBarUtils mProgressbarUtils;

    boolean isUserAcceptedEULA;
    LinearLayout masterLayout ;


   @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_login);

       passwordField = (EditText)findViewById(R.id.login_pwd);
       emailField =  (EditText) findViewById(R.id.login_email);
       mProgressbarUtils = ProgressBarUtils.getInstance();


       masterLayout = (LinearLayout)findViewById(R.id.main_layout);

       mServer = SendToServer.getInstance();


       getSupportActionBar().setTitle("Login");

       checkForUpdates();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterManagers();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    /**
     * called when used clicked login button
     * @param view
     */
    public void onLogin(View view) {
        // get user mail and pwd

        HideSoftKeyboard();

        mUserMail = emailField.getEditableText().toString();

        if (utils.validateUserEmail(mUserMail)) {
            mUserPwd = passwordField.getEditableText().toString();

            if (utils.validateUserPwd(mUserPwd)) {

                // email & pwd are ok, proceed with service call
                new SendLoginDataToServer().execute();

            }else{
                showToast("Check your password , min 3 char ");
            }

        }else{
            showToast("Check your mail ID");
        }

    }



    public enum SCREEN {

        EULA_SCREEN,
        SITE_SCREEN
    }

    /**
     * Async task takes care of login request and response
     */

    private class SendLoginDataToServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String response = null ;
            try {
                ProgressBar(true);
                response =   mServer.login(mUserMail, mUserPwd);
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

            if(null != userModelList && userModelList.size() > 0  ){

                isUserAcceptedEULA = userModelList.get(0).getEulaAcceptance();

                if(isUserAcceptedEULA){
                    // Directly navigate to Site list
                      new GetSiteDataFromServer().execute();

                }else{
                    // show EULA for the end user
                     new GetEULADataFromServer().execute();

                }

            }else{
                ProgressBar(false);
            }

        }
    }

    List<userModel> userModelList ;
    private void updateModel(String response){
        if(response != null){

            try {
                Gson gson = new Gson();
                Type type = new TypeToken<List<userModel>>() {
                }.getType();
                //  String json = gson.toJson(list, type);
                System.out.println(response);
                userModelList = gson.fromJson(response, type);

                Log.i(TAG, userModelList.toString());
                mUserId = userModelList.get(0).getUserID();
            }catch(Exception e){
              Log.e(TAG,"Response recd from server is "+response +"   :: Exception is "+ e.toString());
            }

        }else{
            showToast("invalid response from server");
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
    }


    ArrayList<eulaModel> eulaModelList ;

    /**
     *  worker thread responsible for getting EULA info
     */
    private class GetEULADataFromServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String response = null ;
            try {
                response =   mServer.eula(mUserId);
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
                    Type type = new TypeToken<List<eulaModel>>() {
                    }.getType();
                    //  String json = gson.toJson(list, type);
                    System.out.println("EULA response "+response);
                    eulaModelList = gson.fromJson(response, type);
                    MoveToNextScreen(SCREEN.EULA_SCREEN);
            }
            ProgressBar(false);
        }
    }


    private void showToast(String msg){

        // Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();

        Snackbar snackbar = Snackbar
                .make(masterLayout, msg, Snackbar.LENGTH_LONG);

        snackbar.show();


    }


    private void MoveToNextScreen(SCREEN screen){

        switch (screen){

            case EULA_SCREEN:

                Intent eulaintent = new Intent(LoginActivity.this,EulaScreen.class);
                eulaintent.putExtra(utils.USER_ID,mUserId);
                eulaintent.putParcelableArrayListExtra(utils.EULA_INFO,eulaModelList);
                startActivity(eulaintent);
                break;

            case SITE_SCREEN:

                Intent siteintent = new Intent(LoginActivity.this,SiteListActivity.class);
                siteintent.putParcelableArrayListExtra(utils.SITE_INFO,siteModelList);
                startActivity(siteintent);

                break;
        }

    }



    private void ProgressBar(final boolean visibility) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (visibility) mProgressbarUtils.showProgressBar(LoginActivity.this);
                else mProgressbarUtils.hideProgressBar();
            }
        });

    }


    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }


    private void HideSoftKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
