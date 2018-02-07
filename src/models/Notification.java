/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import models.Enumerations.NotificationType;

/**
 *
 * @author Elyes
 */
class Notification {
    private int id;
    private User sender;
    private User receiver;
    private NotificationType type;
    private String content;
    private Date date;
    private String icon;

    public Notification() {
    }

    public Notification(int id, User sender, User receiver, NotificationType type, String content, Date date, String icon) {
	this.id = id;
	this.sender = sender;
	this.receiver = receiver;
	this.type = type;
	this.content = content;
	this.date = date;
	this.icon = icon;
    }

    public Notification(User sender, User receiver, NotificationType type, String content, Date date, String icon) {
	this.sender = sender;
	this.receiver = receiver;
	this.type = type;
	this.content = content;
	this.date = date;
	this.icon = icon;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public User getSender() {
	return sender;
    }

    public void setSender(User sender) {
	this.sender = sender;
    }

    public User getReceiver() {
	return receiver;
    }

    public void setReceiver(User receiver) {
	this.receiver = receiver;
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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public String getIcon() {
	return icon;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }
    
}
