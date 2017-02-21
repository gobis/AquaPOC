package com.aqua.aquapoc.network;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by iningosu on 8/25/2016.
 */
public class SendToServer implements  NetworkInterface{

     OkHttpClient client ;
    static  SendToServer sSendToServer ;

    String schemaURL = "http://aquarestapi.azurewebsites.net/";
    String loginService = "LoginWithPassword";
    String siteService = "Sites";
    String pondService = "Ponds";
    String pondValueService = "PondValues";
    String eulaService = "Eula";


    public static synchronized SendToServer getInstance(){

        if(null == sSendToServer){
            sSendToServer = new SendToServer();
        }
        return  sSendToServer ;
    }


    private SendToServer(){
        client = new OkHttpClient();
    }




    @Override
    public Response login(String email, String password) throws IOException {
      String url = schemaURL+loginService;
     //   ?uname=sudeesh.thatha%40gmail.com&upassword=passthatha
      String queryParam = "?"+"uname="+email+"&"+"upassword="+password;

       return doPostRequest(url+queryParam,"");
    }

    @Override
    public Response sites(int userId) throws IOException {
        String url = schemaURL+siteService;
        //?userId=2
        String queryParam = "?"+"userId="+userId;

        return doGetRequest(url+queryParam);
    }

    @Override
    public Response ponds(int siteId) throws IOException {

        String url = schemaURL+pondService;
        //?siteId=2
        String queryParam = "?"+"siteId="+siteId;
        return doGetRequest(url+queryParam);
    }

    @Override
    public Response pondValues(int pondId) throws IOException {

        String url = schemaURL+pondValueService;
        //?siteId=2
        String queryParam = "?"+"pondId="+pondId;
        return doGetRequest(url+queryParam);
    }


    @Override
    public Response eula(int userId) throws IOException {
        String url = schemaURL+eulaService;
        //?siteId=2
        String queryParam = "?"+"userId="+userId;
        return doGetRequest(url+queryParam);
    }

    @Override
    public Response eulaAcceptance(int userId, int eulaID) throws IOException {
        String url = schemaURL+eulaService;
        String queryParam = "?"+"userId="+userId+"&"+"eulaid="+eulaID;
        return doPutRequest(url+queryParam,"");
    }




    private Response doPostRequest(final String url, final String json) throws IOException {


        RequestBody body = RequestBody.create(MediaType.parse("application/json" ), json);

        Log.i(getClass().getSimpleName(),"Url " + url +"  Request payload "+json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;

    }



    private Response doGetRequest(final String url) throws IOException {

        Log.i(getClass().getSimpleName(),"Get Url " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        return response;
    }


    private Response doPutRequest(final String url , final String json) throws IOException {

        Log.i(getClass().getSimpleName()," Put Url " + url);
        RequestBody body = RequestBody.create(MediaType.parse("application/json" ), json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();

        return response;
    }


}
