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
public class StatusPost extends Post{
    private String Content;

    public StatusPost() {
    }

    public StatusPost(String Content, Member owner, Date date, Enumerations.Reaction reaction) {
	super(owner, date, reaction);
	this.Content = Content;
    }

    public StatusPost(String Content, int id, Member owner, Date date, Enumerations.Reaction reaction) {
	super(id, owner, date, reaction);
	this.Content = Content;
    }

    public String getContent() {
	return Content;
    }

    public void setContent(String Content) {
	this.Content = Content;
    }
    
}
