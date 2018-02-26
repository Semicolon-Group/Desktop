/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author vaider
 */
public class ShowNotification {
    
    public void handleShowNotification(String title,String text,Node graphic){
        Notifications notificationBuilder = Notifications.create()
                
                .title("SemiCOLON")
                .text("Notification envoyé")
                 .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    System.out.println("Notification envoyé");
                        }
                });
         notificationBuilder.darkStyle();
         notificationBuilder.showConfirm();
 
    }
}
