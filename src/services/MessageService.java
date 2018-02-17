/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Member;
import models.Message;
import models.User;

/**
 *
 * @author Elyes
 */
public class MessageService extends Service implements Create<Message>, Update<Message>, Read<Message> {

    Statement st;
    
    PreparedStatement pst;
    ResultSet rs;

    private static MessageService messageService;

    private MessageService() {
        super();
    }

    public static MessageService getInstance() {
        if (messageService == null) {
            return messageService = new MessageService();
        }
        return messageService;
    }

    @Override
    public Message create(Message obj) throws SQLException {
        String req = "INSERT INTO `message`( `content`, `seen`, `seen_date`, `date`, `sender_id`, `receiver_id`  )"
                + " VALUES (?,?,?,?,?,?)";

        pst = CONNECTION.prepareStatement(req);

        pst.setString(1, obj.getContent());
        pst.setInt(2, (obj.isSeen()) ? 1 : 0);
        pst.setTimestamp(3, new Timestamp(new Date().getTime()));
        pst.setTimestamp(4, new Timestamp(new Date().getTime()));
        pst.setInt(5, obj.getSenderId());
        pst.setInt(6, obj.getReceiverId());
        pst.executeUpdate();
        return obj;
    }

    @Override
    public void update(Message obj) throws SQLException {
        String req = "Update message SET `seen`=? , `seen_date`=? where id=? ";
        pst = CONNECTION.prepareStatement(req);
        pst.setInt(1, (obj.isSeen()) ? 1 : 0);
        pst.setTimestamp(2, new Timestamp(new Date().getTime()));
        pst.setInt(3, obj.getId());

        pst.executeUpdate();

    }

    @Override
    public Message get(Message obj) throws SQLException {
        String req = "Select * from message where id=" + obj.getId();
        st = CONNECTION.createStatement();
        rs = st.executeQuery(req);
        while (rs.next())
        {

        obj.setContent(rs.getString("content"));
        obj.setSeen(rs.getBoolean("seen"));
        obj.setDate(rs.getTimestamp("date"));
        obj.setSeenDate(rs.getTimestamp("seen_date"));
        obj.setSenderId(rs.getInt("sender_id"));
        obj.setReceiverId(rs.getInt("receiver_id"));

        return obj;
    }
        return obj;
    }

    @Override
    public List<Message> getAll(Message obj) throws SQLException {
        String req = "Select * from message where receiver_id=? and sender_id=? or receiver_id=? and sender_id=? ";
        pst = CONNECTION.prepareStatement(req);
        pst.setInt(2, obj.getReceiverId());
        pst.setInt(1, obj.getSenderId());
        pst.setInt(3, obj.getReceiverId());
        pst.setInt(4, obj.getSenderId());
        rs = pst.executeQuery();

        List<Message> Messages = new ArrayList<>();
        while (rs.next()) {
            Message m = new Message();
            m.setId(rs.getInt("id"));
            m.setContent(rs.getString("content"));
            m.setSeen(rs.getBoolean("seen"));
            m.setSeenDate(rs.getTimestamp("date"));
            m.setSenderId(rs.getInt("sender_id"));
            m.setReceiverId(rs.getInt("receiver_id"));
            m.setDate(rs.getTimestamp("date"));

            Messages.add(m);
        }
        return Messages;
    }

}
