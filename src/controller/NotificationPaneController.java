/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Notification;
import services.NotificationService;

/**
 * FXML Controller class
 *
 * @author vaider
 */
public class NotificationPaneController implements Initializable {
    @FXML
    private AnchorPane notificationPane;
    @FXML
    private VBox NotificationsBox;
    private ObservableList<Pane> notificationsBoxs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

         try {
             Notification not = new Notification();
             not.setReceiverId(GlobalViewController.online.getId());
            for(Notification n : NotificationService.getInstance().getAll(not)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NotificationContent.fxml"));
                    Parent notif = loader.load();
                    NotificationContentController c = (NotificationContentController)loader.getController();
                    c.fill(n);
                    NotificationsBox.getChildren().add(notif);
            }

            
            } catch (IOException | SQLException ex) {
                Logger.getLogger(NotificationPaneController.class.getName()).log(Level.SEVERE, null, ex);
            }    
    }

}    
    

