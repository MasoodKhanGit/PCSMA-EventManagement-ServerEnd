package com.masood.facebookevents;


import com.restfb.*;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Event;
import com.restfb.types.Page.Cover;
import com.restfb.types.Venue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Masood
 */
public class temp {

    static String accToken = "CAACEdEose0cBAOmkg5EmGdSN624z1NE0HDTtVUYOP9kvJ7qLto10iFrYEOrDAexgh0R30MUE56WV152S33wtLYUmPZADvCJ1yXdK9cWqacXNadw08h2LavYQtoAVcvF99HoM9FcnNrb8gIbMtWQE6CAETl3veKjXpDVweghHAY6dyFCz1cAcP0H8YtArtNx5ZAlEZCWHXUI0xObiJZC3";
    static String id = "793320830678456";
    static String appid = "436784909811317";
    static String appSecretKey = "6a1c220f1a2809631115dcfc3dff74d3";

    private FacebookClient.AccessToken getFacebookUserToken(String code, String redirectUrl) throws IOException {
        String appId = "YOUR_APP_ID";
        String secretKey = "YOUR_SECRET_KEY";

        WebRequestor wr = new DefaultWebRequestor();
        WebRequestor.Response accessTokenResponse = wr.executeGet(
                "https://graph.facebook.com/oauth/access_token?client_id=" + appId + "&redirect_uri=" + redirectUrl
                        + "&client_secret=" + secretKey + "&code=" + code);

        return DefaultFacebookClient.AccessToken.fromQueryString(accessTokenResponse.getBody());
    }

    private static void genAccessToken() {

        AccessToken accessToken
                = new DefaultFacebookClient().obtainExtendedAccessToken(appid, appSecretKey, accToken);

        System.out.println("My extended access token: " + accessToken);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        int count = 1;
//        genAccessToken();
//        while (true) {

//            System.out.println("Count:" + count++);
        System.out.println("Retrieving Data:");
        FacebookClient publicfacebookClient = new DefaultFacebookClient(accToken, Version.VERSION_2_2);
        Connection<Event> publicSearch
                = publicfacebookClient.fetchConnection("search", Event.class,
                Parameter.with("q", "IIIT Delhi"), Parameter.with("type", "event"));

        List<Event> events = publicSearch.getData();
        System.out.println("public event search: " + events);

//        String name = events.get(0).getPicture()
        Venue venue = events.get(0).getVenue();
        String description = events.get(0).getDescription();
        Date date = events.get(0).getEndTime();
        String id = events.get(0).getId();


        Connection<Cover> cover = publicfacebookClient.fetchConnection(id, Cover.class);
        System.out.println(cover.getData().get(0).getSource());

//        Connection<Cover> coverPicture
//                = publicfacebookClient.fetchConnection("search", Cover.class,
//                        Parameter.with("q", id), Parameter.with("type", "cover"));
//        String coverUrl = coverPicture.getData().get(0).getSource();
//        System.out.println(coverUrl);

        File file = new File("dataR"
                + "eceived.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(publicSearch.toString());
        bw.close();

//            Thread.sleep(10000);//10seconds
//        }
    }

}
