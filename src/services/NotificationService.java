/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;
import models.Notification;

/**
 *
 * @author Elyes
 */
public class NotificationService extends Service implements Create<Notification>,Update<Notification>,Read<Notification>{

    private static NotificationService notificationService;
    
    private NotificationService(){
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
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
