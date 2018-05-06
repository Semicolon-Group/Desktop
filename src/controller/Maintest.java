/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Conversation;
import models.Member;
import models.Message;
import models.User;
import services.MessageService;

/**
 *
 * @author badis
 */
public class Maintest {

    public static void main(String[] args) throws SQLException {
       send();
    

    }

    public static void mm() throws SQLException{
        System.out.println("wtf2");
        MessageService ms = MessageService.getInstance();
        List<Message> msg = ms.getAllMessages(2);
        System.out.println(msg);

    }
        public static void mm2() throws SQLException{
        Message msg = new Message();
        msg.setSenderId(3);
        msg.setReceiverId(2);
        
        MessageService ms = MessageService.getInstance();
        List<Message> msgs = ms.getAllMessagesD(msg);
        System.out.println(msgs);

    }
        
                public static void mm3() throws SQLException{
       Member u = new Member();
       u.setId(2);
       
        
        MessageService ms = MessageService.getInstance();
        List<Conversation> msgs = ms.getAllConversations(u);
        System.out.println(msgs);

    }
                public static void send() throws SQLException
                {
                    Message smg = new Message();
                    smg.setContent("saluut");
                    smg.setSenderId(3);
                    smg.setReceiverId(2);
                            MessageService ms = MessageService.getInstance();
                            ms.SendMessage(smg);

                    
                }
    

}
