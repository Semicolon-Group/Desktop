/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class MatchViewController implements Initializable {

    @FXML
    private HBox filter;
    @FXML
    private ComboBox<?> sort;
    @FXML
    private FlowPane result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/view/MatchCardView.fxml"));
            Parent p1 = FXMLLoader.load(getClass().getResource("/view/MatchCardView.fxml"));
            Parent p2 = FXMLLoader.load(getClass().getResource("/view/MatchCardView.fxml"));
            Parent p3 = FXMLLoader.load(getClass().getResource("/view/MatchCardView.fxml"));
            result.getChildren().add(p);
            result.getChildren().add(p1);
            result.getChildren().add(p2);
            result.getChildren().add(p3);
        } catch (IOException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
