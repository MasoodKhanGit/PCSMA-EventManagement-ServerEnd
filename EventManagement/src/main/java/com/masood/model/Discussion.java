package com.masood.model;

import java.sql.Date;

/**
 * Created by Masood on 4/18/2015.
 */
public class Discussion {
    public String event_id;
    public String user_name;
    public String description;
    public String date;

    public Discussion(String event_id, String user_name, String description, String date) {
        this.event_id = event_id;
        this.user_name = user_name;
        this.description = description;
        this.date = date;
    }



    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getEvent_id() {
        return event_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
