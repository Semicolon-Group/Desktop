
import java.sql.SQLException;
import models.Address;
import models.Enumerations.*;
import models.Feedback;
import models.Member;
import models.Signal;
import services.FeedbackService;
import services.MemberService;
import services.SignalService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elyes
 */
public class AchrefTest {
    public static void main(String[] args) throws SQLException{
//	 FeedbackService f = new FeedbackService();
//        Feedback f1 = new Feedback(1, "bug", true,null);
//         Feedback f2 = new Feedback(2, "buga", true,null);
//        f.create(f1);
//        Feedback f1 = new Feedback();
//        f1.setId(1);
//        f1.setState(true);
//        f.update(f1);
//         Feedback f2 = new Feedback();
//         f2.setId(1);
//         f.get(f2);
//        SignalService s = new SignalService();
//        Signal s1 = new Signal( 1,2,SignalReason.RACISME ,  true, null);
//        s.create(s1);
//        
//        Signal s2 = new Signal();
//        s2.setId(1);
//        s2.setState(false);
//        s.update(s2);
//        s.get(s2);
//        
//        MemberService mem = new MemberService();
//        Member m1 = new Member(null, true , 1.8f , BodyType.GROS , 2 , Religion.ISLAM, Importance.IMPORTANT, true, false , 15 , 18, Proximity.DISTANT, null , 1 , "aa" , "achref", "achref7", "bj", "achref@gmail.com", "achref07", "192.168.1.0", 3303);
        
      Member mbr = new Member();
      mbr.setId(1);
      mbr.setAddress(new Address(mbr.getId(),544.5,646.5,"tunisia","tunis"));
    }
}
