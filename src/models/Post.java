/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elyes
 */
public abstract class Post {
    private int id;
    private int ownerId;
    private Timestamp date;
    private List<Reaction> reactions;

    public Post() {
        reactions = new ArrayList<>();
    }
    
    public Post(int id){
        this.id = id;
        reactions = new ArrayList<>();
    }

    public Post(int ownerId, Timestamp date) {
	this.ownerId = ownerId;
	this.date = date;
        reactions = new ArrayList<>();
    }

    public Post(int id, int ownerId, Timestamp date) {
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }
    
}
