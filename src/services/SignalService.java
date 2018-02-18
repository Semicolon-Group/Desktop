/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Enumerations;
import models.Enumerations.SignalReason;
import models.Signal;

/**
 *
 * @author Elyes
 */
public class SignalService extends Service implements Create<Signal>,Update<Signal>,Read<Signal>{

    private static SignalService signalService;
    
    private SignalService(){
        super();
    }
    
    public static SignalService getInstance(){
        if(signalService == null){
            return signalService = new SignalService();
        }
        return signalService;
    }

    @Override
    public Signal create(Signal obj) throws SQLException {
        String query = "insert into user_signal (reason, date, state, sender_id, receiver_id, content) values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        preparedStatement.setInt(1, obj.getReason().ordinal());
        preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
        preparedStatement.setBoolean(3, false);
        preparedStatement.setInt(4, 1);
        preparedStatement.setInt(5, 2);
        preparedStatement.setString(6, obj.getContent());
        preparedStatement.executeUpdate();
        return obj;
    }

    @Override
    public void update(Signal obj) throws SQLException {
        String query = "UPDATE user_signal SET state = ?  WHERE id = ?";
        PreparedStatement pst = CONNECTION.prepareStatement(query);
        pst.setBoolean(1, obj.isState());
        pst.setInt(2, obj.getId());
        pst.executeUpdate();
    }

    @Override
    public Signal get(Signal obj) throws SQLException {
	String query = "select * from user_signal where id = " + obj.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        if(rs.next()){
            obj.setId(rs.getInt("id"));
            obj.setReason(SignalReason.values()[rs.getInt("reason")]);
            obj.setDate(rs.getTimestamp("date"));
            obj.setState(rs.getBoolean("state"));
            obj.setSenderId(rs.getInt("sender_id"));
            obj.setReceiver(rs.getInt("receiver_id"));
            obj.setContent(rs.getString("content"));
            return obj;
        }
	return null;
    }
    
    @Override
    public List<Signal> getAll(Signal obj) throws SQLException {
	String query = "select * from user_signal " ;
	if (obj.getSenderId() > 0)
	    query += "WHERE sender_id = " + obj.getSenderId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Signal> signaux = new ArrayList<>();
        while(rs.next()){
            Signal s = new Signal();
	    s.setId(rs.getInt("id"));
            s.setReason(Enumerations.SignalReason.values()[rs.getInt("reason")]);
            s.setDate(rs.getTimestamp("date"));
            s.setState(rs.getBoolean("state"));
            s.setSenderId(rs.getInt("sender_id"));
            s.setReceiver(rs.getInt("receiver_id"));
            s.setContent(rs.getString("content"));
            signaux.add(s);
        }
        return signaux;
    }

    
}
