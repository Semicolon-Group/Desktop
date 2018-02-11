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
import models.Enumerations;
import models.Notification;

/**
 *
 * @author Elyes
 */
public class NotificationService extends Service implements Create<Notification>, Update<Notification>, Read<Notification> {

    private static NotificationService notificationService;

    private NotificationService() {
	super();
    }

    public static NotificationService getInstance() {
	if (notificationService == null) {
	    return notificationService = new NotificationService();
	}
	return notificationService;
    }

    @Override
    public Notification create(Notification obj) throws SQLException {
	String query = "INSERT INTO `notification`(`content`, `date`, `icon`, `sender_id`, `receiver_id`, `photo_id`, `answer_id`, `type`, `seen`) VALUES (?,?,?,?,?,?,?,?,?)";
	PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);

	preparedStatement.setString(1, obj.getContent());
	preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
	preparedStatement.setString(3, obj.getIcon());
	preparedStatement.setInt(4, obj.getSenderId());
	preparedStatement.setInt(5, obj.getReceiverId());
	preparedStatement.setInt(6, obj.getPhotoId());
	preparedStatement.setInt(7, obj.getAnswerId());
	preparedStatement.setInt(8, obj.getType().ordinal());
	preparedStatement.setBoolean(9, false);
	preparedStatement.executeUpdate();
	return obj;

    }

    @Override
    public void update(Notification obj) throws SQLException {
	String query = "UPDATE `notification` SET `seen` = 1 WHERE id=?";
	PreparedStatement pst = CONNECTION.prepareStatement(query);
	pst.setInt(1, obj.getId());
	pst.executeUpdate();
    }

    @Override
    public Notification get(Notification obj) throws SQLException {
	String query = "select * from notification where id = " + obj.getId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(query);
	if(rs.next()){
            obj.setId(rs.getInt("id"));
            obj.setContent(rs.getString("content"));
            obj.setDate(rs.getTimestamp("date"));
            obj.setIcon(rs.getString("icon"));
            obj.setSenderId(rs.getInt("sender_id"));
            obj.setReceiverId(rs.getInt("receiver_id"));
            obj.setPhotoId(rs.getInt("photo_id"));
            obj.setAnswerId(rs.getInt("answer_id"));
            obj.setType(Enumerations.NotificationType.values()[rs.getInt("type")]);
            obj.setSeen(rs.getBoolean("seen"));
            return obj;
        }
	return null;
    }

    @Override
    public List<Notification> getAll(Notification obj) throws SQLException {
	String query = "select * from notification WHERE receiver_id = " + obj.getReceiverId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(query);
	List<Notification> ntfs = new ArrayList<>();
	while (rs.next()) {
	    Notification ntf = new Notification();
	    ntf.setId(rs.getInt("id"));
	    ntf.setContent(rs.getString("content"));
	    ntf.setDate(rs.getTimestamp("date"));
	    ntf.setIcon(rs.getString("icon"));
	    ntf.setSenderId(rs.getInt("sender_id"));
	    ntf.setReceiverId(rs.getInt("receiver_id"));
	    ntf.setPhotoId(rs.getInt("photo_id"));
	    ntf.setAnswerId(rs.getInt("answer_id"));
	    ntf.setType(Enumerations.NotificationType.values()[rs.getInt("type")]);
	    ntf.setSeen(rs.getBoolean("seen"));
	    ntfs.add(ntf);
	}
	return ntfs;

    }
}
