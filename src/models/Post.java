/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import models.Enumerations.Reaction;

/**
 *
 * @author Elyes
 */
class Post {
    private int id;
    private Member owner;
    private Date date;
    private Reaction reaction;

    public Post() {
    }

    public Post(Member owner, Date date, Reaction reaction) {
	this.owner = owner;
	this.date = date;
	this.reaction = reaction;
    }

    public Post(int id, Member owner, Date date, Reaction reaction) {
	this.id = id;
	this.owner = owner;
	this.date = date;
	this.reaction = reaction;
    }
    
}
