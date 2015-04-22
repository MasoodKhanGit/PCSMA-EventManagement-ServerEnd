package com.masood.facebookevents;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Masood on 4/22/2015.
 */
public class smallSnippets {

    public static void main(String[] args) {
        java.util.Date date= new java.util.Date();
        Timestamp currentTimestamp = new Timestamp(date.getTime());
        String formattedDateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(currentTimestamp);
        System.out.println(formattedDateTime);
    }
}
