package com.aqua.aquapoc.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.aqua.aquapoc.model.pondModel;
import com.aqua.aquapoc.model.siteModel;

import java.util.List;

/**
 * Created by iningosu on 1/4/2017.
 */

public class SiteModelList {


    List<siteModel> mList;


    public SiteModelList(List<siteModel>  list) {
        mList = list ;

    }


    public List<siteModel> getSiteList(){
        return mList;
    }



}
