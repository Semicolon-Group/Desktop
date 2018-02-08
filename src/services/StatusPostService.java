package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;

public class StatusPostService extends Service implements Create<StatusPostService>,Delete<StatusPostService>,Read<StatusPostService>,Update<StatusPostService>{

    private static StatusPostService statusPostService;
    
    private StatusPostService(){
        super();
    }
    
    public static StatusPostService getInstance(){
        if(statusPostService == null){
            return statusPostService = new StatusPostService();
        }
        return statusPostService;
    }

    @Override
    public StatusPostService create(StatusPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(StatusPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StatusPostService get(StatusPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StatusPostService> getAll(StatusPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StatusPostService update(StatusPostService obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
