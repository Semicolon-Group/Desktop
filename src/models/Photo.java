/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import models.Enumerations.PhotoType;

/**
 *
 * @author Elyes
 */
public class Photo {
    private int id;
    private int userId;
    private String url;
    private Timestamp date;
    private PhotoType type;

    public Photo() {
    }

    public Photo(int id, int userId, String url, Timestamp date, PhotoType type) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.date = date;
        this.type = type;
    }

    public Photo(int userId, Timestamp date) {
        this.userId = userId;
        this.date = date;
    }

    public Photo(int userId, PhotoType type) {
        this.userId = userId;
        this.type = type;
    }
    

    public Photo(int userId) {
        this.userId = userId;
    }

    public PhotoType getType() {
        return type;
    }

    public void setProfile(PhotoType type) {
        this.type = type;
    }

        public Photo(int id, int userId, String url) {
	this.id = id;
	this.userId = userId;
	this.url = url;
	
    }
    
    
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public Timestamp getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
    }
    
    
}
