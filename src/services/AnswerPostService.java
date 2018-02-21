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
import models.Answer;
import models.AnswerPost;
import models.Enumerations;
import models.Enumerations.Importance;
import models.Enumerations.PostType;

public class AnswerPostService extends Service implements Read<AnswerPost>{

    private static AnswerPostService answerPostService;
    
    private AnswerPostService(){
        super();
    }
    
    public static AnswerPostService getInstance(){
        if(answerPostService == null){
            return answerPostService = new AnswerPostService();
        }
        return answerPostService;
    }

    @Override
    public AnswerPost get(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerPost> getAll(AnswerPost obj) throws SQLException {
	/*
	* Get recent answers done by liked user.
	*/
	String req = "SELECT * FROM `answer` WHERE user_id = ? and date > ?";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getOwnerId());
	pst.setTimestamp(2, obj.getDate());
	ResultSet rs = pst.executeQuery();
	List<AnswerPost> list = new ArrayList();
	while(rs.next()){
	    list.add(new AnswerPost(new Answer(rs.getInt("id"), rs.getInt("question_id"), rs.getTimestamp("date"), Importance.values()[rs.getInt("importance")], rs.getInt("user_id")), 0, obj.getOwnerId(), rs.getTimestamp("date")));
	}
	return list;
    }

    
}
