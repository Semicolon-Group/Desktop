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
import java.sql.Timestamp;
import java.util.Date;
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
import models.Enumerations;
import models.Enumerations.PhotoType;
import models.Photo;
import models.StatusPost;
import services.NewsFeed;
import services.PhotoService;
import services.StatusPostService;

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
            photo.setImage(new Image(PhotoService.getInstance().get(new Photo(online.getId(),PhotoType.PROFILE)).getUrl()));
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
            
            StatusPost sp = StatusPostService.getInstance().create(new StatusPost(text.getText(), online.getId(), new Timestamp(new Date().getTime())));
            c.fill(photo.getImage(), text.getText(), online.getPseudo(), "Now", sp.getId());
            HomeViewController.getInstance().addToTopFeed(p);
            text.clear();
            
        } catch (IOException | SQLException ex) {
            Logger.getLogger(StatusWritingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
