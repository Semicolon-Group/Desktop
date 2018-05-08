/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Conversation;
import models.Member;
import models.Message;
import models.User;

import org.apache.http.conn.ConnectionRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.HTTPConnector;

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
        if (rs.next()) {

            obj.setContent(rs.getString("content"));
            obj.setSeen(rs.getBoolean("seen"));
            obj.setDate(rs.getTimestamp("date"));
            obj.setSeenDate(rs.getTimestamp("seen_date"));
            obj.setSenderId(rs.getInt("sender_id"));
            obj.setReceiverId(rs.getInt("receiver_id"));

            return obj;
        }
        return null;
    }

    @Override
    public List<Message> getAll(Message obj) throws SQLException {
        String req = "Select * from message where sender_id=? and receiver_id=? or sender_id=? and receiver_id=? ";
        pst = CONNECTION.prepareStatement(req);
        pst.setInt(1, obj.getSenderId());
        pst.setInt(2, obj.getReceiverId());
        pst.setInt(3, obj.getReceiverId());
        pst.setInt(4, obj.getSenderId());
        rs = pst.executeQuery();

        List<Message> Messages = new ArrayList<>();
        while (rs.next()) {
            Message m = new Message();
            m.setId(rs.getInt("id"));
//            System.out.println(rs.getInt("id"));
            m.setContent(rs.getString("content"));
            m.setSeen(rs.getBoolean("seen"));
            m.setSeenDate(rs.getTimestamp("date"));
            m.setSenderId(rs.getInt("sender_id"));
            m.setReceiverId(rs.getInt("receiver_id"));
            m.setDate(rs.getTimestamp("date"));
//            System.out.println(m);
            Messages.add(m);

        }

        return Messages;
    }

    private List<Message> list;
    private List<Conversation> list2;

    public List<Message> getAllMessages(int threadId) {
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/get_messages?id=" + threadId;

        String content = HTTPConnector.connect(url);
        if (content != null && !content.isEmpty()) {
            try {
                list = new ArrayList<>();
                JSONParser j = new JSONParser();

                JSONArray MessagesMap = (JSONArray) j.parse(new StringReader(content.toString()));
                //JSONArray likeList = (JSONArray) likeMap.get("root");
                if (MessagesMap != null && !MessagesMap.isEmpty()) {
                    for (int i = 0; i < MessagesMap.size(); i++) {
                        list.add(parseMessages((JSONObject) MessagesMap.get(i)));
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    private Message parseMessages(JSONObject MessageMap) {
        Message m = new Message();
        m.setId(((Long) MessageMap.get("id")).intValue());
        m.setSenderId(((Long) MessageMap.get("senderId")).intValue());
        m.setContent((String) MessageMap.get("body"));
        return m;
    }

    public List<Message> getAllMessagesD(Message msg) {
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/get_messages2?sender=" + msg.getSenderId()
                + "&receiver=" + msg.getReceiverId();

        String content = HTTPConnector.connect(url);
        if (content != null && !content.isEmpty()) {
            try {
                list = new ArrayList<>();
                JSONParser j = new JSONParser();

                JSONArray MessagesMap = (JSONArray) j.parse(new StringReader(content.toString()));
                //JSONArray likeList = (JSONArray) likeMap.get("root");
                if (MessagesMap != null && !MessagesMap.isEmpty()) {
                    for (int i = 0; i < MessagesMap.size(); i++) {
                        list.add(parseMessages3((JSONObject) MessagesMap.get(i)));
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    private Message parseMessages3(JSONObject MessageMap) {
        Message m = new Message();

        m.setSenderId(((Long) MessageMap.get("senderId")).intValue());
        m.setReceiverId(((Long) MessageMap.get("receiver")).intValue());
        m.setContent("" + ((Long) MessageMap.get("thread")).intValue());

        return m;
    }

    public List<Conversation> getAllConversations(Member msg) {

        //prend id user et retourne ttes ses conversations 
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/get_threads?id=" + msg.getId();

        String content = HTTPConnector.connect(url);
        if (content != null && !content.isEmpty()) {
            try {
                list2 = new ArrayList<>();
                JSONParser j = new JSONParser();

                JSONArray MessagesMap = (JSONArray) j.parse(new StringReader(content.toString()));
                //JSONArray likeList = (JSONArray) likeMap.get("root");
                if (MessagesMap != null && !MessagesMap.isEmpty()) {
                    for (int i = 0; i < MessagesMap.size(); i++) {
                        list2.add(parseConversations((JSONObject) MessagesMap.get(i)));
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list2;
    }

    private Conversation parseConversations(JSONObject MessageMap) {
        Conversation m = new Conversation();
        m.setPerson1Id(((Long) MessageMap.get("participantId")).intValue());
        m.setPerson2Id(((Long) MessageMap.get("threadId")).intValue());
        m.setLabel((String) MessageMap.get("timeSince"));
        
        return m;
    }

    public void SendMessage(Message msg) {
        String Url = "http://localhost/mysoulmate/web/app_dev.php/service/send_message?sender=" + msg.getSenderId() + ""
                + "&receiver=" + msg.getReceiverId() + "&body=" + msg.getContent();
        HTTPConnector.connect(Url);
    }
}
