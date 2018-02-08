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
import models.Like;

/**
 *
 * @author Elyes
 */
public class LikeService extends Service implements Create<Like>,Delete<Like>,Read<Like>{

    private static LikeService likeService;
    
    private LikeService(){
        super();
    }
    
    public static LikeService getInstance(){
        if(likeService == null){
            return likeService = new LikeService();
        }
        return likeService;
    }

    @Override
    public void create(Like object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Like object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Like get(Like obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Like> getAll(Like obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
