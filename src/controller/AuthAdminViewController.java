/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Admin;
import models.Member;
import models.User;
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
                Admin m = new Admin();

                m.setPseudo(username.getText());

                m = memberService.getAdmin(m);
                
                if (m != null) {
                    if (m.getPseudo().equals(username.getText()) && m.getPassword().equals(pw.getText())) {

                        MySoulMate.MEMBER_ID = m.getId();
                        Member  m2 = MemberService.getInstance().get(new Member(1));
                        System.out.println("success");

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Wrong password ");
                        alert.setHeaderText("Wrong password ");
                        alert.setContentText(" Wrong password ");
                        alert.showAndWait();
                    }

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Error  ");
                    alert.setHeaderText(" This is not an admin");
                    alert.setContentText(" This is not an admin");

                    alert.showAndWait();
                }
                
                Stage stagex = (Stage) username.getScene().getWindow();
            } catch (SQLException ex) {
                Logger.getLogger(AuthAdminViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void goClose(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void backToLogin(MouseEvent event) {
        MySoulMate.getInstance().showAuthenticationView();
    }

}
