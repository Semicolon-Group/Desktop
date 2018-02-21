/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Enumerations;
import models.Enumerations.PhotoType;
import models.Like;
import models.Member;
import models.Photo;
import services.MemberService;
import services.PhotoService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class LikedUserViewController implements Initializable {

    @FXML
    private ImageView memberImage;
    @FXML
    private Label memberName;
    @FXML
    private Label likeDate;
    
    private Like like;
    
    private Member member;
    private GlobalViewController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setLike (Like like) {
        this.like = like;
        populate();
    }

    public void setController(GlobalViewController controller) {
        this.controller = controller;
    }
    
    private void populate(){
        try {
            member = MemberService.getInstance().get(new Member(like.getReceiverId()));
            Photo photo = PhotoService.getInstance().get(new Photo(0, member.getId(), null, null, PhotoType.PROFILE));
            
            String photoPath="";
            if(photo == null){
                photoPath = "/view/assets/icons/member.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photo.getUrl();
            }
            memberImage.setImage(new Image(photoPath));
            memberName.setText(member.getFirstname()+" "+member.getLastname());
            likeDate.setText((new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(like.getDate())));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, LikedUserViewController.class.getName(), null);
        }
    }

    @FXML
    private void toOtherProfile(MouseEvent event) {
        if(member.getId() == MySoulMate.MEMBER_ID){
            controller.setMainContent("/view/SelfProfileView.fxml");
        }else{
            FXMLLoader loader = controller.setMainContent("/view/OthersProfileView.fxml");
            ((OthersProfileViewController)loader.getController()).setUserId(member.getId());
        }
    }
    
}
