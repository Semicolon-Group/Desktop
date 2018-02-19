/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Member;
import models.Notification;
import services.MemberService;
import services.NotificationService;

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
    private Label FnameSender;
    @FXML
    private Label LnameSender;
    @FXML
    private VBox Action_element;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NotificationService ns=NotificationService.getInstance();
        Notification n=new Notification(1);
        try {
            n= ns.get(n);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        try {
          
            FnameSender.setText(n.getSenderFName());
            LnameSender.setText(n.getSenderLName());
            
            Action.setText(n.getContent());
            
          
            n_date.setText(n.getDate().toString());
            
            
            File f1=new File(n.getUrlPhoto());
            Image img1 = new Image(f1.toURI().toString());
            Img.setImage(img1);
            
            //File f2=new File("C:/Users/vaider/Documents/NetBeansProjects/Mysoulmate_2/Desktop/src/view/assets/icons/profilNotification.png");
            File f2=new File(n.getIcon());
            Image icon1 = new Image(f2.toURI().toString());
            Icon.setImage(icon1);
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificationContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
