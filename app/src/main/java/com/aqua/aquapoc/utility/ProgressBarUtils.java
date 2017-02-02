package com.aqua.aquapoc.utility;

import android.app.ProgressDialog;
import android.content.Context;

import com.aqua.aquapoc.R;


/**
 *
 */

public class ProgressBarUtils {
    private static ProgressBarUtils progressBarInstance = new ProgressBarUtils();
    public static ProgressDialog progressDialog;

    //return instance of the class
    public static synchronized  ProgressBarUtils getInstance() {
        if (progressBarInstance == null) {
            progressBarInstance = new ProgressBarUtils();
        }
        return progressBarInstance;
    }

    private ProgressBarUtils() {
    }

    //show progress bar
    public void showProgressBar(Context pContext) {
        progressDialog = new ProgressDialog(pContext);
        progressDialog.setMessage(pContext.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    //hide progress bar
    public void hideProgressBar() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


}
