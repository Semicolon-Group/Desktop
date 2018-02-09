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
    
    public Admin(int id){
        super(id);
    }

    public Admin(int id, String pseudo, String firstname, String lastname, String email, String password, String ip, int port) {
	super(id, pseudo, firstname, lastname, email, password, ip, port);
    }

    public Admin(String pseudo, String firstname, String lastname, String email, String password, String ip, int port) {
	super(pseudo, firstname, lastname, email, password, ip, port);
    }

    
    
}
