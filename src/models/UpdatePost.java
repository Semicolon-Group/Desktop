/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;


/**
 *
 * @author Elyes
 */
public class UpdatePost extends Post{

    public UpdatePost() {
    }

    public UpdatePost(int id) {
        super(id);
    }

    public UpdatePost(int ownerId, Timestamp date) {
        super(ownerId, date);
    }

    public UpdatePost(int id, Member owner, Timestamp date) {
        super(id, owner, date);
    }
}
