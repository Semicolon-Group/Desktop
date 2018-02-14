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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class HomeViewController implements Initializable {

    @FXML
    private VBox feed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent post = FXMLLoader.load(getClass().getResource("/view/postTemplate.fxml"));
            feed.getChildren().add(post);
            Parent post1 = FXMLLoader.load(getClass().getResource("/view/postTemplate.fxml"));
            feed.getChildren().add(post1);
            Parent post2 = FXMLLoader.load(getClass().getResource("/view/postTemplate.fxml"));
            feed.getChildren().add(post2);
            Parent post3 = FXMLLoader.load(getClass().getResource("/view/postTemplate.fxml"));
            feed.getChildren().add(post3);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
