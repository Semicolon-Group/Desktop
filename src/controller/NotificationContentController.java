/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GlobalViewController.online;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.Timestamp;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import models.Enumerations;
import models.Enumerations.NotificationType;
import static models.Enumerations.NotificationType.LIKE;
import static models.Enumerations.NotificationType.MESSAGE;
import static models.Enumerations.NotificationType.REACTION;
import static models.Enumerations.NotificationType.SIGNAL;
import models.Enumerations.PhotoType;
import models.Member;
import models.Notification;
import models.Photo;
import models.PicturePost;
import models.StatusPost;
import services.MemberService;
import services.NotificationService;
import services.PhotoService;
import services.PicturePostService;
import services.StatusPostService;
import util.ShowNotification;
import util.TimeDiff;

/**
 * FXML Controller class
 *
 * @author vaider
 */
public class NotificationContentController implements Initializable {

    @FXML
    private AnchorPane NotificationElement;
    @FXML
    private HBox Img_element;
    @FXML
    private VBox nameSender_element;
    @FXML
    private Label nameSender;
    @FXML
    private Label Action;
    @FXML
    private HBox Icon_element;
    @FXML
    private ImageView Icon;
    @FXML
    private HBox n_date_element;
    @FXML
    private Label n_date;
    @FXML
    private ImageView Img;
    @FXML
    private VBox Action_element;
    
    private int photoId;
    private int postId;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void fill(Notification n) {

        try {
            nameSender.setText(n.getSenderFName()+" "+n.getSenderLName());
            Action.setText(n.getContent());
            
            n_date.setText(TimeDiff.getInstance(n.getDate(),new Timestamp(new java.util.Date().getTime())).getTimeDiffString());

            Image img1 = new Image(MySoulMate.UPLOAD_URL + n.getUrlPhoto());
            Img.setImage(img1);

            Image icon1 = new Image(iconType(n.getType()));
            Icon.setImage(icon1);

        } catch (SQLException ex) {
            Logger.getLogger(NotificationContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
    }

    public String iconType(NotificationType type) {
            String path = "/view/assets/icons/";
            if (type == MESSAGE) {
                path += "Message.png";
            } else if (type == LIKE) {
                path += "Like.png";
            } else if (type == REACTION) {
                path += "Reaction.png";
            } else if (type == SIGNAL) {
                path += "Signal.png";
            } else {
                path += "Feedback.png";
            }
            return path;

    }

    @FXML
    private void goToPost(MouseEvent event) {
        try {
            String path;
            Image photo = new Image(MySoulMate.UPLOAD_URL + PhotoService.getInstance()
                    .get(new Photo(0,online.getId(),null,null,PhotoType.PROFILE)).getUrl());
            if(postId != 0){
                path = "/view/StatusPostView.fxml";
                FXMLLoader loader = GlobalViewController.getInstance().setMainContent(path);
                StatusPostViewController c = (StatusPostViewController)loader.getController();
                StatusPost post = StatusPostService.getInstance().get(new StatusPost(postId));
                c.fill(photo, post.getContent(), online.getPseudo(),
                    TimeDiff.getInstance(post.getDate(),new Timestamp(new java.util.Date().getTime())).getTimeDiffString(),
                    postId);
            }
            else{
                path = "/view/PicturePostView.fxml";
                FXMLLoader loader = GlobalViewController.getInstance().setMainContent(path);
                PicturePostViewController c = (PicturePostViewController)loader.getController();
                Photo picture = PhotoService.getInstance().get(new Photo(photoId,0,null));
                c.fill(photo,online.getPseudo(),
                    TimeDiff.getInstance(picture.getDate(),new Timestamp(new java.util.Date().getTime())).getTimeDiffString(),
                    MySoulMate.UPLOAD_URL + picture.getUrl(),
                    postId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

            public void setPhotoId(int photoId) {
                this.photoId = photoId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }
    
    
}
