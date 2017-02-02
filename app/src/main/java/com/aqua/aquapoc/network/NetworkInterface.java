package com.aqua.aquapoc.network;

import java.io.IOException;

/**
 * Created by iningosu on 1/3/2017.
 */

public interface NetworkInterface {

   String login(String email,String password) throws IOException;
   String sites(int userId) throws IOException ;
   String ponds(int siteId) throws IOException;
   String pondValues(int pondID) throws IOException;
   String eula(int userId) throws IOException;
   String eulaAcceptance(int userId , int eulaID) throws IOException;

}
