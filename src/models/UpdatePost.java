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
public class UpdatePost extends Post{

    public UpdatePost() {
    }

    public UpdatePost(Member owner, Date date, Enumerations.Reaction reaction) {
	super(owner, date, reaction);
    }

    public UpdatePost(int id, Member owner, Date date, Enumerations.Reaction reaction) {
	super(id, owner, date, reaction);
    }
    
}
