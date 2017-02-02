package com.aqua.aquapoc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iningosu on 1/3/2017.
 */

public class siteModel implements Parcelable {

    @SerializedName("SiteID")
    int mSiteID;
    @SerializedName("SiteName")
    String mSiteName;
    @SerializedName("Location")
    String mSiteLocation;
    @SerializedName("Alert")
    int mAlert;



    public siteModel(){

    }

    private siteModel(Parcel in) {
        this.mSiteName = in.readString();
        this.mSiteLocation = in.readString();
        this.mSiteID = in.readInt();
        this.mAlert = in.readInt();
    }


    public int getSiteID() {
        return mSiteID;
    }

    public void setSiteID(int siteID) {
        this.mSiteID = siteID;
    }

    public String getSiteName() {
        return mSiteName;
    }

    public void setSiteName(String siteName) {
        this.mSiteName = siteName;
    }

    public String getSiteLocation() {
        return mSiteLocation;
    }

    public void setSiteLocation(String siteLocation) {
        this.mSiteLocation = siteLocation;
    }

    public int getAlert() {
        return mAlert;
    }

    public void setAlert(int alert) {
        this.mAlert = alert;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mSiteName);
        parcel.writeString(mSiteLocation);
        parcel.writeInt(mSiteID);
        parcel.writeInt(mAlert);
    }


    public static final Parcelable.Creator<siteModel> CREATOR
            = new Parcelable.Creator<siteModel>() {
        public siteModel createFromParcel(Parcel in) {
            return new siteModel(in);
        }

        public siteModel[] newArray(int size) {
            return new siteModel[size];
        }
    };



    @Override
    public String toString() {
        return "siteModel{" +
                "mSiteID=" + mSiteID +
                ", mSiteName='" + mSiteName + '\'' +
                ", mSiteLocation='" + mSiteLocation + '\'' +
                ", mAlert='" + mAlert + '\'' +
                '}';
    }
}
