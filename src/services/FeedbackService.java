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
import models.Feedback;

/**
 *
 * @author Elyes
 */
public class FeedbackService implements Create<Feedback>,Update<Feedback>,Read<Feedback>{

    @Override
    public boolean create(Feedback object) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Feedback object) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Feedback get(Feedback obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Feedback> getAll(Feedback obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}