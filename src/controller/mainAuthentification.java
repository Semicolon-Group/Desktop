/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author badis
 */
public class mainAuthentification extends Application {
    
    @Override
    public void start(Stage Stage) throws IOException {
      
        
      Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
      
        Stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
              
        javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Stage.setX(primaryScreenBounds.getMinX());
        Stage.setY(primaryScreenBounds.getMinY());
        Stage.setWidth(primaryScreenBounds.getWidth());
        Stage.setHeight(primaryScreenBounds.getHeight());
        
        Stage.setResizable(false);
        Stage.initStyle(StageStyle.UNDECORATED);
       
        
        Stage.setScene(scene);
        
        Stage.show();
//         String accessToken = "EAACEdEose0cBAECLrzGpEXDfNTSTax65zBDUZACBMX7fPZBWG7krQoF8A37c9tM5xfZCWTeAzTBgBsBGxqtiUjt7NyeBocWnrpkproMgtD88o4KYme9axKOtysQRmzvBm7PJ9dbfRRZAdToz3VrXYDdeZAt8mwISiZBZB5c8NH3zVL6wlHKVQXvrcygLtSj8iZBOy30yN813hQZDZD";
//         FacebookClient  fbClient = new DefaultFacebookClient(accessToken, com.restfb.Version.UNVERSIONED); 
//         User me = fbClient.fetchObject("me" , User.class);
//         System.out.println(me.getName());
//         System.out.println(me.getLastName());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
