package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;

public class UpdatePostService extends Service implements Create<UpdatePostService>,Delete<UpdatePostService>,Read<UpdatePostService>,Update<UpdatePostService>{

    private static UpdatePostService updatePostService;
    
    private UpdatePostService(){
        super();
    }
    
    public static UpdatePostService getInstance(){
        if(updatePostService == null){
            return updatePostService = new UpdatePostService();
        }
        return updatePostService;
    }

    @Override
    public UpdatePostService create(UpdatePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(UpdatePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UpdatePostService get(UpdatePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UpdatePostService> getAll(UpdatePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(UpdatePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
