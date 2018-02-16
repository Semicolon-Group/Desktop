/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutFeed(ActionEvent event) throws SQLException {
        
              FeedbackService f = FeedbackService.getInstance();
        Feedback f1 = new Feedback(content.getText());
        f.create(f1);
        
    }
    
}
