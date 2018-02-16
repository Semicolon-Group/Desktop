/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.MainAchref.container;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.Feedback;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void traiter(ActionEvent event) {
        container.switchView("GetFeedView");
    }
    
}
