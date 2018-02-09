package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;

public class PicturePostService extends Service implements Create<PicturePostService>,Delete<PicturePostService>,Read<PicturePostService>,Update<PicturePostService>{

    private static PicturePostService picturePostService;
    
    private PicturePostService(){
        super();
    }
    
    public static PicturePostService getInstance(){
        if(picturePostService == null){
            return picturePostService = new PicturePostService();
        }
        return picturePostService;
    }

    @Override
    public PicturePostService create(PicturePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PicturePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PicturePostService get(PicturePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PicturePostService> getAll(PicturePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PicturePostService obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
