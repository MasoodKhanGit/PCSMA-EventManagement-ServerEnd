package com.masood.controller;

import com.masood.model.Discussion;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Masood on 4/18/2015.
 */

public class JdbcDiscussionDAO implements DiscussionDAO {
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
    public void insert(Discussion discussion) {
        String sql = "INSERT INTO discussion " +
                "(event_id, user_name, description, date) " +
                "VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, discussion.getEvent_id());
            ps.setString(2,discussion.getUser_name());
            ps.setString(3, discussion.getDescription());

            java.util.Date date= new java.util.Date();
            Timestamp currentTimestamp = new Timestamp(date.getTime());
            String formattedDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTimestamp);

            ps.setString(4, formattedDateTime );

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
    public ArrayList<Discussion > findDiscussionByEventId(String event_id) {
        String sql = "SELECT * FROM discussion WHERE event_id = ?";

        Connection conn = null;

        try {
            dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, event_id);

            Discussion discussion = null;
            ArrayList<Discussion> discussionList = new ArrayList<Discussion>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                discussion = new Discussion(
                        rs.getString("event_id"),
                        rs.getString("user_name"),
                        rs.getString("description"),
                        rs.getString("date")
                );
                discussionList.add(discussion);
            }
            rs.close();
            ps.close();
            return discussionList;
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
