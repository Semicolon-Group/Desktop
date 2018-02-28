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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Notification;
import services.NotificationService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class NotificationPaneViewController implements Initializable {

    @FXML
    private VBox NotificationsBox;
    @FXML
    private AnchorPane notificationPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Notification not = new Notification();
            not.setReceiverId(GlobalViewController.online.getId());
            NotificationsBox.getChildren().clear();
            for (Notification n : NotificationService.getInstance().getAll(not)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NotificationContent.fxml"));
                Parent notif = loader.load();
                NotificationContentController c = (NotificationContentController) loader.getController();
                c.fill(n);
                c.setPhotoId(n.getPhotoId());
                c.setPostId(n.getPostId());
                c.setSenderId(n.getSenderId());
                NotificationsBox.getChildren().add(notif);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationPaneViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotificationPaneViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
