/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import models.Enumerations.SignalReason;

/**
 *
 * @author Elyes
 */
public class Signal {
    private int id;
    private Member sender;
    private Member receiver;
    private SignalReason reason;
    private boolean state;
    private Date date;

    public Signal() {
    }

    public Signal(Member sender, Member receiver, SignalReason reason, boolean state, Date date) {
	this.sender = sender;
	this.receiver = receiver;
	this.reason = reason;
	this.state = state;
	this.date = date;
    }

    public Signal(int id, Member sender, Member receiver, SignalReason reason, boolean state, Date date) {
	this.id = id;
	this.sender = sender;
	this.receiver = receiver;
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

    public Member getSender() {
	return sender;
    }

    public void setSender(Member sender) {
	this.sender = sender;
    }

    public Member getReceiver() {
	return receiver;
    }

    public void setReceiver(Member receiver) {
	this.receiver = receiver;
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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }
    
}
