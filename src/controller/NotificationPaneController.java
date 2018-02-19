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
//        notificationsBoxs=FXCollections.
////        NotificationsBox.add(createNotificationBox());
////        NotificationsBox.add(createNotificationBox());
//          NotificationsBox.getChildren().
         try {
            
            Parent notif = FXMLLoader.load(getClass().getResource("/view/NotificationContent.fxml"));
            NotificationsBox.getChildren().add(notif);
            
        } catch (IOException ex) {
            Logger.getLogger(NotificationPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
    
//    private void onNotificationListChange(){
//        
//        NotificationsBox.getChildren().add(notificationsBoxs.get(notificationsBoxs.size()-1));
//        
//    }
    
//    private Pane createNotificationBox(){
//        //Pane p=new Pane();
//        VBox vb=new VBox();
//        
//        
//        Pane p=new Pane();
//        vb.getChildren().clear();
//        vb.getChildren().add(p);
//        
//        
//        p.getChildren().clear();
//        p.getChildren().add(vb);
//        
//        return p;
//    }

//    @FXML
//    private void ajoutNotification(ActionEvent event) {
//        
//        Pane p=createNotificationBox();
//        NotificationsBoxs.add(p);
//    }
        
    }    
    

