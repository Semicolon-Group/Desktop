/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Member;
import services.MemberService;
import util.MailVerification;
import util.PasswordDialog;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class SettingsViewController implements Initializable {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField paswordField;
    @FXML
    private PasswordField confirmPassField;
    
    private Member member;
    private Stage dialog;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setDialog(Stage dialog){
        this.dialog = dialog;
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getScene().setFill(Color.TRANSPARENT);
        populate();
    }
    
    private void populate(){
        try{
            member = MemberService.getInstance().get(new Member(MySoulMate.MEMBER_ID));
            emailField.setText(member.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(SettingsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        try {
            if(emailField.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Your email won't be updated if you keep the field empty!",
                        ButtonType.OK);
                alert.showAndWait();
            }else if(!MailVerification.validate(emailField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please use a valid email address!", ButtonType.OK);
                alert.show();
                return;
            }else if(!emailField.getText().equals(member.getEmail())){
                member.setEmail(emailField.getText());
            }
            if(paswordField.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your password will not be updated.", ButtonType.OK);
                alert.showAndWait();
            }else if(!paswordField.getText().isEmpty() && confirmPassField.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please confirm your password!", ButtonType.OK);
                alert.show();
                return;
            }else if(!paswordField.getText().equals(confirmPassField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password do not match!", ButtonType.OK);
                alert.show();
                return;
            }else{
                member.setPassword(paswordField.getText());
            }
            MemberService.getInstance().update(member);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your credentials has been updated.", ButtonType.OK);
            alert.show();
            dialog.close();
        } catch (SQLException ex) {
            Logger.getLogger(SettingsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmDesactivation(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to deactivate your account?", ButtonType.YES, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.YES){
            PasswordDialog dialog = new PasswordDialog();
            Optional<String> res = dialog.showAndWait();
            res.ifPresent(password -> {
                if(password.equals(member.getPassword())){
                    try {
                        MemberService.getInstance().updatelock(member.getId(), (short)2);
                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Your account has been deactivated.", ButtonType.OK);
                        Optional<ButtonType> r = alert1.showAndWait();
                        this.dialog.close();
                        MySoulMate.getInstance().logOut();
                    } catch (SQLException ex) {
                        Logger.getLogger(SettingsViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Incorrect password!", ButtonType.OK);
                    alert1.show();
                }
            });
        }
    }

    @FXML
    private void closeDialog(MouseEvent event) {
        dialog.close();
    }
    
}
