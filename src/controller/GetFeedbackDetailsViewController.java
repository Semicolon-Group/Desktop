/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.webkit.graphics.WCImage.getImage;
import static controller.GetFeedViewController.f1;
import static controller.MainAchref.container;
import static controller.MySoulMate.UPLOAD_URL;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Enumerations;
import models.Feedback;
import models.Member;
import models.Photo;
import services.FeedbackService;
import services.MemberService;
import services.PhotoService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GetFeedbackDetailsViewController implements Initializable {

    @FXML
    private Label name;
    
    @FXML
    private Label gender;
    
    @FXML
    private Button traiterButton;
    public static Feedback feed;
    
    
    FeedbackService feed1 ;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label date;
    private Label Content;
    @FXML
    private Label content;
    @FXML
    private ImageView profileph;
    private FeedbackContainerViewController container;
  
        
    public String getContent(){
        return Content.getText();      
    }
       //name.setText();
        // TODO
      
     int sender;
     @Override
     public void initialize(URL url, ResourceBundle rb) {
        
        try {
            feed1 = FeedbackService.getInstance();
            feed1.update(f1);
            Photo photoObj = PhotoService.getInstance().get(new Photo(0, f1.getSenderId(), null , null, Enumerations.PhotoType.PROFILE));
            String photoPath="";
            if (photoObj==null){
                photoPath="/view/assets/icons/member.jpg";}
            else{
                photoPath =MySoulMate.UPLOAD_URL+photoObj.getUrl();
            }
            profileph.setImage(new Image(photoPath));
            
            content.setText(String.valueOf(f1.getContent()));
            name.setText(String.valueOf(f1.getSenderName()));
            gender.setText(String.valueOf(f1.getGender()? "Male" : "Female" ));
            date.setText(String.valueOf(f1.getBirthDate()));
//            Image img1 = new Image(UPLOAD_URL + feed.getUrlPhoto());
//            profileph.setImage(img1);
            // name.setText();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(GetFeedbackDetailsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
     
    public void setContainer(FeedbackContainerViewController container){
        this.container = container;
    }

    @FXML
    private void Return(ActionEvent event) {
        container.switchView("GetFeedView"); 
    }
    
}
