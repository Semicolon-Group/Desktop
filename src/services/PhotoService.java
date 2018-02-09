/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.SQLException;
import java.util.List;
import models.Photo;

/**
 *
 * @author Elyes
 */
public class PhotoService extends Service implements Create<Photo>,Read<Photo>,Delete<Photo>{

    private static PhotoService photoService;
    
    private PhotoService(){
        super();
    }
    
    public static PhotoService getInstance(){
        if(photoService == null){
            return photoService = new PhotoService();
        }
        return photoService;
    }
    
    @Override
    public Photo create(Photo obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Photo get(Photo obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Photo> getAll(Photo obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Photo obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
