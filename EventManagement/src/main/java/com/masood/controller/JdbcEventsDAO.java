package com.masood.controller;

import com.masood.model.Events;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Masood on 4/18/2015.
 */
public class JdbcEventsDAO implements EventsDAO{
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
    public void insert(Events events){
        String sql = "INSERT INTO events " +
                "(event_id, name, description, owner_name, start_time, end_time, cover_url , venue, date, latitude, longitude, FOOBAR, BYLD, MADTOES, electroholics, game_craft, SEMINAR, allcat, response) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, events.getEvent_id());
            ps.setString(2,events.getName());
            ps.setString(3,events.getDescription());
            ps.setString(4, events.getOwner_name());
            ps.setString(5, events.getStart_time());
            ps.setString(6, events.getEnd_time());
            ps.setString(7, events.getCover_url());
            ps.setString(8, events.getVenue());
            ps.setString(9, events.getDate());
            ps.setString(10, events.getLatitude());
            ps.setString(11, events.getLongitude());

            ps.setInt(12, events.getFoobar());
            ps.setInt(13, events.getByld());
            ps.setInt(14, events.getMadtoes());
            ps.setInt(15, events.getElectroholics());
            ps.setInt(16, events.getGame_craft());
            ps.setInt(17, events.getSeminar());
            ps.setInt(18, events.getallcat());

            ps.setString(19, events.getResponse());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException f) {}
            }
        }
    }

    @Override
    public Events findByEventId(String event_id){

        String sql = "SELECT * FROM events WHERE event_id = ?";
        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, event_id);

            Events event = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
            }


            rs.close();
            ps.close();
            return event;

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

    public ArrayList<Events> findEventsByDate(String event_date){
        String sql = "SELECT * FROM events WHERE ? = DATE(date) ";
        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, event_date);

            Events event = null;
            ArrayList<Events> eventsList = new ArrayList<Events>();
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
                } catch (SQLException e) {}
            }
        }
    }


}
