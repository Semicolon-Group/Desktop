/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;
import models.Answer;

/**
 *
 * @author Elyes
 */
public class AnswerService implements Create<Answer>,Delete<Answer>,Read<Answer>,Update<Answer>{

    @Override
    public boolean create(Answer object) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Answer object) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Answer get(Answer obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Answer> getAll(Answer obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Answer object) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
