/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.sql.Timestamp;
import services.MemberService;


/**
 *
 * @author Elyes
 */
public class Feedback {
    private int id;
    private int senderId;
    private String content;
    private boolean state;
    private Timestamp date;
    
    public Feedback() {
    }
    
    public Feedback(int id){
        this.id = id;
    }

    public Feedback(String content) {
        this.content = content;
    }

    public Feedback(String content, boolean state) {
        this.content = content;
        this.state = state;
    }

    public Feedback(int id, int senderId, String content, boolean state, Timestamp date) {
	this.id = id;
	this.senderId = senderId;
	this.content = content;
	this.state = state;
        this.date = date;
    }

    public Feedback(int senderId, String content, boolean state, Timestamp date) {
	this.senderId = senderId;
	this.content = content;
	this.state = state;
        this.date = date;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getSenderName() throws SQLException{
        return MemberService.getInstance().get(new Member(senderId)).getFirstname();
    }
    
    public int getSenderId() {
	return senderId;
    }

    public void setSenderId(int senderId) {
	this.senderId = senderId;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public boolean isState() {
	return state;
    }
    
    public String getStateName(){
        return state ? "Treated" : "Non-Treated";
    }
    
    public void setState(boolean state) {
	this.state = state;
    }
    
    public Timestamp getDate(){
        return this.date;
    }
    
    public void setDate(Timestamp date){
        this.date = date;
    }
    
}
