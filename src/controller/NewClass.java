import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import models.Conversation;
import models.Member;
import models.Message;
import models.User;
import services.MemberService;
import services.MessageService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author badis
 */
public class NewClass {

    public static void main(String[] args) throws SQLException {
 getMembreTest();
//          getAllMessageTest(); ci bon
        //       createMessageTest(); ci bon

// getAllMessageTest(); ci bon
//        getMsgTest(); ci bon 
//            updateMsg(); ci bon

    }

    public static void getMsgTest() throws SQLException {
        MessageService messageService = MessageService.getInstance();
        Member sender = new Member();
        sender.setId(1);
        Member receiver = new Member();
        receiver.setId(2);
        Message m = new Message();
        m.setId(11);
        m.setContent(m.getContent());
        m.setSenderId(1);
        m.setReceiverId(2);
        Message m2 = messageService.get(m);
        System.out.println(m2.getId() + " " + m2.getContent() + " " + m2.isSeen() + " " + m2.getSeenDate() + " " + m2.getSenderId() + " " + m2.getReceiverId());
        //messages.forEach(m2
        //      > System.out.println(m2.getId() + " " + m2.getContent() + " " + m2.isSeen() + " " + m2.getSeenDate() + " " + m2.getSenderId() + " " + m2.getReceiverId())
        //id	content	seen	seen_date	date	sender_id	receiver_id

    }

    public static void getAllMessageTest() throws SQLException {
        MessageService messageService = MessageService.getInstance();
        Member sender = new Member();
        sender.setId(1);
        Member receiver = new Member();
        receiver.setId(2);
        Message m2 = new Message();
        m2.setSenderId(sender.getId());
        m2.setReceiverId(receiver.getId());
        List<Message> messages = messageService.getAll(m2);
        messages.forEach(e2-> System.out.println(m2.getId() + " " + m2.getContent() + " " + m2.isSeen() + " " + m2.getSeenDate() + " " + m2.getSenderId() + " " + m2.getReceiverId())
        );//id	content	seen	seen_date	date	sender_id	receiver_id

    }

    public static void getMembreTest() throws SQLException {
        Member m = new Member();
        String x = "bdas";
        m.setPseudo("bdas");
        m.setPassword("5555");
//        m.setPassword("5555");
     
        MemberService memberService = MemberService.getInstance();
        m = memberService.get(m);
        System.out.println(m.getId() + " " + m.getPseudo() + " " + m.getFirstname() + " " + m.getLastname() + " " + m.getEmail() + " " + m.getPassword() + " " + m.getBirthDate()
                + " " + m.isGender() + " " + m.getHeight() + " " + m.getHeight() + " " + m.getBodyType() + " " + m.getChildrenNumber() + " " + m.getReligion() + " " + m.getReligionImportance() + " " + m.isSmoker() + " "
                + m.isDrinker() + " " + m.getMaxAge() + " " + m.getMinAge() + " " + m.getProximity()
                + " " + m.getLastLogin() + " " + m.getLocked() + " " + m.getIp() + " " + m.getPort() + " " + m.getPreferedRelations() + " " + m.getPreferedStatuses());
    }

    public static void createMessageTest() throws SQLException {
        MessageService msg1 = MessageService.getInstance();
        User sender = new Member();
        sender.setId(1);
        User receiver = new Member();
        receiver.setId(2);
        
        
        //    public Message(String content, Timestamp date, int senderId, int receiverId, boolean seen, Timestamp seenDate) {

        Message msg = new Message("yoo2", new Timestamp(System.currentTimeMillis()), 2, 1, false, new Timestamp(System.currentTimeMillis()));
        msg = msg1.create(msg);
        System.out.println(msg.getId() + " " + msg.getContent() + " " + msg.getSeenDate());

    }

    public static void updateMsg() throws SQLException {
         MessageService msg1 = MessageService.getInstance();
         Message msg = new Message();
         msg.setId(11);
         msg.setSeenDate(new Timestamp(System.currentTimeMillis()));
         msg.setSeen(true);
         msg1.update(msg);
        
       
        
        

    }
}
