/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;


/**
 *
 * @author Elyes
 */
public class Message {
    private int id;
    private String content;
    private Timestamp date;
    private int senderId;
    private int receiverId;
    private boolean seen;
    private Timestamp seenDate;

    public Message() {
    }
    
    public Message(int id){
        this.id = id;
    }

    public Message(int id, String content, Timestamp date, int senderId, int receiverId, boolean seen, Timestamp seenDate) {
	this.id = id;
	this.content = content;
	this.date = date;
	this.senderId = senderId;
	this.receiverId = receiverId;
	this.seen = seen;
	this.seenDate = seenDate;
    }

    public Message(String content, Timestamp date, int senderId, int receiverId, boolean seen, Timestamp seenDate) {
	this.content = content;
	this.date = date;
	this.senderId = senderId;
	this.receiverId = receiverId;
	this.seen = seen;
	this.seenDate = seenDate;
    }

    public Message(int senderId, int receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", content=" + content + ", date=" + date + ", senderId=" + senderId + ", receiverId=" + receiverId + ", seen=" + seen + ", seenDate=" + seenDate + "}\n";
    }
    

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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

    public int getSenderId() {
	return senderId;
    }

    public void setSenderId(int senderId) {
	this.senderId = senderId;
    }

    public int getReceiverId() {
	return receiverId;
    }

    public void setReceiverId(int receiverId) {
	this.receiverId = receiverId;
    }

    public boolean isSeen() {
	return seen;
    }

    public void setSeen(boolean seen) {
	this.seen = seen;
    }

    public Timestamp getSeenDate() {
	return seenDate;
    }

    public void setSeenDate(Timestamp seenDate) {
	this.seenDate = seenDate;
    }

}
