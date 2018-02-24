/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class SettingsViewController implements Initializable {

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populate();
    }
    
    private void populate(){
        
    }

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void confirmDesactivation(MouseEvent event) {
    }
    
}
