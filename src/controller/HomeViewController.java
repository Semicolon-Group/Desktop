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
import models.Member;
import models.Photo;
import models.PicturePost;
import models.Post;
import models.StatusPost;
import services.MemberService;
import services.NewsFeed;
import services.PhotoService;

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
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/view/StatusWritingView.fxml"));
            feed.getChildren().add(root);
            for(Post p : NewsFeed.getInstance().getFeed(online)){
                loader = new FXMLLoader();
                PicturePostViewController ppc;
                StatusPostViewController spc;
                if(p instanceof StatusPost){
                    loader.setLocation(getClass().getResource("/view/StatusPostView.fxml"));
                    root = loader.load();
                    loader.getLocation().openStream();
                    spc = (StatusPostViewController)loader.getController();
                    spc.fill(new Image(PhotoService.getInstance().get(new Photo(p.getOwnerId())).getUrl()),
                            ((StatusPost)p).getContent(),MemberService.getInstance().get(new Member(p.getOwnerId())).getPseudo(),
                            p.getDate().toString());
                }
                else if(p instanceof PicturePost){
                    loader.setLocation(getClass().getResource("/view/PicturePostView.fxml"));
                    root = loader.load();
                    loader.getLocation().openStream();
                    ppc = (PicturePostViewController)loader.getController();
                    ppc.fill(new Image(PhotoService.getInstance().get(new Photo(p.getOwnerId())).getUrl()),
                        MemberService.getInstance().get(new Member(p.getOwnerId())).getPseudo(),
                        p.getDate().toString(), ((PicturePost)p).getUrl());
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
