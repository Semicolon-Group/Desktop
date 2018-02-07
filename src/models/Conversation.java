/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.TreeSet;

/**
 *
 * @author Elyes
 */
public class Conversation {
    private int id;
    private String label;
    private boolean seen;
    private Date seenDate;
    private TreeSet<Message> messages;
    private User person1;
    private User person2;

    public Conversation() {
    }

    public Conversation(int id, String label, boolean seen, Date seenDate, TreeSet<Message> messages, User person1, User person2) {
	this.id = id;
	this.label = label;
	this.seen = seen;
	this.seenDate = seenDate;
	this.messages = messages;
	this.person1 = person1;
	this.person2 = person2;
    }

    public Conversation(String label, boolean seen, Date seenDate, TreeSet<Message> messages, User person1, User person2) {
	this.label = label;
	this.seen = seen;
	this.seenDate = seenDate;
	this.messages = messages;
	this.person1 = person1;
	this.person2 = person2;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
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

    public TreeSet<Message> getMessages() {
	return messages;
    }

    public void setMessages(TreeSet<Message> messages) {
	this.messages = messages;
    }

    public User getPerson1() {
	return person1;
    }

    public void setPerson1(User person1) {
	this.person1 = person1;
    }

    public User getPerson2() {
	return person2;
    }

    public void setPerson2(User person2) {
	this.person2 = person2;
    }
    
}
