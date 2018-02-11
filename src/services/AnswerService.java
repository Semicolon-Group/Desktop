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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Answer;
import models.Choice;
import models.Enumerations;

/**
 *
 * @author Elyes
 */
public class AnswerService extends Service implements Create<Answer>,Delete<Answer>,Read<Answer>,Update<Answer>{
    
    private static AnswerService answerService;
    
    private AnswerService(){
        super();
    }
    
    public static AnswerService getInstance(){
        if(answerService == null){
            return answerService = new AnswerService();
        }
        return answerService;
    }

    @Override
    public Answer create(Answer obj) throws SQLException {
        String query = "INSERT INTO answer(importance, date, question_id, user_id) VALUES (?,?,?,?)";
        PreparedStatement prepare = CONNECTION.prepareStatement(query);
        prepare.setInt(1, obj.getImportance().ordinal());
        prepare.setTimestamp(2, new Timestamp(new Date().getTime()));
        prepare.setInt(3, obj.getQuestionId());
        prepare.setInt(4, obj.getMemberId());
        prepare.executeUpdate();
        
        query = "SELECT MAX(id) last_row FROM answer";
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        rs.next();
        obj.setId(rs.getInt("last_row"));
        
        insertAcceptedChoices(obj);
        insertSelectedChoices(obj);
        return obj;
    }

    @Override
    public void delete(Answer obj) throws SQLException {
        String query = "delete from answer where id = "+obj.getId();
        CONNECTION.createStatement().executeUpdate(query);
    }

    @Override
    public Answer get(Answer obj) throws SQLException {
        String query = "select * from answer where id = " + obj.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        if(rs.next()){
            obj.setId(rs.getInt("id"));
            obj.setImportance(Enumerations.Importance.values()[rs.getInt("importance")]);
            obj.setMemberId(rs.getInt("user_id"));
            obj.setQuestionId(rs.getInt("question_id"));

            getAcceptedChoices(obj);
            getSelectedChoices(obj);
            return obj;
        }
        return null;
    }

    @Override
    public List<Answer> getAll(Answer obj) throws SQLException {
        String query = "select * from answer where user_id = " + obj.getMemberId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Answer> answers = new ArrayList<>();
        while(rs.next()){
            Answer answer = new Answer();
            answer.setId(rs.getInt("id"));
            answer.setImportance(Enumerations.Importance.values()[rs.getInt("importance")]);
            answer.setMemberId(rs.getInt("user_id"));
            answer.setQuestionId(rs.getInt("question_id"));
            
            getAcceptedChoices(answer);
            getSelectedChoices(answer);
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public void update(Answer obj) throws SQLException {
        String query = "update answer set importance=? where id = ?";
        PreparedStatement prepare = CONNECTION.prepareStatement(query);
        prepare.setInt(1, obj.getImportance().ordinal());
        prepare.setInt(2, obj.getId());
        deleteAcceptedChoices(obj);
        deleteSelectedChoices(obj);
        insertAcceptedChoices(obj);
        insertSelectedChoices(obj);
    }

    private void insertAcceptedChoices(Answer answer){
        answer.getAcceptedChoices().forEach(ac -> {
            try {
                String qry = "insert into accepted_choice values(?,?)";
                PreparedStatement pst = CONNECTION.prepareStatement(qry);
                pst.setInt(1, answer.getId());
                pst.setInt(2, ac.getId());
                pst.executeUpdate();
            } catch (SQLException ex) {
                util.Logger.writeLog(ex, AnswerService.class.getName(), null);
            }
        });
    }
    
    private void getAcceptedChoices(Answer answer) throws SQLException{
        String query = "select * from accepted_choice where answer_id  = "+answer.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        while(rs.next()){
            answer.getAcceptedChoices().add(ChoiceService.getInstance().get(new Choice(rs.getInt("choice_id"))));
        }
    }
    
    private void deleteAcceptedChoices(Answer answer) throws SQLException{
        String query = "delete from accepted_choice where answer_id  = "+answer.getId();
        CONNECTION.createStatement().executeUpdate(query);
    }
    
    private void insertSelectedChoices(Answer answer){
        answer.getSelectedChoices().forEach(sc -> {
            try {
                String qry = "insert into answer_choice values(?,?)";
                PreparedStatement pst = CONNECTION.prepareStatement(qry);
                pst.setInt(1, answer.getId());
                pst.setInt(2, sc.getId());
                pst.executeUpdate();
            } catch (SQLException ex) {
                util.Logger.writeLog(ex, AnswerService.class.getName(), null);
            }
        });
    }
    
    private void deleteSelectedChoices(Answer answer) throws SQLException{
        String query = "delete from answer_choice where answer_id  = "+answer.getId();
        CONNECTION.createStatement().executeUpdate(query);
    }
    
    private void getSelectedChoices(Answer answer) throws SQLException{
        String query = "select * from answer_choice where answer_id  = "+answer.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        while(rs.next()){
            answer.getSelectedChoices().add(ChoiceService.getInstance().get(new Choice(rs.getInt("choice_id"))));
        }
    }
    
}
