package com.masood.controller;

import com.masood.model.Events;

import java.util.ArrayList;

/**
 * Created by Masood on 4/18/2015.
 */
public interface EventsDAO {

    public void insert(Events events);
    public Events findByEventId(String event_id);
    public ArrayList<Events> findEventsByDate(String date);
}
