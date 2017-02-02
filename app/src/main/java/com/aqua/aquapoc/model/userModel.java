package com.aqua.aquapoc.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iningosu on 1/3/2017.
 */

public class userModel  implements Parcelable{



 /*   "UserID": 2,
    "FirstName": "Sudeesh",
    "LastName": "Thatha",
    "Email": "sudeesh.thatha@gmail.com"*/

    @SerializedName("UserID")
    int mUserID;
    @SerializedName("FirstName")
    String mFirstname;
    @SerializedName("LastName")
    String mLastName;
    @SerializedName("Email")
    String mEmail;
    @SerializedName("accepted")
    boolean mIsEulaAccepted;




    public userModel(){

    }

    private userModel(Parcel in) {
        this.mFirstname = in.readString();
        this.mLastName = in.readString();
        this.mEmail = in.readString();
        this.mUserID = in.readInt();
    }


    public static final Parcelable.Creator<userModel> CREATOR
            = new Parcelable.Creator<userModel>() {
        public userModel createFromParcel(Parcel in) {
            return new userModel(in);
        }

        public userModel[] newArray(int size) {
            return new userModel[size];
        }
    };




    @Override
    public String toString() {
        return "userModel{" +
                "mUserID=" + mUserID +
                ", mFirstname='" + mFirstname + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }

    public int getUserID() {
        return mUserID;
    }

    public void setUserID(int userID) {
        this.mUserID = userID;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        this.mFirstname = firstname;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public boolean getEulaAcceptance() {
        return mIsEulaAccepted;
    }

    public void setEulaAcceptance(boolean eulaAccepted) {
        this.mIsEulaAccepted = eulaAccepted;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mFirstname);
        parcel.writeString(mLastName);
        parcel.writeString(mEmail);
        parcel.writeInt(mUserID);
    }


}
