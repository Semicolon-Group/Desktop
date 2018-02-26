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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Comment;
import services.CommentService;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class CommentViewController implements Initializable {

    @FXML
    private Label pseudo;
    
    private CommentBoxViewController box;
    private Comment comment;
    @FXML
    private TextArea content;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView reply;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    @FXML
    private void onPseudoClick(MouseEvent event) {
        FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/OthersProfileView.fxml");
        ((OthersProfileViewController)loader.getController()).setUserId(comment.getSenderId());
    }
    
    public void startListening(){
        content.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldProperty, Boolean newProperty) {
                if(online.getId() != comment.getSenderId())
                    return;
                if(newProperty)
                    content.setEditable(true);
                else{
                    content.setEditable(false);
                    try {
                        comment.setContent(content.getText());
                        CommentService.getInstance().update(comment);
                    } catch (SQLException ex) {
                        Logger.getLogger(CommentViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void setBox(CommentBoxViewController box){
        this.box = box;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        if(comment.getSenderId() == online.getId())
            delete.setVisible(true);
        else if(box.getOwnerId() == online.getId())
            reply.setVisible(true);
    }

    public void setPseudo(String pseudo) {
        this.pseudo.setText(pseudo);
    }

    public void setContent(String content) {
        this.content.setText(content);
    }

    @FXML
    private void onDeleteClick(MouseEvent event) {
        try {
            CommentService.getInstance().delete(comment);
            box.fill();
        } catch (SQLException ex) {
            Logger.getLogger(CommentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onReplyClick(MouseEvent event) {
        if(comment.getSenderId() != online.getId())
            box.setSelected(comment.getSenderId(), pseudo.getText());
    }
}
