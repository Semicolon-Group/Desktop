
import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import models.Conversation;
import models.Member;
import models.Message;
import models.User;
import services.ConversationService;
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

    public static void main(String[] args) throws SQLException, IOException {
// getMembreTest();
//        getAllMessageTest();
////    createMessageTest(); 
//        getAllConversationsTest();
// getAllMessageTest(); ci bon
//        getMsgTest(); 
////            updateMsg(); ci bon
//        getC();
//        updateMembre();
//createConver();
  MemberService ms = MemberService.getInstance();
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        ImageIO.write(webcam.getImage(),"JPG",new File("First.jpg") );

    }

    public static void getMsgTest() throws SQLException {
        MessageService messageService = MessageService.getInstance();

        Message m = new Message();

        m.setId(5);
        m = messageService.get(m);
        System.out.println(m.getId() + " " + m.getContent() + " " + m.isSeen() + " " + m.getSeenDate() + " " + m.getSenderId() + " " + m.getReceiverId());
        //messages.forEach(m2
        //      > System.out.println(m2.getId() + " " + m2.getContent() + " " + m2.isSeen() + " " + m2.getSeenDate() + " " + m2.getSenderId() + " " + m2.getReceiverId())
        //id	content	seen	seen_date	date	sender_id	receiver_id

    }

    public static void getAllMessageTest() throws SQLException, IOException {
        MessageService ms = MessageService.getInstance();

        try {
            List<Message> messages = new ArrayList<>();
            Message m2 = new Message();
            m2.setSenderId(1);
            m2.setReceiverId(7);

            messages = ms.getAll(m2);
//            messages.forEach(e -> System.out.println(m2));
            System.out.println(messages);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void getAllConversationsTest() throws SQLException {

        ConversationService cs = ConversationService.getInstance();
        Conversation c = new Conversation();

        c.setPerson1Id(1);
        List<Conversation> convs = cs.getAll(c);

        convs.forEach(e -> System.out.println(e.getId() + " " + e.getLabel()
                + " " + e.getPerson1Id() + " " + e.getPerson2Id() + " " + e.getSeenDate().toString()));

    }

    public static void getMembreTest() throws SQLException {
//        Member m = new Member();
//        String x = "bdas";
////        m.setPseudo("bdas");
//        m.setEmail("badis.maalej@gmail.com");
//        m.setPseudo("bdas");
//        m.setPseudo("bdas");
//        m.setPassword("5555");
//        m.setPassword("5555");

        Member m = MemberService.getInstance().get(new Member("bdas", "badis.maalej@gmail.com"));

        System.out.println(m.getId() + " " + m.getPseudo() + " " + m.getFirstname() + " " + m.getLastname() + " " + m.getEmail() + " " + m.getPassword() + " " + m.getBirthDate()
                + " " + m.isGender() + " " + m.getHeight() + " " + m.getHeight() + " " + m.getBodyType() + " " + m.getChildrenNumber() + " " + m.getReligion() + " " + m.getReligionImportance() + " " + m.isSmoker() + " "
                + m.isDrinker() + " " + m.getMaxAge() + " " + m.getMinAge() + " " + m.getProximity()
                + " " + m.getLastLogin() + " " + m.getLocked() + " " + m.getIp() + " " + m.getPort() + " " + m.getPreferedRelations() + " " + m.getPreferedStatuses());

    }

    public static void getC() throws SQLException,IOException {

        ConversationService cs = ConversationService.getInstance();
        
         
        try {
            
           
            Conversation x = new Conversation();
         x.setPerson1Id(2);
            x.setPerson2Id(6);
            if (cs.get(x)!= null) {
                
                x.setLabel("no");
             cs.update(x);
                System.out.println("yea");
            } else {
                x= cs.create(x);
                System.out.println(x.getId() + " " + x.getLabel() + " " + x.getPerson1Id());
            }
            

        } catch (Exception e) {
            
        }

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

    public static void updateMembre() throws SQLException {

        MemberService ms = MemberService.getInstance();

        try {
//            Member m2 = new Member();
//            m2.setId(2);
//               ms.get(m2);
//              m2.setPassword("5");
//               
//               ms.update(m2);
            Member m = new Member();
            m.setPseudo("bdas");
            m.setEmail("badis.maalej@gmail.com");
            ms.get(m);
            System.out.println(m.getPassword());

            m.setPassword("9");

            ms.update(m);

            System.out.println(m.getPassword());

        } catch (Exception e) {
            System.out.println(e + "a");
        }
    }
    
    public static void createConver() throws SQLException {
    
        ConversationService cs = ConversationService.getInstance();
        Conversation m = new Conversation();
        m.setLabel("f");
        m.setPerson1Id(7);
        m.setPerson2Id(3);
        m.setSeen(true);
        m.setSeenDate(new Timestamp(System.currentTimeMillis()));
        try{
        
        m=cs.create(m);
            System.out.println(m);
        
        
        }catch(Exception e)
        
        {System.out.println(e);}
        
        
        }

}
