/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GlobalViewController.online;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Photo;
import services.NewsFeed;
import services.PhotoService;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class StatusWritingViewController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private TextArea text;
    @FXML
    private Button postButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            photo.setImage(new Image(PhotoService.getInstance().getProfileUrl(online.getId())));
        } catch (SQLException ex) {
            Logger.getLogger(StatusWritingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void postButtonClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/StatusPostView.fxml"));
            Parent p = loader.load();
            loader.getLocation().openStream();
            StatusPostViewController c = (StatusPostViewController)loader.getController();
            
            c.fill(photo.getImage(), text.getText(), online.getPseudo(), "Now");
            text.clear();
            HomeViewController.getInstance().addToTopFeed(p);
            
        } catch (IOException ex) {
            Logger.getLogger(StatusWritingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
