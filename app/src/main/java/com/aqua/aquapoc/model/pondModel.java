package com.aqua.aquapoc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iningosu on 1/3/2017.
 */

public class pondModel implements Parcelable{

    /*
    "PondID": 3,
    "PondName": "Pond1 - Mangamuru Palem",
    "Location": "Mangamuru Palem",
    "PondSize": 1.5,
    "PondType": "Back Water"  */

    @SerializedName("PondID")
    int mPondID;

    @SerializedName("PondName")
    String mPondName;

    @SerializedName("Location")
    String mPondLocation;

    @SerializedName("PondSize")
    float mPondSize;

    @SerializedName("PondType")
    String mPondType;



    public pondModel(){

    }

    private pondModel(Parcel in) {
        this.mPondID = in.readInt();
        this.mPondName = in.readString();
        this.mPondLocation = in.readString();
        this.mPondSize = in.readFloat();
        this.mPondType = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mPondID);
        parcel.writeString(mPondName);
        parcel.writeString(mPondLocation);
        parcel.writeFloat(mPondSize);
        parcel.writeString(mPondType);

    }


    public static final Parcelable.Creator<pondModel> CREATOR
            = new Parcelable.Creator<pondModel>() {
        public pondModel createFromParcel(Parcel in) {
            return new pondModel(in);
        }

        public pondModel[] newArray(int size) {
            return new pondModel[size];
        }
    };



    public int getPondID() {
        return mPondID;
    }

    public void setPondID(int pondID) {
        this.mPondID = pondID;
    }

    public String getPondName() {
        return mPondName;
    }

    public void setPondName(String pondName) {
        this.mPondName = pondName;
    }

    public String getPondLocation() {
        return mPondLocation;
    }

    public void setPondLocation(String pondLocation) {
        this.mPondLocation = pondLocation;
    }

    public float getPondSize() {
        return mPondSize;
    }

    public void setPondSize(float pondSize) {
        this.mPondSize = pondSize;
    }

    public String getPondType() {
        return mPondType;
    }

    public void setPondType(String pondType) {
        this.mPondType = pondType;
    }


    @Override
    public String toString() {
        return "pondModel{" +
                "mPondID=" + mPondID +
                ", mPondName='" + mPondName + '\'' +
                ", mPondLocation='" + mPondLocation + '\'' +
                ", mPondSize=" + mPondSize +
                ", mPondType='" + mPondType + '\'' +
                '}';
    }

}
