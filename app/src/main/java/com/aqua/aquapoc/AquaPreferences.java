package com.aqua.aquapoc;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by iningosu on 2/2/2017.
 */
public class AquaPreferences {

    public final String PREFS_NAME = "AquaPrefsFile";

    private Context context;
    private SharedPreferences settings;

    private static AquaPreferences sInstance;


    private AquaPreferences(Context c) {
        context = c;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

    }

    public static AquaPreferences getInstance(Context c) {
        if (sInstance == null) {
            if (c == null) {
                return null;
            }
            sInstance = new AquaPreferences(c);
        }

        return sInstance;
    }
}
