package com.masood.model;

/**
 * Created by Masood on 4/21/2015.
 */
public class Rsvp{

        String event_id, response;

public Rsvp(String event_id, String response) {
        this.event_id = event_id;
        this.response = response;
        }


    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}
