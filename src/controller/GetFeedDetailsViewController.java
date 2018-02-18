/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.webkit.graphics.WCImage.getImage;
import static controller.GetFeedViewController.f1;
import static controller.MainAchref.container;
import java.net.URL;
import java.sql.SQLException;
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
import models.Feedback;
import models.Photo;
import services.FeedbackService;
import services.PhotoService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GetFeedDetailsViewController implements Initializable {

    @FXML
    private Label name;
    
    @FXML
    private Label date;
    
    @FXML
    private Button traiterButton;
    public static Feedback feed;
    
    @FXML
    private  TextArea content;
    
    FeedbackService feed1 ;
    @FXML
    private ImageView photo;
    /**
     * Initializes the controller class.
     */
    
     static PhotoService phserv = PhotoService.getInstance();
       
     Photo ph;
    
     public String getContent() {
        return content.getText();
       //name.setText();
        // TODO
    }    
     int sender;
     @Override
     public void initialize(URL url, ResourceBundle rb) {
        try {
            feed1 = FeedbackService.getInstance();
            feed1.update(f1);       
            
//                sender=f1.getSenderId();  
//              ph=phserv.getuserphoto(sender);
//           Image image = new Image(getClass().getResourceAsStream(ph.getUrl()));
//       
//            photo.setImage(image);
             content.setText(String.valueOf(f1.getContent()));
            
          
            // name.setText();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(GetFeedDetailsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void traiter(ActionEvent event) {
               
        container.switchView("GetFeedView");       
    }
    
}
