package com.masood.model;

/**
 * Created by Masood on 4/18/2015.
 */
public class User {
    public String user_id;
    public String name;
    public String duration;

    public int foobar;
    public int byld;
    public int madtoes;
    public int electroholics;
    public int game_craft;
    public int seminar;
    public int allcat;

    public User(String user_id, String name, String duration, int foobar, int byld, int madtoes, int electroholics, int game_craft, int seminar, int allcat) {
        this.user_id = user_id;
        this.name = name;
        this.duration = duration;
        this.foobar = foobar;
        this.byld = byld;
        this.madtoes = madtoes;
        this.electroholics = electroholics;
        this.game_craft = game_craft;
        this.seminar = seminar;
        this.allcat = allcat;
    }

    public User(){

    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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




    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
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

}
