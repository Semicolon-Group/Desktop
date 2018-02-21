/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.sql.Timestamp;
import models.Enumerations.SignalReason;
import services.MemberService;

/**
 *
 * @author Elyes
 */
public class Signal {
    private int id;
    private int senderId;
    private int receiverId;
    private SignalReason reason;
    private boolean state;
    private Timestamp date;
    private String content;

    public Signal() {
    }
    
    public Signal(int id){
        this.id = id;
    }

    public Signal(SignalReason reason, String content) {
        this.reason = reason;
        this.content= content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
    public Signal(int senderId, int receiverId, SignalReason reason, boolean state, Timestamp date) {
	this.senderId = senderId;
	this.receiverId = receiverId;
	this.reason = reason;
	this.state = state;
	this.date = date;
    }

    public Signal(int id, int senderId, int receiverId, SignalReason reason, boolean state, Timestamp date) {
	this.id = id;
	this.senderId = senderId;
	this.receiverId = receiverId;
	this.reason = reason;
	this.state = state;
	this.date = date;
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

    public int getReceiverId() {
	return receiverId;
    }

    public void setReceiver(int receiverId) {
	this.receiverId = receiverId;
    }

    public SignalReason getReason() {
	return reason;
    }

    public void setReason(SignalReason reason) {
	this.reason = reason;
    }

    public boolean isState() {
	return state;
    }

    public void setState(boolean state) {
	this.state = state;
    }

    public Timestamp getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
    }
    public String getSenderName() throws SQLException{
        return MemberService.getInstance().get(new Member(senderId)).getFirstname() +" "+MemberService.getInstance().get(new Member(senderId)).getLastname();
    }
    public String getReceiverName() throws SQLException{
        return MemberService.getInstance().get(new Member(receiverId)).getFirstname()+" "+MemberService.getInstance().get(new Member(receiverId)).getLastname();
    }
    public String getStateName(){
        return state ? "Consulted" : "Non-Consulted";
    }
    
    @Override
    public String toString() {
	return "Signal{" + "id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", reason=" + reason + ", state=" + state + ", date=" + date + '}';
    }
    
}
