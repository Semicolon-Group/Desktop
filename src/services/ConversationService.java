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
public class ConversationService extends Service implements Create<Conversation>,Update<Conversation>,Read<Conversation>{

    private static ConversationService conversationService;
    
    private ConversationService(){
        super();
    }
    
    public static ConversationService getInstance(){
        if(conversationService == null){
            return conversationService = new ConversationService();
        }
        return conversationService;
    }

    @Override
    public void create(Conversation object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Conversation object) throws SQLException {
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
