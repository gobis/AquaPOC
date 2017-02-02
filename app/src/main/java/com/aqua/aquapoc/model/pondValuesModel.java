package com.aqua.aquapoc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iningosu on 1/6/2017.
 */

public class pondValuesModel implements Parcelable {

    @SerializedName("pH")
    int mpH;
    @SerializedName("DO")
    float mDO;
    @SerializedName("Temparature")
    float mTemp;
    @SerializedName("UpdateddateTime")
    String mUpdatedTime;

    @Override
    public String toString() {
        return "pondValuesModel{" +
                "mpH=" + mpH +
                ", mDO=" + mDO +
                ", mTemp=" + mTemp +
                ", mUpdatedTime='" + mUpdatedTime + '\'' +
                '}';
    }

    public pondValuesModel(){

    }

    public int getpH() {
        return mpH;
    }

    public void setpH(int pH) {
        this.mpH = pH;
    }

    public float getDO() {
        return mDO;
    }

    public void setDO(float DO) {
        this.mDO = mDO;
    }

    public float getTemp() {
        return mTemp;
    }

    public void setTemp(float temp) {
        this.mTemp = temp;
    }

    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.mUpdatedTime = updatedTime;
    }



    private pondValuesModel(Parcel in){
        this.mpH =  in.readInt();
        this.mDO = in.readFloat();
        this.mTemp = in.readFloat();
        this.mUpdatedTime =  in.readString();

    }



    public static final Parcelable.Creator<pondValuesModel> CREATOR
            = new Parcelable.Creator<pondValuesModel>() {
        public pondValuesModel createFromParcel(Parcel in) {
            return new pondValuesModel(in);
        }

        public pondValuesModel[] newArray(int size) {
            return new pondValuesModel[size];
        }
    };
    

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mpH);
        parcel.writeFloat(mDO);
        parcel.writeFloat(mTemp);

        if(null == mUpdatedTime) mUpdatedTime = "";

        parcel.writeString(mUpdatedTime);

    }
}
