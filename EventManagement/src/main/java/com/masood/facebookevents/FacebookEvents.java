package com.masood.facebookevents;


import com.masood.controller.JdbcEventsDAO;
import com.masood.model.Events;
import com.restfb.*;
import com.restfb.types.Event;
import org.joda.time.DateTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import java.sql.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Masood
 */
public class FacebookEvents {

//    static String accToken = "CAACEdEose0cBAGmNjIb5GMaUeLrlO69UsKqMGDc1ZC2o33dDVyZAMlWqxUSJTwXi6DneBjZCSzfs61APwgt2EajknyWJ7XGaCSF5CNlEb7kTCyCMNeUE9Gh2KPZC1DvSoe7pnrfkhgTYAJmyQuO2FNfPnXVegmEg6Y4ZCJIlfGgMLs9jZC8Ni8fvOWaq7RU27ZCR81AmYH5ZCLNQPmHQ7lAN";




    public static String dateConversion(Date incomingdate) throws ParseException {

//        String dateString = "2013-05-30T19:30:00+0300";
//        Sat May 02 10:00:00 IST 2015
//        Sat Jan 25 16:52:28 PST 2014
//        System.out.println(incomingdate);
        DateTime startdate = new DateTime(incomingdate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String temp=startdate.toString();
        temp=temp.substring(0,19)+temp.substring(23,26)+temp.substring(27,29);
        java.util.Date date = dateFormat.parse(temp);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String formatedDate = dateFormat.format(date);
        System.out.println("formated: " + formatedDate);
//        date = dateFormat.parse(formatedDate);

        return formatedDate;

    }

    public static int parseNameDescription(String type, String name, String description){
        if(name.toLowerCase().contains(type.toLowerCase()) || description.toLowerCase().contains(type.toLowerCase())){
            return 1;
        } else{
            return 0;
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        System.out.println("Retrieving Data:");
        FacebookClient publicfacebookClient = new DefaultFacebookClient(UrlParser.accToken, Version.VERSION_2_2);
        Connection<Event> publicSearch
                = publicfacebookClient.fetchConnection("search", Event.class,
                Parameter.with("q", "IIITD"), Parameter.with("type", "event"));

        List<Event> eventsList = publicSearch.getData();
        System.out.println("public event search: " + eventsList);

        int size = eventsList.size();
        int i = 0;
        UrlParser urlParser = new UrlParser();

        while(i < size ){
//            String id = events.get(i).getId();

            String event_id = eventsList.get(i).getId();
            String name = eventsList.get(i).getName();
            String description = urlParser.getDescription(event_id);
            String start_time = dateConversion(eventsList.get(i).getStartTime());
            String end_time = dateConversion(eventsList.get(i).getEndTime());
            String venue = eventsList.get(i).getLocation();


            String owner_name = "";
            String cover_url= urlParser.getUrl(event_id)  ;
            String latitude = "";
            String longitude = "";
            String date = start_time;


            int foobar = parseNameDescription("foobar", name, description);
            int byld = parseNameDescription("byld", name, description);
            int madtoes = parseNameDescription("madtoes", name, description);
            int electroholics = parseNameDescription("electroholics", name, description);;
            int game_craft = parseNameDescription("craft", name, description);;
            int seminar = parseNameDescription("seminar", name, description);;
            int allcat = 1;

            String response = "";

            Events event = new Events(event_id, name, description, owner_name, start_time, end_time, cover_url, venue, date, latitude,
                    longitude, foobar,  byld, madtoes, electroholics, game_craft, seminar, allcat, response);

            JdbcEventsDAO jdbcEventsDAO = new JdbcEventsDAO();
            jdbcEventsDAO.insert(event);
            i++;
        }




//            Thread.sleep(10000);//10seconds
//        }
    }

}
