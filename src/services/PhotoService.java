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
import java.util.ArrayList;
import java.util.List;
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
    public Photo get(Photo obj) throws SQLException {
	String req = "SELECT * FROM `photo` WHERE id = " + obj.getId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	if(rs.next()){
            return new Photo(rs.getInt("id"), rs.getInt("user_id"), rs.getString("url"), rs.getTimestamp("date"));
        }
	return null;
    }    
    
    
    
    public Photo getuserphoto(int id) throws SQLException {
	String req = "SELECT * FROM `photo` WHERE id = " + id;
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	if(rs.next()){
            return new Photo(rs.getInt("id"), rs.getInt("user_id"), rs.getString("url"));
        }
	return null;
    }
    
    @Override
    public List<Photo> getAll(Photo obj) throws SQLException {
	String req = "SELECT * FROM `photo` WHERE user_id = " + obj.getUserId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	List<Photo> list = new ArrayList();
	while(rs.next()){
	    list.add(new Photo(rs.getInt("id"), rs.getInt("user_id"), rs.getString("url"), rs.getTimestamp("date")));
	}
	return list;
    }
    
    @Override
    public void delete(Photo obj) throws SQLException {
	String req = "DELETE FROM `photo` WHERE id = " + obj.getId();
	CONNECTION.createStatement().executeUpdate(req);
    }
    
}
