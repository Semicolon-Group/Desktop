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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Comment;
import models.Enumerations;
import models.Member;
import models.Notification;
import models.StatusPost;
import services.CommentService;
import services.MemberService;
import services.StatusPostService;
import util.Notification_N;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class CommentBoxViewController implements Initializable {

    @FXML
    private VBox box;
    @FXML
    private TextArea comment;
    private int postId;
    private int photoId;
    private int selected;
    private int ownerId;
    @FXML
    private Button send;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void fill(){
        try {
            box.getChildren().clear();
            
            if(ownerId == online.getId())
                comment.setEditable(false);
            
            Comment c = new Comment();
            c.setPostId(postId);
            c.setPhotoId(photoId);
            if(postId != 0 && online.getId() != ownerId){
                c.setReceiverId(online.getId());
                c.setSenderId(ownerId);
            }
            if(photoId != 0 && online.getId() != ownerId){
                c.setReceiverId(online.getId());
                c.setSenderId(ownerId);
            }
            List<Comment> comments = CommentService.getInstance().getAll(c);
            for(Comment comment : comments){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CommentView.fxml"));
                Parent p = loader.load();
                CommentViewController cont = (CommentViewController)loader.getController();
                cont.setBox(this);
                cont.setComment(comment);
                cont.setPseudo(MemberService.getInstance().get(new Member(comment.getSenderId())).getPseudo());
                cont.setContent(comment.getContent());
                if(online.getId() == comment.getSenderId())
                    cont.startListening();
                box.getChildren().add(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(StatusPostViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommentBoxViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
    
    public void setSelected(int id, String pseudo){
        if(ownerId != online.getId())
            return;
        if(selected == id){
            selected = 0;
            comment.setEditable(false);
            comment.clear();
        }
        else{
            selected = id;
            comment.setEditable(true);
            comment.setText("@" + pseudo + " ");
        }
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        if(ownerId != online.getId())
            this.selected = ownerId;
    }

    @FXML
    private void onSendClick(MouseEvent event) {
        if(selected == 0)
            return;
        if(comment.getText().equals(""))
            return;
        try {
            Comment c = CommentService.getInstance().create(new Comment(online.getId(),selected,postId,photoId,comment.getText(),
                    new Timestamp(new Date().getTime())));
	    if(online.getId() != ownerId){
		String notifText;
		if(postId != 0){
		    notifText = "has commented on your post.";
		}else{
		    notifText = "has commented on your photo.";
		}
		Notification_N.sendNotifications(new Notification(online.getId(),
                        ownerId,
                        Enumerations.NotificationType.COMMENT,
                        notifText, new Timestamp(new Date().getTime()), null, postId, photoId, false));
	    }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CommentView.fxml"));
            Parent p = loader.load();
            CommentViewController cont = (CommentViewController)loader.getController();
            cont.setBox(this);
            cont.setComment(c);
            cont.setPseudo(online.getPseudo());
            cont.setContent(comment.getText());
            cont.startListening();
            selected = 0;
            comment.clear();
            box.getChildren().add(0,p);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CommentBoxViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
