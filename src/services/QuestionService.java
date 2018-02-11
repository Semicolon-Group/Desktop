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
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import models.Choice;
import models.Enumerations;
import models.Question;

/**
 *
 * @author Elyes
 */
public class QuestionService extends Service implements Create<Question>, Delete<Question>, Read<Question>{

    private static QuestionService questionService;

    private QuestionService() {
	super();
    }

    public static QuestionService getInstance() {
	if (questionService == null) {
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
	String query = "delete from question where id= " + obj.getId();
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
	while (rs.next()) {
	    Question qst = new Question();
	    qst.setId(rs.getInt("id"));
	    qst.setQuestion(rs.getString("question"));
	    /*
	    * We're using getAll(Choice) to retrieve the list of choices for our question.
	    */
	    Choice c = new Choice();
	    c.setQuestionId(qst.getId());
	    qst.setChoices(new HashSet<Choice>(ChoiceService.getInstance().getAll(c)));
	    
	    qsts.add(qst);
	}
	return qsts;
    }
}
