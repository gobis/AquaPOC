package com.aqua.aquapoc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iningosu on 1/4/2017.
 */

public class eulaModel implements Parcelable {

    @SerializedName("EulaID")
    int mEULA_ID;

    @SerializedName("Description")
    String mDescription;


    public eulaModel(){

    }

    private eulaModel(Parcel in) {
        this.mEULA_ID = in.readInt();
        this.mDescription = in.readString();
    }




    public int getEULA_ID() {
        return mEULA_ID;
    }

    public void setEULA_ID(int eula_id) {
        this.mEULA_ID = eula_id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mEULA_ID);
        parcel.writeString(mDescription);

    }

    public static final Parcelable.Creator<eulaModel> CREATOR
            = new Parcelable.Creator<eulaModel>() {
        public eulaModel createFromParcel(Parcel in) {
            return new eulaModel(in);
        }

        public eulaModel[] newArray(int size) {
            return new eulaModel[size];
        }
    };

}
