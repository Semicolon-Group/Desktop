/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import models.Enumerations.NotificationType;
import models.Enumerations.PhotoType;
import services.AnswerService;
import services.MemberService;
import services.NotificationService;
import services.PhotoService;

/**
 *
 * @author Elyes
 */
public class Notification {
    private int id;
    private int senderId;
    private int receiverId;
    private NotificationType type;
    private String content;
    private Timestamp date;
    private String icon;
    private int answerId;
    private int photoId;
    private boolean seen;

    public Notification() {
    }
    
    public Notification(int id){
        this.id = id;
    }

    public Notification(int id, int senderId, int receiverId, NotificationType type, String content, Timestamp date, String icon, int answerId, int photoId, boolean seen) {
	this.id = id;
	this.senderId = senderId;
	this.receiverId = receiverId;
	this.type = type;
	this.content = content;
	this.date = date;
	this.icon = icon;
	this.answerId = answerId;
	this.photoId = photoId;
	this.seen = seen;
    }

    public Notification(int senderId, int receiverId, NotificationType type, String content, Timestamp date, String icon, int answerId, int photoId, boolean seen) {
	this.senderId = senderId;
	this.receiverId = receiverId;
	this.type = type;
	this.content = content;
	this.date = date;
	this.icon = icon;
	this.answerId = answerId;
	this.photoId = photoId;
	this.seen = seen;
    }

    public Notification(String content) {
        this.content = content;
    }

    public boolean isSeen() {
	return seen;
    }

    public void setSeen(boolean seen) {
	this.seen = seen;
    }

    

    public int getAnswerId() {
	return answerId;
    }

    public void setAnswerId(int answerId) {
	this.answerId = answerId;
    }

    public int getPhotoId() {
	return photoId;
    }

    public void setPhotoId(int photoId) {
	this.photoId = photoId;
    }

    

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getSenderId() {
	return senderId;
    }

    public void setSenderId(int senderId) {
	this.senderId = senderId;
    }
    
    public String getSenderFName() throws SQLException{
        return MemberService.getInstance().get(new Member(senderId)).getFirstname();
    }
    public String getSenderLName() throws SQLException{
        return MemberService.getInstance().get(new Member(senderId)).getLastname();
    }
    
    public String getReceiverName() throws SQLException{
        return MemberService.getInstance().get(new Member(receiverId)).getLastname();
    }
    
    public String getUrlPhoto() throws SQLException{
        return  PhotoService.getInstance().get(new Photo(0,senderId,null,null,PhotoType.PROFILE)).getUrl();
    }
    
//     public HashSet<Choice> getAnswer() throws SQLException{
//        return  AnswerService.getInstance().get(new Answer()).getSelectedChoices();
//    }
    
    

    public int getReceiverId() {
	return receiverId;
    }

    public void setReceiverId(int receiverId) {
	this.receiverId = receiverId;
    }

    public NotificationType getType() {
	return type;
    }

    public void setType(NotificationType type) {
	this.type = type;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Timestamp getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    @Override
    public String toString() {
	return "Notification{" + "id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", type=" + type + ", content=" + content + ", date=" + date + ", icon=" + icon + ", answerId=" + answerId + ", photoId=" + photoId + ", seen=" + seen + '}';
    }
    
    
}
