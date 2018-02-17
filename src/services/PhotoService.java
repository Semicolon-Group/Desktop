/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static controller.GlobalViewController.online;
import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.Member;
import models.Photo;

/**
 *
 * @author Elyes
 */
public class PhotoService extends Service implements Create<Photo>,Read<Photo>,Delete<Photo>{

    private static PhotoService photoService;
    
    private PhotoService(){
        super();
    }
    
    public static PhotoService getInstance(){
        if(photoService == null){
            return photoService = new PhotoService();
        }
        return photoService;
    }
    
    @Override
    public Photo create(Photo obj) throws SQLException {
	String req = "INSERT INTO `photo`(`url`, `user_id`, `date`) VALUES (?,?,?)";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setString(1, obj.getUrl());
	pst.setInt(2, obj.getUserId());
	pst.setTimestamp(3, obj.getDate());
	pst.executeUpdate();
	return obj;
    }

    @Override
    /*
    * Always returns the profile photo for a user.
    */
    public Photo get(Photo obj) throws SQLException {
	String req = "SELECT * FROM `photo` WHERE user_id = " + obj.getUserId() + " and `profile` = 1";
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	if(rs.next()){
            return new Photo(rs.getInt("id"), rs.getInt("user_id"), rs.getString("url"), rs.getTimestamp("date"), true);
        }
	return null;
    }

    @Override
    public List<Photo> getAll(Photo obj) throws SQLException {
	String req = "SELECT * FROM `photo` WHERE `user_id` = ? AND `date` > ? AND `profile` = 0 ORDER BY `date` DESC";
        
        Timestamp date;
        if (obj.getDate() == null)
            date = new Timestamp(new Date(0).getTime());
        else
            date = obj.getDate();
        
        PreparedStatement ps = CONNECTION.prepareStatement(req);
        ps.setInt(1, obj.getUserId());
        ps.setTimestamp(2, date);
	ResultSet rs = ps.executeQuery();
	List<Photo> list = new ArrayList();
	while(rs.next()){
	    list.add(new Photo(rs.getInt("id"), rs.getInt("user_id"), rs.getString("url"),
                    rs.getTimestamp("date"),rs.getBoolean("profile")));
	}
	return list;
    }

    @Override
    public void delete(Photo obj) throws SQLException {
	String req = "DELETE FROM `photo` WHERE id = " + obj.getId();
	CONNECTION.createStatement().executeUpdate(req);
    }
    
}
