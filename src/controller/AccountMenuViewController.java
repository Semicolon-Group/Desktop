/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
}
