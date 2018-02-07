/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;
import models.Conversation;

/**
 *
 * @author Elyes
 */
public class ConversationService implements Create<Conversation>,Update<Conversation>,Read<Conversation>{

    @Override
    public boolean create(Conversation object) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Conversation object) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Conversation get(Conversation obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Conversation> getAll(Conversation obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
