/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.TreeSet;

/**
 *
 * @author Elyes
 */
public class User {
    private int id;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private TreeSet<Conversation> conversations;
    private TreeSet<Notification> notifications;

    public User() {
    }

    public User(int id, String pseudo, String nom, String prenom, String email, String password) {
	this.id = id;
	this.pseudo = pseudo;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.password = password;
    }

    public User(String pseudo, String nom, String prenom, String email, String password) {
	this.pseudo = pseudo;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.password = password;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getPseudo() {
	return pseudo;
    }

    public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public TreeSet<Conversation> getConversations() {
	return conversations;
    }

    public void setConversations(TreeSet<Conversation> conversations) {
	this.conversations = conversations;
    }

    public TreeSet<Notification> getNotifications() {
	return notifications;
    }

    public void setNotifications(TreeSet<Notification> notifications) {
	this.notifications = notifications;
    }
    
}
