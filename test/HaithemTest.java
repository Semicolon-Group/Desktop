
import java.sql.SQLException;
import models.Enumerations;
import models.Enumerations.Topic;
import models.Notification;
import models.Question;
import services.MemberService;
import services.NotificationService;
import services.QuestionService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elyes
 */
public class HaithemTest {
    public static void main(String[] args) throws SQLException{
	
	NotificationService ns = NotificationService.getInstance();
	/*Notification n1 = new Notification();
	n1.setSenderId(1);
	n1.setReceiverId(2);
	n1.setPhotoId(1);
	n1.setAnswerId(1);
	n1.setType(Enumerations.NotificationType.LIKE);
	ns.create(n1);*/
//	
//	Notification n2 = new Notification(7);
//	//ns.update(n2);
//	
//	//System.out.println(ns.get(n2));
//	n2.setReceiverId(2);
//	System.out.println(ns.getAll(n2));

	QuestionService qs = QuestionService.getInstance();
//	System.out.println(qs.getAll(null));
//	qs.delete(new Question(1));
//	System.out.println(qs.getAll(null));
	//qs.create(new Question("Your favorite show?",Topic.GENERAL));
	//qs.delete(new Question(3));
	
	MemberService ms = MemberService.getInstance();
//	System.out.println(ms.getAll(null));
    }
}
