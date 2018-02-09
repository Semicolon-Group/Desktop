package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;
import models.Choice;

public class ChoiceService extends Service implements Create<Choice>, Read<Choice>, Delete<Choice>{

    private static ChoiceService choiceService;
    
    private ChoiceService(){
        super();
    }
    
    public static ChoiceService getInstance(){
        if(choiceService == null){
            return choiceService = new ChoiceService();
        }
        return choiceService;
    }
    
    @Override
    public Choice create(Choice obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Choice get(Choice obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Choice> getAll(Choice obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Choice obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
