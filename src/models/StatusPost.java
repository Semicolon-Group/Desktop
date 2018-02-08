/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;



/**
 *
 * @author Elyes
 */
public class StatusPost extends Post{
    private String Content;

    public StatusPost() {
    }
    
    public StatusPost(int id){
        super(id);
    }

    public StatusPost(String Content, int ownerId, Date date) {
        super(ownerId, date);
        this.Content = Content;
    }

    public StatusPost(String Content, int id, Member owner, Date date) {
        super(id, owner, date);
        this.Content = Content;
    }

    
    
    public String getContent() {
	return Content;
    }

    public void setContent(String Content) {
	this.Content = Content;
    }
    
}
