package com.masood.controller;

import com.masood.model.Events;
import com.masood.model.User;

import java.util.ArrayList;

/**
 * Created by Masood on 4/18/2015.
 */
public interface UserDAO {
    public void insert(User user);
    public User findByUserId(String user_id);
    public ArrayList<Events> findEventsSettingsbyUserId(String user_id);
    public void updateSettings(User user);
}
