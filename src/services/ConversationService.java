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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import models.Conversation;
import models.Member;

/**
 *
 * @author Elyes
 */
public class ConversationService extends Service implements Create<Conversation>, Update<Conversation>, Read<Conversation> {

    Statement st;

    PreparedStatement pst;
    ResultSet rs;
    private static ConversationService conversationService;

    private ConversationService() {
        super();
    }

    public static ConversationService getInstance() {
        if (conversationService == null) {
            return conversationService = new ConversationService();
        }
        return conversationService;
    }

    @Override
    public Conversation create(Conversation obj) throws SQLException {
        String req = "INSERT INTO `conversation`( `person1_id`, `person2_id`,"
                + " `label`, `seen`, `seen_date`) VALUES (?,?,?,?,?)";

        pst = CONNECTION.prepareStatement(req);

        pst.setInt(1, obj.getPerson1Id());
        pst.setInt(2, obj.getPerson2Id());
        pst.setString(3, obj.getLabel());
        pst.setInt(4, obj.isSeen() ? 1 : 0);
        pst.setTimestamp(5, new Timestamp(new Date().getTime()));
        pst.executeUpdate();

        return obj;

    }

    @Override
    public void update(Conversation obj) throws SQLException {
        String condition = "";

        if (obj.getId() != 0) {
            condition = " where id =?" + obj.getId();
        } else if (obj.getPerson1Id() != 0 && obj.getPerson2Id() != 0) {
            condition = "Where person1_id=" + obj.getPerson1Id() + " and person2_id=" + obj.getPerson2Id();
        }
        String req = "UPDATE `conversation` SET `label`=?,`seen`=?,`seen_date`=? " + condition;
        pst = CONNECTION.prepareStatement(req);
        pst.setString(1, obj.getLabel());
        pst.setInt(2, obj.isSeen() ? 1 : 0);
        pst.setTimestamp(3, new Timestamp(new Date().getTime()));
        pst.executeUpdate();

    }

    @Override
    public Conversation get(Conversation obj) throws SQLException {
        String req = "SELECT * FROM `conversation` WHERE person1_id =" + obj.getPerson1Id() + " and person2_id ="
                + obj.getPerson2Id() + " or person1_id=" + obj.getPerson2Id() + " and person2_id=" + obj.getPerson1Id();
        st = CONNECTION.createStatement();
        rs = st.executeQuery(req);
        if (rs.next()) {
            obj.setPerson1Id(rs.getInt("person1_id"));
            obj.setPerson2Id(rs.getInt("person2_id"));
            obj.setLabel(rs.getString("label"));
            obj.setSeen(rs.getBoolean("seen"));
            obj.setSeenDate(rs.getTimestamp("seen_date"));

            return obj;
        }
        return null;

    }

    @Override
    public List<Conversation> getAll(Conversation obj) throws SQLException {

        String req = "Select * from conversation where person1_id=? or person2_id=?";
        pst = CONNECTION.prepareStatement(req);
        pst.setInt(1, obj.getPerson1Id());
        pst.setInt(2, obj.getPerson1Id());

        rs = pst.executeQuery();
        List<Conversation> Conversations = new ArrayList<>();
        while (rs.next()) {
            Conversations.add(new Conversation(rs.getInt("id"), rs.getString("label"),
                    rs.getBoolean("seen"), rs.getTimestamp("seen_date"),
                    rs.getInt("person1_id"), rs.getInt("person2_id")));

        }

        return Conversations;

    }

}
