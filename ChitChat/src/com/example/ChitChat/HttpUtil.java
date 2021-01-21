package com.example.ChitChat;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class HttpUtil {
    public static String getResultFromWeb(String url, List<NameValuePair> lstData) throws Exception{
        HttpClient httpclient = new DefaultHttpClient();

        // Prepare a request object
        HttpPost hpost=new HttpPost(url);

        UrlEncodedFormEntity uee=new UrlEncodedFormEntity(lstData);
        hpost.setEntity(uee);

        HttpResponse resp=httpclient.execute(hpost);

        String respText=EntityUtils.toString(resp.getEntity());
        return respText;
        
        
    }
}
