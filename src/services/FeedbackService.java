/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Feedback;

/**
 *
 * @author Elyes
 */
public class FeedbackService extends Service implements Create<Feedback>,Update<Feedback>,Read<Feedback>{

    private static FeedbackService feedbackService;
    
    private FeedbackService(){
        super();
    }
    
    public static FeedbackService getInstance(){
        if(feedbackService == null){
            return feedbackService = new FeedbackService();
        }
        return feedbackService;
    }

    @Override
    public Feedback create(Feedback obj) throws SQLException {
        String query = "insert into feedback ( content, state, sender_id, date) values(?,?,?,?)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        preparedStatement.setString(1, obj.getContent());
        preparedStatement.setBoolean(2, false);
        preparedStatement.setInt(3, 1);
        preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
        preparedStatement.executeUpdate();
        return obj;
    
    }

    @Override
    public void update(Feedback obj) throws SQLException {
        String query = "UPDATE feedback SET state = ?  WHERE id = ?";
        PreparedStatement pst = CONNECTION.prepareStatement(query);
        pst.setBoolean(1, true);
        pst.setInt(2, obj.getId());
        pst.executeUpdate();
    }

    @Override
    public Feedback get(Feedback obj) throws SQLException {
        String query = "select * from feedback where id = " + obj.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        if(rs.next()){
            obj.setId(rs.getInt("id"));
            obj.setContent(rs.getString("content"));
            obj.setState(rs.getBoolean("state"));
            obj.setSenderId(rs.getInt("sender_id"));
            obj.setDate(rs.getTimestamp("date"));
            return obj;
        }
	return null;
    }

    @Override
    public List<Feedback> getAll(Feedback obj) throws SQLException {
        String query = "select * from feedback";
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Feedback> feeds = new ArrayList<>();
        while(rs.next()){
            Feedback feed = new Feedback();
	    feed.setId(rs.getInt("id"));
            feed.setContent(rs.getString("content"));
            feed.setState(rs.getBoolean("state"));
            feed.setSenderId(rs.getInt("sender_id"));
            feed.setDate(rs.getTimestamp("date"));
            feeds.add(feed);
        }
        return feeds;
        
    }



 public List<Feedback> getFalse(Feedback obj) throws SQLException {
        String query = "select * from feedback where state = 0";
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Feedback> feeds = new ArrayList<>();
        while(rs.next()){
            Feedback feed = new Feedback();
	    feed.setId(rs.getInt("id"));
            feed.setContent(rs.getString("content"));
            feed.setState(rs.getBoolean("state"));
            feed.setSenderId(rs.getInt("sender_id"));
            feed.setDate(rs.getTimestamp("date"));
            feeds.add(feed);
        }
        return feeds;
        
    }



    
}
