/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import models.Choice;
import models.Enumerations.Topic;
import models.Question;

/**
 *
 * @author Elyes
 */
public class QuestionService extends Service implements Create<Question>, Delete<Question>, Read<Question> {

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
	String req = "INSERT INTO `question`(`question`, `topic`) VALUES (?,?)";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setString(1, obj.getQuestion());
	pst.setInt(2, obj.getTopic().ordinal());
	pst.executeUpdate();
	/*
	* After creating question, we need to create the choices as well. This creation needs the new question_id.
	* So we return the object with its new id, in order to use it.
	*/
	req = "SELECT MAX(id) max from question";
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	rs.next();
	obj.setId(rs.getInt("max"));
	
	return obj;
    }
   
     public void deleteq(int id) throws SQLException{
        String query = "delete from question where id= " + id;
        CONNECTION.createStatement().executeUpdate(query);

            }
            
            
    @Override
    public void delete(Question obj) throws SQLException {
	String query = "delete from question where id= " + obj.getId();
	CONNECTION.createStatement().executeUpdate(query);
    }

    @Override
    public Question get(Question obj) throws SQLException {
	String query = "select * from question where id = " + obj.getId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(query);
	Question qst = new Question();
	if(rs.next()) {
	    qst.setId(rs.getInt("id"));
	    qst.setQuestion(rs.getString("question"));
	    qst.setTopic(Topic.values()[rs.getInt("topic")]);
	    /*
	    * We're using getAll(Choice) to retrieve the list of choices for our question.
	     */
	    Choice c = new Choice();
	    c.setQuestionId(qst.getId());
	    qst.setChoices(new HashSet<Choice>(ChoiceService.getInstance().getAll(c)));
            return qst;
        }
	return null;
    }

    @Override
    public List<Question> getAll(Question obj) throws SQLException {
	String query = "select * from question";
        if(obj.getTopic() != null)
            query+=" where topic = "+obj.getTopic().ordinal();
	ResultSet rs = CONNECTION.createStatement().executeQuery(query);
	List<Question> qsts = new ArrayList<>();
	while (rs.next()) {
	    Question qst = new Question();
	    qst.setId(rs.getInt("id"));
	    qst.setQuestion(rs.getString("question"));
	    qst.setTopic(Topic.values()[rs.getInt("topic")]);
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
