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
import models.Enumerations.NotificationType;
import static models.Enumerations.NotificationType.LIKE;
import static models.Enumerations.NotificationType.MESSAGE;
import static models.Enumerations.NotificationType.REACTION;
import static models.Enumerations.NotificationType.SIGNAL;
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

    }

    public void fill(Notification n) {

        try {
            FnameSender.setText(n.getSenderFName());
            LnameSender.setText(n.getSenderLName());
            Action.setText(n.getContent());
            n_date.setText(n.getDate().toString());

//                File f1=new File(n.getUrlPhoto());
            Image img1 = new Image(MySoulMate.UPLOAD_URL + n.getUrlPhoto());
            Img.setImage(img1);

//                File f2=new File(n.getIcon());
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
            path += "LIKE.png";
        } else if (type == REACTION) {
            path += "Reaction.png";
        } else if (type == SIGNAL) {
            path += "Signal.png";
        } else {
            path += "Feedback.png";
        }
        return path;

    }

}
