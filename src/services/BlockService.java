/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
        String query = "insert into user_block values(?,?,?)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        preparedStatement.setInt(1, obj.getSenderId());
        preparedStatement.setInt(2, obj.getReceiverId());
        preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
        preparedStatement.executeUpdate();
        return obj;
    }

    @Override
    public void delete(Block obj) throws SQLException {
        String query = "delete from user_block where ";
        if (obj.getSenderId() != 0 && obj.getReceiverId() != 0) {
            query += "block_sender_id = " + obj.getSenderId() + " and block_receiver_id = " + obj.getReceiverId();
        } else if (obj.getSenderId() != 0) {
            query += "block_sender_id = " + obj.getSenderId();
        } else if (obj.getReceiverId() != 0) {
            query += "block_receiver_id = " + obj.getReceiverId();
        } else {
            return;
        }
        CONNECTION.createStatement().executeUpdate(query);
    }

    @Override
    public Block get(Block obj) throws SQLException {
        String query = "select * from user_block where block_sender_id = " + obj.getSenderId() + " and block_receiver_id = "
                + obj.getReceiverId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        rs.next();
        obj.setSenderId(rs.getInt("block_sender_id"));
        obj.setReceiverId(rs.getInt("block_receiver_id"));
        obj.setDate(rs.getTimestamp("date"));
        return obj;
    }

    @Override
    public List<Block> getAll(Block obj) throws SQLException {
        String query = "select * from user_block";
        if (obj.getSenderId() != 0 && obj.getReceiverId() != 0) {
            query += " where block_sender_id = " + obj.getSenderId() + " and block_receiver_id = " + obj.getReceiverId();
        } else if (obj.getSenderId() != 0) {
            query += " where block_sender_id = " + obj.getSenderId();
        } else if (obj.getReceiverId() != 0) {
            query += " where block_receiver_id = " + obj.getReceiverId();
        }
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Block> blocks = new ArrayList<>();
        while (rs.next()) {
            blocks.add(new Block(rs.getInt("block_sender_id"), rs.getInt("block_receiver_id"), rs.getTimestamp("date")));
        }
        return blocks;
    }
}
