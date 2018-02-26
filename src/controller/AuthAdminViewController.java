/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Member;
import services.MemberService;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class AuthAdminViewController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField pw;
    @FXML
    private Label cls;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goAuthentification(ActionEvent event) {
            MemberService memberService = MemberService.getInstance();
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty username");
            alert.setHeaderText("Username ");
            alert.setContentText("No username was inserted");

            alert.showAndWait();

        } else if (pw.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty password");
            alert.setHeaderText(" Password ");
            alert.setContentText("No Password was inserted");

            alert.showAndWait();
        } else {

            try {

                Member m = new Member();

                m.setPseudo(username.getText());
//                m.setPassword(pw.getText());

                m = memberService.get(m);

                if (m != null) {
                    if (m.getPseudo().equals(username.getText()) && m.getPassword().equals(pw.getText())) {
                        if (m.getLocked() == 0) {
                            m.setConnected(true);
                            memberService.update(m);
                            MySoulMate.MEMBER_ID = m.getId();
                            MySoulMate.getInstance().ChangeToHomeScene();
                        } else if (m.getLocked() == 1) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Bloqué");
                            alert.setHeaderText("Vous etes bloqué ! ");
                            alert.setContentText(" Vous etes bloqué . \n Vous ne pouvez pas accéder à MySoulMate .");

                            alert.showAndWait();
                        } else if (m.getLocked() == 2) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Compte supprimé");
                            alert.setHeaderText("Compte supprimé ");
                            alert.setContentText(" Compte supprimé");

                            alert.showAndWait();

                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Mot de passe eronné !");
                        alert.setHeaderText("Mot de passe eronné ! ");
                        alert.setContentText(" Mot de passe eronné !");

                        alert.showAndWait();

                    }

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Erreur ");
                    alert.setHeaderText(" Ce membre n'existe pas ! ");
                    alert.setContentText(" Ce membre n'existe pas ! ");

                    alert.showAndWait();
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            Stage stagex = (Stage) username.getScene().getWindow();

        }
    }

    @FXML
    private void goClose(MouseEvent event) {
        Platform.exit();
    }
    
}
