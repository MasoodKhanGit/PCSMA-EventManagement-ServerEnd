package com.masood.model;

import java.util.Date;

/**
 * Created by Masood on 4/18/2015.
 */
public class Events {

    public String event_id;
    public String name;
    public String description;
    public String owner_name;
    public String start_time;
    public String end_time;
    public String cover_url;
    public String venue;
    public String date;
    public String latitude;
    public String longitude;

    public int foobar;
    public int byld;
    public int madtoes;
    public int electroholics;
    public int game_craft;
    public int seminar;
    public int allcat;

    public String response;

    public Events(String event_id, String name, String description, String owner_name, String start_time, String end_time, String cover_url, String venue, String date, String latitude, String longitude, int foobar, int byld, int madtoes, int electroholics, int game_craft, int seminar, int allcat, String response) {
        this.event_id = event_id;
        this.name = name;
        this.description = description;
        this.owner_name = owner_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.cover_url = cover_url;
        this.venue = venue;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;

        this.foobar = foobar;
        this.byld = byld;
        this.madtoes = madtoes;
        this.electroholics = electroholics;
        this.game_craft = game_craft;
        this.seminar = seminar;
        this.allcat = allcat;

        this.response = response;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setFoobar(int foobar) {
        this.foobar = foobar;
    }

    public void setByld(int byld) {
        this.byld = byld;
    }

    public void setMadtoes(int madtoes) {
        this.madtoes = madtoes;
    }

    public void setElectroholics(int electroholics) {
        this.electroholics = electroholics;
    }

    public void setGame_craft(int game_craft) {
        this.game_craft = game_craft;
    }

    public void setSeminar(int seminar) {
        this.seminar = seminar;
    }

    public void setallcat(int allcat) {
        this.allcat = allcat;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getFoobar() {
        return foobar;
    }

    public int getByld() {
        return byld;
    }

    public int getMadtoes() {
        return madtoes;
    }

    public int getElectroholics() {
        return electroholics;
    }

    public int getGame_craft() {
        return game_craft;
    }

    public int getSeminar() {
        return seminar;
    }

    public int getallcat() {
        return allcat;
    }

    public String getResponse() {
        return response;
    }
}
