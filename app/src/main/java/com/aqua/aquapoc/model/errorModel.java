package com.aqua.aquapoc.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iningosu on 2/21/2017.
 */

public class errorModel {

    @SerializedName("Message")
    String mError_msg;

    public String getError_msg() {
        return mError_msg;
    }

    public void setError_msg(String error_msg) {
        this.mError_msg = error_msg;
    }
}
