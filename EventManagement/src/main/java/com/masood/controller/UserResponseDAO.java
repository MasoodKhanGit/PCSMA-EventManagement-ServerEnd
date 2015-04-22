package com.masood.controller;

import com.masood.model.Events;
import com.masood.model.UserResponse;

import java.util.ArrayList;

/**
 * Created by Masood on 4/18/2015.
 */
public interface UserResponseDAO {
    public void insert(UserResponse userResponse);
    public void updateResponse(UserResponse userResponse);
    public ArrayList<Events> findEventsAttendingByUserId(String user_id);
    public Events findEventDescriptionByUserAndEventId(String user_id, String event_id);
    public UserResponse findByUserIdandEventId(String user_id, String event_id);
}
