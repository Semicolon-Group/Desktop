/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.MainAchref.container4;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import models.Signal;
import services.SignalService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterSignalDetailsViewController implements Initializable {

    @FXML
    private TextArea content;
    @FXML
    private Button envoyer;
    private Signal signal;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        public void setSignal(Signal signal) {
        this.signal = signal;
        
    }    

    @FXML
    private void envoyerSignal(ActionEvent event) throws SQLException {
        signal.setContent(content.getText());
        SignalService.getInstance().create(signal);
         Platform.exit();
        
    }

    @FXML
    private void back(ActionEvent event) {
        container4.switchView("AjouterSignalView");
    }
    
}
