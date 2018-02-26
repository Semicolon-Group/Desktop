/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GetSignalViewController.s1;
import static controller.MainAchref.container2;
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
import models.Enumerations;
import models.Photo;
import services.PhotoService;
import services.SignalService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GetSignalDetailsViewController implements Initializable {

    @FXML
    private Label content;
    SignalService s2 ;
    @FXML
    private Button button;
    @FXML
    private Label receiver;
    @FXML
    private Label sender;
    @FXML
    private ImageView imageSender;
    @FXML
    private ImageView imageReceiver;
    @FXML
    private Label genderSender;
    @FXML
    private Label dateSender;
    @FXML
    private Label genderReceiver;
    @FXML
    private Label dateReceiver;

    /**
     * Initializes the controller class.
     */
   
    public String getContent() {
        return content.getText();
    }   
    public String getReceiver(){
        return receiver.getText();
    }
     public String getSender(){
        return sender.getText();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            s2 = SignalService.getInstance();
            content.setText(String.valueOf(s1.getContent()));
            sender.setText(String.valueOf(s1.getSenderName()));
            receiver.setText(String.valueOf(s1.getReceiverName()));
            genderSender.setText(String.valueOf(s1.getGenderSender()? "Male" : "Female" ));
            dateSender.setText(String.valueOf(s1.getBirthDateSender()));
            genderReceiver.setText(String.valueOf(s1.getGenderReceiver()? "Male" : "Female" ));
            dateReceiver.setText(String.valueOf(s1.getBirthDateReceiver()));
            Photo photoObj = PhotoService.getInstance().get(new Photo(0, s1.getSenderId(), null , null, Enumerations.PhotoType.PROFILE));
            String photoPath="";
            if (photoObj==null){
                photoPath="/view/assets/icons/member.jpg";}
            else{
                photoPath =MySoulMate.UPLOAD_URL+photoObj.getUrl();
            }
            imageSender.setImage(new Image(photoPath));
           
            Photo photoObj2 = PhotoService.getInstance().get(new Photo(0, s1.getReceiverId(), null , null, Enumerations.PhotoType.PROFILE));
            if (photoObj2==null){
                photoPath="/view/assets/icons/member.jpg";}
            else{
                photoPath =MySoulMate.UPLOAD_URL+photoObj.getUrl();
            }
            imageReceiver.setImage(new Image(photoPath));
            
            s2.update(s1);
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GetSignalDetailsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    

    @FXML
    private void back(ActionEvent event) {
        
        container2.switchView("GetSignalView"); 
    }
    
}
