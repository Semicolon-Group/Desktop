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
public class Admin extends User{

    public Admin() {
    }

    public Admin(int id, String pseudo, String nom, String prenom, String email, String password) {
	super(id, pseudo, nom, prenom, email, password);
    }

    public Admin(String pseudo, String nom, String prenom, String email, String password) {
	super(pseudo, nom, prenom, email, password);
    }
    
}
