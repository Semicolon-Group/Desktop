/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Member;
import services.MemberService;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField pw;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goAuthentification(ActionEvent event) throws SQLException {
//        Alert a = new Alert(Alert.AlertType.ERROR, contentText, buttons);
//        MemberService memberService = new MemberService();
        Member m = new Member();
           MemberService memberService = MemberService.getInstance();
        m.setPseudo(username.getText());
        m.setPassword(pw.getText());
//        m.setPassword(pw.getText());

        Stage stagex = (Stage) username.getScene().getWindow();
        stagex.close();
        
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Empty username");
            alert.setHeaderText("Username ");
            alert.setContentText("No username was inserted");

            alert.showAndWait();

           
        } else if (pw.getText().isEmpty()) {
             Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Empty password");
            alert.setHeaderText(" Password ");
            alert.setContentText("No Password was inserted");

            alert.showAndWait();
            try {
            
         if(memberService.get(m)==null) {
            alert.setTitle("No one !");
            alert.setHeaderText(" No one ! ");
            alert.setContentText("No one !");

            alert.showAndWait();}
         

             
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfaceCompte.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                }
            } else {
                System.out.println("erreur");
            }
//        memberService.get(m);
     
     
        System.out.println(m.getId() + " " + m.getPseudo() + " " + m.getFirstname() + " " + m.getLastname() + " " + m.getEmail() + " " + m.getPassword() + " " + m.getBirthDate() + " " + m.isGender() + " " + m.getHeight() + " " + m.getHeight() + " " + m.getBodyType() + " " + m.getChildrenNumber() + " " + m.getReligion() + " " + m.getReligionImportance() + " " + m.isSmoker() + " "
                + m.isDrinker() + " " + m.getMaxAge() + " " + m.getMinAge() + " " + m.getProximity() + " " + m.getLastLogin() + " " + m.getLocked() + " " + m.getIp() + " " + m.getPort() + " " + m.getPreferedRelations() + " " + m.getPreferedStatuses());
    }
}



