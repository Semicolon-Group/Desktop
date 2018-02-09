package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.SQLException;
import java.util.List;
import models.Reaction;

public class ReactionService extends Service implements Create<Reaction>, Read<Reaction>, Delete<Reaction> {

    private static ReactionService reactionService;
    
    private ReactionService(){
        super();
    }
    
    public static ReactionService getInstance(){
        if(reactionService == null){
            return reactionService = new ReactionService();
        }
        return reactionService;
    }
    
    @Override
    public Reaction create(Reaction obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reaction get(Reaction obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reaction> getAll(Reaction obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Reaction obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
