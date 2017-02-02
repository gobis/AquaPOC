package com.aqua.aquapoc.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.aqua.aquapoc.model.pondModel;

import java.util.List;

/**
 * Created by iningosu on 1/4/2017.
 */

public class PondModelList implements Parcelable{

    List<pondModel>  mList;




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public PondModelList(List<pondModel>  list) {
        mList = list ;

    }


    public List<pondModel> getPondList(){
        return mList;
    }


}
