/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Enumerations.BodyType;
import models.Enumerations.Importance;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import models.Enumerations.RelationType;
import models.Enumerations.Religion;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriptionDetailsViewController implements Initializable {

    @FXML
    private ComboBox<String> bodyBox;
    ObservableList<String> bodyList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> religionBox;
    ObservableList<String> religionList = FXCollections.observableArrayList();

    @FXML
    private TextField Height;
    @FXML
    private ComboBox<String> importanceBox;
    ObservableList<String> importanceList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> maritalBox;
        ObservableList<String> maritalList = FXCollections.observableArrayList();

    @FXML
    private TextField Address;
    @FXML
    private TextField ChildrenNumber;
    @FXML
    private ComboBox<String> relationBox;
        ObservableList<String> relationList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> proximityBox;
        ObservableList<String> proximityList = FXCollections.observableArrayList();

    @FXML
    private TextField Min_age;
    @FXML
    private TextField Max_age;
    @FXML
    private Button confirmer;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            for (BodyType b : BodyType.values()){
            bodyList.add(b.toString());
        }
        bodyBox.setItems(bodyList);
      
           for (Religion r : Religion.values())
           {
            religionList.add(r.toString());
        }
        religionBox.setItems(religionList);
        
        
                for (Importance i : Importance.values())
           {
            importanceList.add(i.toString());
        }
        importanceBox.setItems(importanceList);
        
           for (MaritalStatus m : MaritalStatus.values())
           {
            maritalList.add(m.toString());
        }
        maritalBox.setItems(maritalList);
        
        for (RelationType t : RelationType.values())
           {
            relationList.add(t.toString());
        }
        relationBox.setItems(relationList);
        
         for (Proximity p : Proximity.values())
           {
            proximityList.add(p.toString());
        }
        proximityBox.setItems(proximityList);
        
    }   
    
    
    

    @FXML
    private void AjouterMembre(ActionEvent event) {
    }
    
}
