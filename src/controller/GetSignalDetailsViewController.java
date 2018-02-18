/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GetSignalViewController.s1;
import static controller.MainAchref.container2;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import services.SignalService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GetSignalDetailsViewController implements Initializable {

    @FXML
    private TextArea content;
    SignalService s2 ;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
   
    public String getContent() {
        return content.getText();
      
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            s2 = SignalService.getInstance();
            content.setText(String.valueOf(s1.getContent()));

    }    

    @FXML
    private void back(ActionEvent event) {
        container2.switchView("GetSignalView"); 
    }
    
}
