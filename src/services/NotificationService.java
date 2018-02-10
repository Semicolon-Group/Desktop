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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import models.Notification;

/**
 *
 * @author Elyes
 */
public class NotificationService extends Service implements Create<Notification>,Update<Notification>,Read<Notification>{

    private static NotificationService notificationService;
    
    public NotificationService(){
        super();
    }
    
    public static NotificationService getInstance(){
        if(notificationService == null){
            return notificationService = new NotificationService();
        }
        return notificationService;
    }

    @Override
    public Notification create(Notification obj) throws SQLException {
        String query = "INSERT INTO `notification`(`content`, `date`, `icon`, `sender_id`, `receiver_id`, `photo_id`, `answer_id`, `type`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        
        preparedStatement.setString(1, obj.getContent());
        preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
        preparedStatement.setString(3, obj.getIcon());
        preparedStatement.setInt(4, obj.getSenderId());
        preparedStatement.setInt(5, obj.getReceiverId());
        preparedStatement.setInt(6, obj.getPhotoId());
        preparedStatement.setInt(7, obj.getAnswerId());
        preparedStatement.setInt(8, obj.getType().ordinal());     
        preparedStatement.executeUpdate();
        return obj;
        
	   }

    @Override
    public void update(Notification obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Notification get(Notification obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notification> getAll(Notification obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
