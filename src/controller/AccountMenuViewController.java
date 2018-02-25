/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Question;
import services.QuestionService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class AccountMenuViewController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showProfile(ActionEvent event) {
        GlobalViewController controller = GlobalViewController.getInstance();
        if(controller!=null){
            controller.setMainContent("/view/SelfProfileView.fxml");
            controller.clearMenuSelection();
        }
    }

//    private void showOtherProfile(ActionEvent event) {
//        GlobalViewController controller = GlobalViewController.getInstance();
//        if(controller!=null){
//            FXMLLoader loader = controller.setMainContent("/view/OthersProfileView.fxml");
//            ((OthersProfileViewController)loader.getController()).setUserId(1);
//        }
//    }

    @FXML
    private void showSettingsDialog(ActionEvent event) {
        try {
            final Stage dialog = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SettingsView.fxml"));
            AnchorPane pane = loader.load();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(MySoulMate.mainStage);
            Scene dialogScene = new Scene(pane, 710, 350);
            dialog.setScene(dialogScene);
            ((SettingsViewController)loader.getController()).setDialog(dialog);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(AccountMenuViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        MySoulMate.getInstance().logOut();
    }
}
