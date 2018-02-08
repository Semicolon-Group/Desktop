/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import models.Enumerations.Reaction;

/**
 *
 * @author Elyes
 */
public abstract class Post {
    private int id;
    private int ownerId;
    private Date date;
    private List<Reaction> reactions;

    public Post() {
        reactions = new ArrayList<>();
    }
    
    public Post(int id){
        this.id = id;
        reactions = new ArrayList<>();
    }

    public Post(int ownerId, Date date) {
	this.ownerId = ownerId;
	this.date = date;
        reactions = new ArrayList<>();
    }

    public Post(int id, Member owner, Date date) {
	this.id = id;
	this.ownerId = ownerId;
	this.date = date;
        reactions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }
    
}
