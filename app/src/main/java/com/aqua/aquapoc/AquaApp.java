package com.aqua.aquapoc;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by iningosu on 2/2/2017.
 */
public class AquaApp  extends Application {

    private static AquaApp mInstance;
    public static boolean isLoggedIn = false;

    public AquaApp() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getContext() {
        return mInstance;
    }

    public static synchronized AquaApp getInstance() {
        return mInstance;
    }

    /**
     * @return the main resources from the Application
     */
    public static Resources getAppResources() {
        if (mInstance == null)
            return null;
        return mInstance.getResources();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
