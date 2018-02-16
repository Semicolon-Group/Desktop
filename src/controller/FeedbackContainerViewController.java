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
import javafx.scene.layout.AnchorPane;
import models.Feedback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FeedbackContainerViewController implements Initializable {

    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent feedView = FXMLLoader.load(getClass().getResource("/view/GetFeedView.fxml"));
            anchor.getChildren().add(feedView);
        } catch (IOException ex) {
            Logger.getLogger(FeedbackContainerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void switchView(String view){
        try {
            Parent feedView = FXMLLoader.load(getClass().getResource("/view/" + view + ".fxml"));
            anchor.getChildren().clear();
            anchor.getChildren().add(feedView);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
