package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;

public class AnswerPostService extends Service implements Create<AnswerPostService>,Delete<AnswerPostService>,Read<AnswerPostService>,Update<AnswerPostService>{

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
    public AnswerPostService create(AnswerPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(AnswerPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnswerPostService get(AnswerPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerPostService> getAll(AnswerPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnswerPostService update(AnswerPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
