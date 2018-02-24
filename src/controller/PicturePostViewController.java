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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Enumerations;
import models.Photo;
import models.Reaction;
import models.StatusPost;
import services.PhotoService;
import services.ReactionService;
import services.StatusPostService;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class PicturePostViewController implements Initializable {

    @FXML
    private VBox post;
    @FXML
    private ImageView photo;
    @FXML
    private Label name;
    @FXML
    private Label time;
    @FXML
    private ImageView picture;
    @FXML
    private ImageView smile;
    @FXML
    private ImageView love;
    @FXML
    private ImageView laugh;
    @FXML
    private ImageView scowl;
    
    private int photoId;
    private ImageView selectedReaction;
    private Reaction r;
    @FXML
    private VBox container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void fill(Image photo, String name, String time, String picture, int photoId) {
        this.photo.setImage(photo);
        this.name.setText(name);
        this.time.setText(time);
        this.picture.setImage(new Image(picture));
        this.photoId = photoId;
        try {
            r = ReactionService.getInstance().get(new Reaction(online.getId(),0,photoId,0,null));
            if(r != null) switch (r.getReactionType()) {
                case SMILE:
                    selectedReaction = smile;
                    smile.setOpacity(1);
                    break;
                case LOVE:
                    selectedReaction = love;
                    love.setOpacity(1);
                    break;
                case LAUGH:
                    selectedReaction = laugh;
                    laugh.setOpacity(1);
                    break;
                case SCOWL:
                    selectedReaction = scowl;
                    scowl.setOpacity(1);
                    break;
                default:
                    break;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CommentBoxView.fxml"));
            Parent p = loader.load();
            CommentBoxViewController c = (CommentBoxViewController)loader.getController();
            c.setPhotoId(photoId);
            c.setOwnerId(PhotoService.getInstance().get(new Photo(photoId,0,null)).getUserId());
            c.fill();
            container.getChildren().add(p);
        } catch (SQLException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PicturePostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onSmileClick(MouseEvent event) {
        try {
            if(selectedReaction == smile){
                ReactionService.getInstance().delete(new Reaction(online.getId(), 0, photoId, 0, null));
                selectedReaction = null;
                smile.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.SMILE));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.SMILE));
                smile.setOpacity(1);
                if (selectedReaction != null)
                    selectedReaction.setOpacity(0.4);
                selectedReaction = smile;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PicturePostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onLoveClick(MouseEvent event) {
        try {
            if(selectedReaction == love){
                ReactionService.getInstance().delete(new Reaction(online.getId(), 0, photoId, 0, null));
                selectedReaction = null;
                love.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.LOVE));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.LOVE));
                love.setOpacity(1);
                if (selectedReaction != null)
                    selectedReaction.setOpacity(0.4);
                selectedReaction = love;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PicturePostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onLaughClick(MouseEvent event) {
        try {
            if(selectedReaction == laugh){
                ReactionService.getInstance().delete(new Reaction(online.getId(), 0, photoId, 0, null));
                selectedReaction = null;
                laugh.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.LAUGH));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.LAUGH));
                laugh.setOpacity(1);
                if (selectedReaction != null)
                    selectedReaction.setOpacity(0.4);
                selectedReaction = laugh;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PicturePostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onScowlClick(MouseEvent event) {
        try {
            if(selectedReaction == scowl){
                ReactionService.getInstance().delete(new Reaction(online.getId(), 0, photoId, 0, null));
                selectedReaction = null;
                scowl.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.SCOWL));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), 0, photoId, 0, Enumerations.ReactionType.SCOWL));
                scowl.setOpacity(1);
                if (selectedReaction != null)
                    selectedReaction.setOpacity(0.4);
                selectedReaction = scowl;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PicturePostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onPseudoClick(MouseEvent event) {
        try {
            FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/OthersProfileView.fxml");
            ((OthersProfileViewController)loader.getController()).setUserId(PhotoService.getInstance()
                    .get(new Photo(photoId,0,null)).getUserId());
        } catch (SQLException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onPhotoClick(MouseEvent event) {
        try {
            FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/OthersProfileView.fxml");
            ((OthersProfileViewController)loader.getController()).setUserId(PhotoService.getInstance()
                    .get(new Photo(photoId,0,null)).getUserId());
        } catch (SQLException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
