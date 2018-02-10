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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Enumerations;
import models.Question;

/**
 *
 * @author Elyes
 */
public class QuestionService extends Service implements Create<Question>,Delete<Question>,Read<Question>,Update<Question>{

    private static QuestionService questionService;
    
    public QuestionService(){
        super();
    }
    
    public static QuestionService getInstance(){
        if(questionService == null){
            return questionService = new QuestionService();
        }
        return questionService;
    }

    @Override
    public Question create(Question obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Question obj) throws SQLException {
	String query = "delete from question where id= "+ obj.getId();
        CONNECTION.createStatement().executeUpdate(query);
    }

    @Override
    public Question get(Question obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Question> getAll(Question obj) throws SQLException {
	String query = "select * from question";
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Question> qsts = new ArrayList<>();
        while(rs.next()){
            Question qst = new Question();
        qst.setQuestion(rs.getString("content"));
        
       
            qsts.add(obj);
        }
        return qsts;
    }

    @Override
    public void update(Question obj) throws SQLException {
	String query = "UPDATE `question` SET `question`=? WHERE id =?";
        PreparedStatement pst = CONNECTION.prepareStatement(query);
        pst.setString(1, obj.getQuestion());
        pst.setInt(2, obj.getId());
         
        
        pst.executeUpdate();
    }

    
}
