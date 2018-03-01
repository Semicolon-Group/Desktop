/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.Member;
import models.Notification;

/**
 *
 * @author vaider
 */
public class N_SendMail {
     private String user = "mysoulmatePI@gmail.com";
    private String password = "mysoulmatePI*";

    public N_SendMail(String to, String sub, String msg) {

        Properties properties = new Properties();

        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", user);
        properties.setProperty("mail.smtp.password", password);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

                return new javax.mail.PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage msgg = new MimeMessage(session);
            msgg.setFrom(new InternetAddress(user));
            msgg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
            msgg.setSubject(sub);
            msgg.setText(msg);
            msgg.reply(true);

            Transport.send(msgg);
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
