package com.masood.controller;

import com.masood.model.Events;
import com.masood.model.UserResponse;
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
public class JdbcUserResponseDAO implements UserResponseDAO{
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
    public void insert(UserResponse userResponse) {


        String sql = "INSERT INTO user_response " +
                "(user_id, event_id, response) " +
                "VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, userResponse.getUser_id());
            ps.setString(2,userResponse.getEvent_id());
            ps.setString(3, userResponse.getResponseType());

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


    public void updateResponse(UserResponse userResponse) {
        String sql = "update user_response set response = ? where user_id = ? and event_id = ?;";

        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
//            System.out.println(userResponse.getResponseType());

            ps.setString(1, userResponse.getResponseType());
            ps.setString(2, userResponse.getUser_id());
            ps.setString(3, userResponse.getEvent_id());

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
    public ArrayList<Events> findEventsAttendingByUserId(String user_id) {
        String sql = "select e.* from events e, user_response u where e.event_id = u.event_id  and u.user_id = ?;";

        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_id);

            ArrayList<Events> eventsList = new ArrayList<Events>();
            Events event = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                event = new Events(
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

                eventsList.add(event);
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
    @Override
    public UserResponse findByUserIdandEventId(String user_id, String event_id){
        String sql = "select * from user_response where user_id = ? and event_id = ?;";
        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_id);
            ps.setString(2, event_id);
            UserResponse userResponse = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userResponse = new UserResponse(
                        rs.getString("user_id"),
                        rs.getString("event_id"),
                        rs.getString("response")
                );
            }
            rs.close();
            ps.close();

            return userResponse;
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
    public Events findEventDescriptionByUserAndEventId(String user_id, String event_id){
        JdbcEventsDAO jdbcEventsDAO = new JdbcEventsDAO();
        Events event = jdbcEventsDAO.findByEventId(event_id);
        try{
            UserResponse userResponse= this.findByUserIdandEventId(user_id, event_id);
            event.setResponse(userResponse.getResponseType());
        } catch (Exception ex){
            event.setResponse("");
        }
        return event;
    }
}
