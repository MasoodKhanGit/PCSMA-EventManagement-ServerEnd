package com.masood.facebookevents;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;

/**
 * Created by Masood on 4/20/2015.
 */
public class UrlParser {

    static String accToken = "CAACEdEose0cBAIZC64EZAWpMMFTZBiONMcG9ncFWhVD7uEuyWtPSeQQmcjHfgAdKgfPsQB7kcxZCH1DL9uUrBgok8tHJZB5Y7vvNoo8t9RfuP2djFFxCoT8AosOKULxPi9ZABsarLgyQonR8HQpZCC0nU2bjFjEkBRG7kZA8eTaz76eznpKGmoJa1eWtmf2ajskoJxrdtNY2g2ngOlRqT9YI";

    protected String getUrl(String event_id) {

        String url = "https://graph.facebook.com/v2.3/"+event_id+"?fields=cover&access_token=" + accToken;
        String source="";

        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet();
            get.setURI(new URI(url));
            HttpResponse res = client.execute(get);
            HttpEntity entity = res.getEntity();
            String resStr = EntityUtils.toString(entity);

            if (res == null) {
                //"Could not fetch the responce"
            } else {

                JSONObject jObj = new JSONObject(resStr);
                JSONObject src = new JSONObject(jObj.getString("cover"));
                source = src.getString("source");

            }


        } catch (Exception ex) {
            //ex.printStackTrace();
            source="";
        }
        return source;
    }

    public String getDescription(String event_id){
        String url = "https://graph.facebook.com/v2.3/"+event_id+"?fields=description&access_token="+accToken;
        String source="";

        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet();
            get.setURI(new URI(url));
            HttpResponse res = client.execute(get);
            HttpEntity entity = res.getEntity();
            String resStr = EntityUtils.toString(entity);

            if (res == null) {
                //"Could not fetch the responce"
            } else {

                JSONObject jObj = new JSONObject(resStr);
                source = jObj.getString("description");
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            source="";
        }
        return source;
    }

    public static void main(String[] args) {
        UrlParser urlParser = new UrlParser();
        urlParser.getDescription("433962050099416");
    }
}
