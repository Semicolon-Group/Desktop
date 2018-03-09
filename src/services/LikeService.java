/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Like;

/**
 *
 * @author Elyes
 */
public class LikeService extends Service implements Create<Like>,Delete<Like>,Read<Like>{

    private static LikeService likeService;
    
    private LikeService(){
        super();
    }
    
    public static LikeService getInstance(){
        if(likeService == null){
            return likeService = new LikeService();
        }
        return likeService;
    }

    @Override
    public Like create(Like obj) throws SQLException {
        String query = "insert into user_like values(?,?,?)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        preparedStatement.setInt(1, obj.getSenderId());
        preparedStatement.setInt(2, obj.getReceiverId());
        preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
        preparedStatement.executeUpdate();
        return obj;
    }

    @Override
    public void delete(Like obj) throws SQLException {
        String query = "delete from user_like where ";
        if (obj.getSenderId() != 0 && obj.getReceiverId() != 0) {
            query += "like_sender_id = " + obj.getSenderId() + " and like_receiver_id = " + obj.getReceiverId();
        } else if (obj.getSenderId() != 0) {
            query += "like_sender_id = " + obj.getSenderId();
        } else if (obj.getReceiverId() != 0) {
            query += "like_receiver_id = " + obj.getReceiverId();
        } else {
            return;
        }
        CONNECTION.createStatement().executeUpdate(query);
    }

    @Override
    public Like get(Like obj) throws SQLException {
         String query = "select * from user_like where like_sender_id = " + obj.getSenderId() + " and like_receiver_id = "
                + obj.getReceiverId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        if(!rs.next()) return null;
        obj.setSenderId(rs.getInt("like_sender_id"));
        obj.setReceiverId(rs.getInt("like_receiver_id"));
        obj.setDate(rs.getTimestamp("date"));
        return obj;
    }

    @Override
    public List<Like> getAll(Like obj) throws SQLException {
        String query = "select * from user_like";
        if (obj.getSenderId() != 0 && obj.getReceiverId() != 0) {
            query += " where like_sender_id = " + obj.getSenderId() + " and like_receiver_id = " + obj.getReceiverId();
        } else if (obj.getSenderId() != 0) {
            query += " where like_sender_id = " + obj.getSenderId();
        } else if (obj.getReceiverId() != 0) {
            query += " where like_receiver_id = " + obj.getReceiverId();
        }
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Like> likes = new ArrayList<>();
        while (rs.next()) {
            likes.add(new Like(rs.getInt("like_sender_id"), rs.getInt("like_receiver_id"), rs.getTimestamp("date")));
        }
        return likes;
    }
}
