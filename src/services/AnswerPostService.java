package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.AnswerPost;
import models.Enumerations;
import models.Enumerations.PostType;

public class AnswerPostService extends Service implements Create<AnswerPost>,Delete<AnswerPost>,Read<AnswerPost>,Update<AnswerPost>{

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
    public AnswerPost create(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnswerPost get(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerPost> getAll(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
