package com.aqua.aquapoc.utility;

/**
 * Created by iningosu on 1/3/2017.
 */

public class utils {


    public static String USER_ID = "userID";
    public static String SITE_ID = "siteID";

    public static String SITE_INFO = "siteInfo";
    public static String POND_INFO = "pondInfo";
    public static String EULA_INFO = "eulaInfo";
    public static String POND_TREND = "pondtrend";
    public static String EULA_ACCEPT_TEXT = "User Eula successfully updated";



    public static boolean validateUserEmail(String email) {
        if (email != null && !email.contains(" ") && email.length() > 0
                && email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
            return true;
        return false;
    }


    public static boolean validateUserPwd(String password) {

        if(password != null  && password.trim().length() >=3){
            return true;

        }

        return false;
    }

}
