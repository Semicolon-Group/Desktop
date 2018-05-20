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
import util.BCrypt;
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
    int logged = 0;

    Date dLater = new Date(System.currentTimeMillis() + 5 * 1000);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void goRecover(ActionEvent event) throws InterruptedException {

        MemberService ms = MemberService.getInstance();

        try {

            Member m = new Member();
            m.setPseudo(pseudo.getText());
//            m.setEmail(email.getText());

            if (ms.get(m) != null) {
                logged = m.getId();

                if (m.getPseudo().equals(pseudo.getText()) && m.getEmail().equals(email.getText())) {

                    Webcam webcam = Webcam.getDefault();
                    webcam.open();
                    ImageIO.write(webcam.getImage(), "JPG", new File("First.jpg"));
                    Thread.sleep(3);
                    webcam.close();
                    System.out.println(m.getPassword() + " " + m.getLastname());
//                SendMail sm = new SendMail(m.getEmail(), "Email recovery", "Bonjour " + m.getFirstname() + " , Votre token de"
//                        + " récupération est " + token);
                    String[] attachFiles = new String[1];
                    attachFiles[0] = "C:\\Users\\badis\\Desktop\\3\\Desktop\\First.jpg";
                    sendEmailWithAttachments("smtp.gmail.com", "465", "mysoulmatePI@gmail.com", "mysoulmatePI*",
                            m.getEmail(), "MySoulMate | Recovery mail", "Use this token to recover your Password : " + token, attachFiles);
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Email de récuperation envoyé !");
                    alert.setHeaderText("Email de récuperation envoyé ! ");
                    alert.setContentText("Nous avons envoyé un mail de récuperation , veuillez consulter votre boit Mail ! ");

                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Vérifiez vos coordonnés ");
                    alert.setHeaderText("Error ");
                    alert.setContentText("Veuillez vérifier votre email !");

                    alert.showAndWait();

                }

            } else if (ms.get(m) == null) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ce Pseudo n'existe pas  ");
                alert.setHeaderText("Error ");
                alert.setContentText("Ce Pseudo n'existe pas ! Veuillez vérifier vos coordonnés ");

            }
        } catch (Exception ex) {
            Logger.getLogger(GlobalViewController.class.getName()).log(Level.SEVERE, null, ex);
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

                Member m = new Member();
                m.setId(logged);

                if (ms.get(m) != null) {
                    String hash = BCrypt.hashpw(newpw.getText(), BCrypt.gensalt());
                    String myName = hash;
                    char[] myNameChars = myName.toCharArray();
                    myNameChars[2] = 'y';
                    myName = String.valueOf(myNameChars);

                    m.setPassword(myName);

                    ms.update(m);
                    ms.getInstance().get(m);
                    System.out.println(m.getPassword());

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Mot de passe changé ");
                    alert.setHeaderText("Success ! ");
                    alert.setContentText("Nous avons changé votre mot de passe , veuillez accéder de nouveau");
                    token = "";
                    alert.showAndWait();
                } else {

                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Veuillez saisir vos coordonnés ");
                    alert.setHeaderText("Error ");
                    alert.setContentText("Veuillez saisir vos coordonnés");
                    token = "";
                    alert.showAndWait();
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (inputtoken.getText() != token) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Please check the token");
            alert.setHeaderText("Error ");
            alert.setContentText("Please check the token");

            alert.showAndWait();
        }

    }

    @FXML
    private void goClose(MouseEvent event) {
        Platform.exit();

    }

    @FXML
    private void back(MouseEvent event) {
        MySoulMate.getInstance().showAuthenticationView();
    }

}
