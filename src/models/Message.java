/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Elyes
 */
public class Message {
    private int id;
    private String content;
    private Date date;
    private User sender;
    private User receiver;
    private boolean seen;
    private Date seenDate;
    private Conversation conversation;

    public Message() {
    }

    public Message(int id, String content, Date date, User sender, User receiver, boolean seen, Date seenDate, Conversation conversation) {
	this.id = id;
	this.content = content;
	this.date = date;
	this.sender = sender;
	this.receiver = receiver;
	this.seen = seen;
	this.seenDate = seenDate;
	this.conversation = conversation;
    }

    public Message(String content, Date date, User sender, User receiver, boolean seen, Date seenDate, Conversation conversation) {
	this.content = content;
	this.date = date;
	this.sender = sender;
	this.receiver = receiver;
	this.seen = seen;
	this.seenDate = seenDate;
	this.conversation = conversation;
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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
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

    public boolean isSeen() {
	return seen;
    }

    public void setSeen(boolean seen) {
	this.seen = seen;
    }

    public Date getSeenDate() {
	return seenDate;
    }

    public void setSeenDate(Date seenDate) {
	this.seenDate = seenDate;
    }

    public Conversation getConversation() {
	return conversation;
    }

    public void setConversation(Conversation conversation) {
	this.conversation = conversation;
    }
    
}
