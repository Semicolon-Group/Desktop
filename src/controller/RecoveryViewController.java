/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import models.*;
import services.MemberService;
import util.SendMail;
import util.Sendmail2;
import static util.Sendmail2.sendEmailWithAttachments;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class RecoveryViewController implements Initializable {

    @FXML
    private TextField pseudo;
    @FXML
    private Button recover;
    @FXML
    private TextField email;
    @FXML
    private Button change;
    @FXML
    private ImageView cls;
    @FXML
    private TextField inputtoken;
    @FXML
    private TextField newpw;
    String token = UUID.randomUUID().toString();
    int logged = 0 ;

    Date dLater = new Date(System.currentTimeMillis() + 5 * 1000);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void goRecover(ActionEvent event) throws SQLException, IOException, InterruptedException {

        Webcam webcam = Webcam.getDefault();
        webcam.open();
        ImageIO.write(webcam.getImage(), "JPG", new File("First.jpg"));
        Thread.sleep(3);
        webcam.close();

        MemberService ms = MemberService.getInstance();

        try {


            Member m = new Member();
            m.setPseudo(pseudo.getText());
//            m.setEmail(email.getText());
            m = ms.get(m);
            if (m.getPseudo() != null ) {
                logged = m.getId(); 
                
                if (m.getPseudo().equals(pseudo.getText())&&m.getEmail().equals(email.getText()))
                    
                {
                    System.out.println(m.getPassword() + " " + m.getLastname());
//                SendMail sm = new SendMail(m.getEmail(), "Email recovery", "Bonjour " + m.getFirstname() + " , Votre token de"
//                        + " récupération est " + token);
                String[] attachFiles = new String[1];
                attachFiles[0] = "C:\\Users\\badis\\Documents\\PI\\Desktop\\First.jpg";
                sendEmailWithAttachments("smtp.gmail.com", "465", "testmailesprit69@gmail.com", "testmailesprit69@",
                        m.getEmail(), "Recovery mail", "Ce mail contient un token pour récuperer votre mot de passe , ainsi que la photo de la personne qui a essayé de le récuperer ."+
                                " \n My Soul Mate ."+"  \n "
                                + "Token : " + token, attachFiles);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Recovery mail ");
                alert.setHeaderText("Mail Sent ! ");
                alert.setContentText("Nous avons envoyé un mail de récuperation , veuillez consulter votre boit Mail ! ");

                alert.showAndWait();
                
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Vérifiez vos coordonnés ");
                alert.setHeaderText("Error ");
                alert.setContentText("Veuillez vérifier votre email !");

                alert.showAndWait();
           
                }
                
            } else {

                        Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Ce Pseudo n'existe pas  ");
                alert.setHeaderText("Error ");
                alert.setContentText("Ce Pseudo n'existe pas ! Veuillez vérifier vos coordonnés ");
                
            }
        } catch (Exception ex) {
            System.out.println(ex + "www");
        }


        long mTime = System.currentTimeMillis();
        long end = (System.currentTimeMillis()) + 5000; // 5 seconds 

        if (mTime > end) {
            Platform.exit();

        }

    }

    @FXML
    private void goChange(ActionEvent event) throws SQLException, IOException, InterruptedException {

        MemberService ms = MemberService.getInstance();
        if (inputtoken.getText().equals(token)) {
            try {

//               Member m =ms.get(new Member(pseudo.getText(), email.getText()));
                Member m = new Member();
                if (logged!=0){
               m.setId(logged);
                m = ms.get(m);
                m.setPassword(newpw.getText());

                ms.update(m);
                ms.getInstance().get(m);
                System.out.println(m.getPassword());

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Mot de passe changé ");
                alert.setHeaderText("Success ! ");
                alert.setContentText("Nous avons changé votre mot de passe , veuillez accéder de nouveau");
                token = "";
                alert.showAndWait();}
                else {
                
                 Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Veuillez saisir vos coordonnés ");
                alert.setHeaderText("Error ");
                alert.setContentText("Veuillez saisir vos coordonnés");
                token = "";
                alert.showAndWait();}
                
                

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (inputtoken.getText() != token) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Vérifiez vos coordonnés ");
            alert.setHeaderText("Error ");
            alert.setContentText("Veuillez vérifier vos coordonnés !");

            alert.showAndWait();
        }

    }

    @FXML
    private void goClose(MouseEvent event) {
        Platform.exit();

    }

}
