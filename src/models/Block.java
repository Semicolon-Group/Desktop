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
public class Block {
    private int id;
    private Member sender;
    private Member receiver;
    private Date date;
    
    public Block() {
    }

    public Block(Member sender, Member receiver, Date date) {
	this.sender = sender;
	this.receiver = receiver;
	this.date = date;
    }

    public Block(int id, Member sender, Member receiver, Date date) {
	this.id = id;
	this.sender = sender;
	this.receiver = receiver;
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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }
}
