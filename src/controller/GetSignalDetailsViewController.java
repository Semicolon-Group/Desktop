/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GetSignalViewController.s1;
import static controller.MainAchref.container2;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label receiver;
    @FXML
    private Label sender;

    /**
     * Initializes the controller class.
     */
   
    public String getContent() {
        return content.getText();
    }   
    public String getReceiver(){
        return receiver.getText();
    }
     public String getSender(){
        return sender.getText();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            s2 = SignalService.getInstance();
            content.setText(String.valueOf(s1.getContent()));
            sender.setText(String.valueOf(s1.getSenderName()));
            receiver.setText(String.valueOf(s1.getReceiverName()));
            s2.update(s1);
            
        } catch (SQLException ex) {
            Logger.getLogger(GetSignalDetailsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    

    @FXML
    private void back(ActionEvent event) {
        
        container2.switchView("GetSignalView"); 
    }
    
}
