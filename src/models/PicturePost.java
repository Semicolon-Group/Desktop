/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.TreeSet;

/**
 *
 * @author Elyes
 */
public class PicturePost extends Post{
    private TreeSet<String> urls;

    public PicturePost() {
    }

    public PicturePost(TreeSet<String> urls, Member owner, Date date, Enumerations.Reaction reaction) {
	super(owner, date, reaction);
	this.urls = urls;
    }

    public PicturePost(TreeSet<String> urls, int id, Member owner, Date date, Enumerations.Reaction reaction) {
	super(id, owner, date, reaction);
	this.urls = urls;
    }

    public TreeSet<String> getUrls() {
	return urls;
    }

    public void setUrls(TreeSet<String> urls) {
	this.urls = urls;
    }
    
}
