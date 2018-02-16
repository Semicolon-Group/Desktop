/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GetFeedViewController implements Initializable {

    @FXML
    private TreeTableColumn<?, ?> Sender;
    @FXML
    private TreeTableColumn<?, ?> Date;
    @FXML
    private TreeTableColumn<?, ?> State;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
