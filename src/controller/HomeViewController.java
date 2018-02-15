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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class HomeViewController implements Initializable {

    @FXML
    private VBox feed;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView headPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            Parent post2 = FXMLLoader.load(getClass().getResource("/view/StatusWritingView.fxml"));
            feed.getChildren().add(post2);
            Parent post = FXMLLoader.load(getClass().getResource("/view/StatusPostView.fxml"));
            feed.getChildren().add(post);
            Parent post1 = FXMLLoader.load(getClass().getResource("/view/PicturePostView.fxml"));
            feed.getChildren().add(post1);
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
