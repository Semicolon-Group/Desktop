/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GlobalViewController.online;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import models.Enumerations;
import models.Enumerations.ReactionType;
import models.Reaction;
import models.StatusPost;
import services.ReactionService;
import services.StatusPostService;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class StatusPostViewController implements Initializable {

    private VBox post;
    @FXML
    private ImageView photo;
    @FXML
    private Label name;
    @FXML
    private Label time;
    @FXML
    private TextArea status;
    @FXML
    private ImageView smile;
    @FXML
    private ImageView love;
    @FXML
    private ImageView laugh;
    @FXML
    private ImageView scowl;
    @FXML
    private VBox statusPost;
    
    private int postId;
    @FXML
    private HBox reactions;
    
    private ImageView selectedReaction;
    private Reaction r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void fill(Image photo, String text, String name, String time, int postId){
        this.photo.setImage(photo);
        this.status.setText(text);
        this.name.setText(name);
        this.time.setText(time);
        this.postId = postId;
        try {
            if (StatusPostService.getInstance().get(new StatusPost(postId)).getOwnerId() == online.getId()){
                reactions.setVisible(false);
            }
            r = ReactionService.getInstance().get(new Reaction(online.getId(),postId,0,0,null));
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
        } catch (SQLException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void onSmileClick(MouseEvent event) {
        try {
            if(selectedReaction == smile){
                ReactionService.getInstance().delete(new Reaction(online.getId(), postId, 0, 0, null));
                selectedReaction = null;
                smile.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.SMILE));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.SMILE));
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
                ReactionService.getInstance().delete(new Reaction(online.getId(), postId, 0, 0, null));
                selectedReaction = null;
                love.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.LOVE));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.LOVE));
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
                ReactionService.getInstance().delete(new Reaction(online.getId(), postId, 0, 0, null));
                selectedReaction = null;
                laugh.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.LAUGH));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.LAUGH));
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
                ReactionService.getInstance().delete(new Reaction(online.getId(), postId, 0, 0, null));
                selectedReaction = null;
                scowl.setOpacity(0.4);
            }
            else{
                if(r == null)
                    ReactionService.getInstance().create(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.SCOWL));
                else
                    ReactionService.getInstance().update(new Reaction(online.getId(), postId, 0, 0, Enumerations.ReactionType.SCOWL));
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
    }
    
}
