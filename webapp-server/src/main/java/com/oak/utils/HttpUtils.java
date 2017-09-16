package com.oak.utils;

import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Chennl on 7/23/2017.
 */
public class HttpUtils {

    public static CloseableHttpClient getHttpClient(String userName,String password){
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, password);
        provider.setCredentials(AuthScope.ANY, credentials);
        return  HttpClients.custom().setDefaultCredentialsProvider(provider).build();
    }

    public static String executeBasicCredentialRequest (String url,String userName,String password) {
        CloseableHttpClient httpclient =getHttpClient(userName,password);
        String responseString ="";
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                }
            } catch (IOException ioe) {

            } finally {
                response.close();
            }
        }catch (IOException e) {

        }finally {
            try {
                httpclient.close();
            }catch (IOException ee){

            }
        }
        return responseString;
    }
}
