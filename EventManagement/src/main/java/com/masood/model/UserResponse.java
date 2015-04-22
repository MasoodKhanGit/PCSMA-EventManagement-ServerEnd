package com.masood.model;

/**
 * Created by Masood on 4/18/2015.
 */

public class UserResponse {
    public String user_id;
    public String event_id;
    public String response;

    public UserResponse(String user_id, String event_id, String response) {
        this.user_id = user_id;
        this.event_id = event_id;
        this.response = response;
    }

    public UserResponse() {

    }

    public String getUser_id() {
        return user_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getResponseType() { return response; }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setResponseType(String response) {
        this.response = response;
    }
}
