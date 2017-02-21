package com.aqua.aquapoc.network;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by iningosu on 1/3/2017.
 */

public interface NetworkInterface {

   Response login(String email, String password) throws IOException;
   Response sites(int userId) throws IOException ;
   Response ponds(int siteId) throws IOException;
   Response pondValues(int pondID) throws IOException;
   Response eula(int userId) throws IOException;
   Response eulaAcceptance(int userId , int eulaID) throws IOException;

}
