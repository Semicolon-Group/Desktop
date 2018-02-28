/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GlobalViewController.online;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import models.Enumerations;
import models.Enumerations.PhotoType;
import models.Member;
import models.Photo;
import models.PicturePost;
import models.Post;
import models.StatusPost;
import services.MemberService;
import services.NewsFeed;
import services.PhotoService;
import util.TimeDiff;

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

    private static HomeViewController instance;
    
    public HomeViewController(){
        instance = this;
    }
    
    public static HomeViewController getInstance(){
        if (instance == null)
            instance = new HomeViewController();
        return instance;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fill();
    }
    
    public void fill(){
        try {
            feed.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/view/StatusWritingView.fxml"));
            feed.getChildren().add(root);
            for(Post p : NewsFeed.getInstance().getFeed(online)){
                loader = new FXMLLoader();
                PicturePostViewController ppc;
                StatusPostViewController spc;
                Image image;
                if(p instanceof StatusPost){
                    loader.setLocation(getClass().getResource("/view/StatusPostView.fxml"));
                    root = loader.load();
                    loader.getLocation().openStream();
                    spc = (StatusPostViewController)loader.getController();
                    Photo photo = PhotoService.getInstance().get(new Photo(0,p.getOwnerId(),null,null,PhotoType.PROFILE));
                    if(photo == null){
                        image = new Image("/view/assets/icons/member.jpg");
                    }else{
                        image = new Image(MySoulMate.UPLOAD_URL + photo.getUrl());
                    }
                    spc.fill(image,
                        ((StatusPost)p).getContent(), MemberService.getInstance().get(new Member(p.getOwnerId())).getPseudo(),
                        TimeDiff.getInstance(p.getDate(),new Timestamp(new java.util.Date().getTime())).getTimeDiffString(),
                        p.getId());
                }
                else if(p instanceof PicturePost){
                    loader.setLocation(getClass().getResource("/view/PicturePostView.fxml"));
                    root = loader.load();
                    loader.getLocation().openStream();
                    ppc = (PicturePostViewController)loader.getController();
                    Photo photo = PhotoService.getInstance().get(new Photo(0,p.getOwnerId(),null,null,PhotoType.PROFILE));
                    if(photo == null){
                        image = new Image("/view/assets/icons/member.jpg");
                    }else{
                        image = new Image(MySoulMate.UPLOAD_URL + photo.getUrl());
                    }
                    ppc.fill(image,
                        MemberService.getInstance().get(new Member(p.getOwnerId())).getPseudo(),
                        TimeDiff.getInstance(p.getDate(),new Timestamp(new java.util.Date().getTime())).getTimeDiffString(),
                        MySoulMate.UPLOAD_URL + ((PicturePost)p).getUrl(), ((PicturePost)p).getPhotoId());
                }
                feed.getChildren().add(root);
            }
            
        } catch (IOException | SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addToTopFeed(Parent p){
        feed.getChildren().add(1, p);
    }
}
