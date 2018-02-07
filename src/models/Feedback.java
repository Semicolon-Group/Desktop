/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Elyes
 */
class Feedback {
    private int id;
    private Member sender;
    private String content;
    private boolean state;

    public Feedback() {
    }

    public Feedback(int id, Member sender, String content, boolean state) {
	this.id = id;
	this.sender = sender;
	this.content = content;
	this.state = state;
    }

    public Feedback(Member sender, String content, boolean state) {
	this.sender = sender;
	this.content = content;
	this.state = state;
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

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public boolean isState() {
	return state;
    }

    public void setState(boolean state) {
	this.state = state;
    }
    
}
