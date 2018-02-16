/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import models.Enumerations;
import models.Enumerations.SignalReason;
import models.Signal;
import services.SignalService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutSignalController implements Initializable {

    @FXML
    private Button btn2;
    @FXML
    private ComboBox<String> ReasonBox;
    ObservableList<String> ReasonList = FXCollections.observableArrayList();
    @FXML
    private TextArea content;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            for (SignalReason i : SignalReason.values()){
            ReasonList.add(i.toString());
        }
        ReasonBox.setItems(ReasonList);
    }    
     
    @FXML
    private void ajoutSignal(ActionEvent event) throws SQLException {
        
        
        SignalService s = SignalService.getInstance();
        Signal s1 = new Signal(SignalReason.valueOf(ReasonBox.getValue()), content.getText());
        s.create(s1);
        
         
    }
    
}
