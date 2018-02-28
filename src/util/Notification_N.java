/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static controller.GlobalViewController.online;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import models.Enumerations;
import static models.Enumerations.NotificationType.MESSAGE;
import static models.Enumerations.NotificationType.REACTION;
import models.Member;
import models.Notification;
import services.MemberService;
import services.NotificationService;
import models.Post;

/**
 *
 * @author vaider
 */
public class Notification_N {

    public static void sendNotifications(Notification obj) throws SQLException {
        Member M = MemberService.getInstance().get(new Member(obj.getReceiverId()));
        Member sender = MemberService.getInstance().get(new Member(obj.getSenderId()));
        String email = M.getEmail();
        String phone = ""+M.getPhone();
        String subject = "MySoulMate | Notification";
        String body = sender.getPseudo();
        switch (obj.getType()) {
            case REACTION:
                if (obj.getPhotoId() == 0) {
                    body += " has reacted to your post.";
                } else {
                    body += " has reacted to your photo.";
                }
                break;

            case LIKE:
                body += " has liked your profile.";

                break;

            case SIGNAL:
                body = "Your signal has been treated.";

                break;

            case FEEDBACK:
                body = "Your feedback has been treated.";

                break;

        }
        obj.setContent(body);
        NotificationService.getInstance().create(obj);

        new N_SendMail(email,subject,body);
        SendSMS sm = new SendSMS();
        //sm.SendSms(subject+" | "+body,phone);

        ShowNotification ps = new ShowNotification();
        ps.handleShowNotification();

    }

}
