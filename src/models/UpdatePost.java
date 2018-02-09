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
public class UpdatePost extends Post{

    public UpdatePost() {
    }

    public UpdatePost(int id) {
        super(id);
    }

    public UpdatePost(int ownerId, java.sql.Date date) {
        super(ownerId, date);
    }

    public UpdatePost(int id, Member owner, java.sql.Date date) {
        super(id, owner, date);
    }
}
