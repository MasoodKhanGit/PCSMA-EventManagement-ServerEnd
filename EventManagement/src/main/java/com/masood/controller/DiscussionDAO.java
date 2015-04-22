package com.masood.controller;

import com.masood.model.Discussion;

import java.util.ArrayList;

/**
 * Created by Masood on 4/18/2015.
 */
public interface DiscussionDAO {
    public void insert(Discussion discussion);
    public ArrayList<Discussion> findDiscussionByEventId(String event_id);

}
