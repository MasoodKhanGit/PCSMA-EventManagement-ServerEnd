package com.masood.controller;

import com.masood.model.Events;
import com.masood.model.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Masood on 4/18/2015.
 */



public class JdbcUserDAO implements UserDAO{
    private DataSource dataSource;

    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/publicevents");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        return dataSource;
    }

    @Override
    public void insert(User user) {


        String sql = "INSERT INTO user " +
                "(USER_ID,NAME,DURATION, FOOBAR, BYLD, MADTOES, ELECTROHOLICS, GAME_CRAFT, SEMINAR, allcat) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

//        String sql = "insert into user "
        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUser_id());
            ps.setString(2, user.getName());
            ps.setString(3, user.getDuration());
            ps.setInt(4, user.getFoobar());
            ps.setInt(5, user.getByld());
            ps.setInt(6, user.getMadtoes());
            ps.setInt(7, user.getElectroholics());
            ps.setInt(8, user.getGame_craft());
            ps.setInt(9, user.getSeminar());
            ps.setInt(10, user.getallcat());


            ps.executeUpdate();
            ps.close();

        } catch (SQLException exp) {
            throw new RuntimeException(exp);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException f) {}
            }
        }

    }
    @Override
    public User findByUserId(String user_id){

        String sql = "SELECT * FROM user WHERE user_id = ?";

        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_id);
            User newUser = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                newUser = new User(
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("duration"),
                        rs.getInt("foobar"),
                        rs.getInt("byld"),
                        rs.getInt("madtoes"),
                        rs.getInt("electroholics"),
                        rs.getInt("game_craft"),
                        rs.getInt("seminar"),
                        rs.getInt("allcat")
                );
            }
            rs.close();
            ps.close();
            return newUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    @Override
    public ArrayList<Events> findEventsSettingsbyUserId(String user_id){

        String sql = "SELECT e.* from events e, user u where  u.user_id = ? and (   (e.foobar = u.foobar and u.foobar = 1 )  or (e.byld = u.byld and u.byld =1) or (e.madtoes = u.madtoes and u.madtoes=1) or (e.electroholics = u.electroholics and u.electroholics =1) or (e.seminar = u.seminar and u.seminar=1) or (e.game_craft = u.game_craft and u.game_craft=1) or (e.allcat = u.allcat and u.allcat=1)    and (  e.start_time >NOW() and (e.start_time < (SELECT ADDTIME(NOW(), u.duration )))))";

        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_id);

            ArrayList<Events> eventsList = new ArrayList<Events>();
            Events newEvent = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                newEvent = new Events(
                        rs.getString("event_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("owner_name"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("cover_url"),
                        rs.getString("venue"),
                        rs.getString("date"),
                        rs.getString("latitude"),
                        rs.getString("longitude"),

                        rs.getInt("foobar"),
                        rs.getInt("byld"),
                        rs.getInt("madtoes"),
                        rs.getInt("electroholics"),
                        rs.getInt("game_craft"),
                        rs.getInt("seminar"),
                        rs.getInt("allcat"),

                        rs.getString("response")
                );

                eventsList.add(newEvent);
            }
            rs.close();
            ps.close();
            return eventsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }


    public void updateSettings(User user){


        String sql = "update user set duration = ?, foobar = ?, byld = ?, madtoes = ?, electroholics = ?, game_craft = ?, seminar = ?, allcat = ? where user_id = ?";

        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getDuration());
            ps.setInt(2, user.getFoobar());
            ps.setInt(3, user.getByld());
            ps.setInt(4, user.getMadtoes());
            ps.setInt(5, user.getElectroholics());
            ps.setInt(6, user.getGame_craft());
            ps.setInt(7, user.getSeminar());
            ps.setInt(8, user.getallcat());
            ps.setString(9, user.getUser_id());


            ps.executeUpdate();
            ps.close();

        } catch (SQLException exp) {
            throw new RuntimeException(exp);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException f) {}
            }
        }



    }
}





