/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Feedback;
import services.FeedbackService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutFeedbackController implements Initializable {

    @FXML
    private TextArea content;
    @FXML
    private Button btn;
    @FXML
    private Button cancelButton;
    private int senderId;
    private Stage dialog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setParams(int senderId, Stage dialog){
        this.senderId = senderId;
        this.dialog = dialog;
    }

    @FXML
    private void ajoutFeed(ActionEvent event) throws SQLException {
        
            FeedbackService f = FeedbackService.getInstance();
            Feedback f1 = new Feedback(senderId, content.getText(), false, null);
            f.create(f1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your feedback has been successfully submitted.", ButtonType.OK);
            alert.show();
            dialog.close();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        dialog.close();
    }
    
    
}
