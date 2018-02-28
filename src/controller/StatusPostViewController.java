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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import models.Comment;
import models.Enumerations;
import models.Enumerations.ReactionType;
import models.Member;
import models.Notification;
import models.Reaction;
import models.StatusPost;
import services.CommentService;
import services.MemberService;
import services.NotificationService;
import services.ReactionService;
import services.StatusPostService;
import util.N_SendMail;
import util.Notification_N;
import util.SendSMS;

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
    @FXML
    private HBox reactions;
    
    private int postId;
    private int ownerId;
    private ImageView selectedReaction;
    private Reaction r;
    @FXML
    private VBox container;
    @FXML
    private ImageView delete;

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
            ownerId = StatusPostService.getInstance().get(new StatusPost(postId)).getOwnerId();
            if (ownerId == online.getId()){
                reactions.setVisible(false);
                delete.setVisible(true);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CommentBoxView.fxml"));
            Parent p = loader.load();
            CommentBoxViewController c = (CommentBoxViewController)loader.getController();
            c.setPostId(postId);
            c.setOwnerId(StatusPostService.getInstance().get(new StatusPost(postId)).getOwnerId());
            c.fill();
            container.getChildren().add(p);
        } catch (SQLException | IOException ex) {
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
                Notification_N.sendNotifications(new Notification(online.getId(),
                        ownerId,
                        Enumerations.NotificationType.REACTION,
                        "has reacted to your post.", new Timestamp(new Date().getTime()), null, postId, 0, false));
                
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
                Notification_N.sendNotifications(new Notification(online.getId(),
                        ownerId,
                        Enumerations.NotificationType.REACTION,
                        "has reacted to your post.", new Timestamp(new Date().getTime()), null, postId, 0, false));
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
                Notification_N.sendNotifications(new Notification(online.getId(),
                        ownerId,
                        Enumerations.NotificationType.REACTION,
                        "has reacted to your post.", new Timestamp(new Date().getTime()), null, postId, 0, false));
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
                Notification_N.sendNotifications(new Notification(online.getId(),
                        ownerId,
                        Enumerations.NotificationType.REACTION,
                        "has reacted to your post.", new Timestamp(new Date().getTime()), null, postId, 0, false));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PicturePostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onPseudoClick(MouseEvent event) {
        try {
            FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/OthersProfileView.fxml");
            ((OthersProfileViewController)loader.getController()).setUserId(StatusPostService.getInstance()
                    .get(new StatusPost(postId)).getOwnerId());
        } catch (SQLException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onPhotoClick(MouseEvent event) {
        try {
            FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/OthersProfileView.fxml");
            ((OthersProfileViewController)loader.getController()).setUserId(StatusPostService.getInstance()
                    .get(new StatusPost(postId)).getOwnerId());
        } catch (SQLException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onDeleteClick(MouseEvent event) {
        try {
            StatusPostService.getInstance().delete(new StatusPost(postId));
            Notification n = new Notification();
            n.setPostId(postId);
            NotificationService.getInstance().delete(n);
            CommentService.getInstance().delete(new Comment(0, 0, postId, 0, null, null));
            GlobalViewController.getInstance().setMainContent("/view/HomeView.fxml");
        } catch (SQLException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
