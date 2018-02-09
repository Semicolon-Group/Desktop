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
import models.Block;

/**
 *
 * @author Elyes
 */
public class BlockService extends Service implements Create<Block>,Delete<Block>,Read<Block>{

    private static BlockService blockService;
    
    private BlockService(){
        super();
    }
    
    public static BlockService getInstance(){
        if(blockService == null){
            return blockService = new BlockService();
        }
        return blockService;
    }

    @Override
    public Block create(Block obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Block obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Block get(Block obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Block> getAll(Block obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
