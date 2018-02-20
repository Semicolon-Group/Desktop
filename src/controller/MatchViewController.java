/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GlobalViewController.online;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import models.MatchCard;
import services.Matching;

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
    
    private List<MatchCard> cards;
    @FXML
    private ComboBox<?> ageMin;
    @FXML
    private ComboBox<?> ageMax;
    @FXML
    private ComboBox<?> heightMin;
    @FXML
    private ComboBox<?> heightMax;
    @FXML
    private CheckBox mince;
    @FXML
    private CheckBox forme;
    @FXML
    private CheckBox gros;
    @FXML
    private CheckBox islam;
    @FXML
    private CheckBox christ;
    @FXML
    private CheckBox juda;
    @FXML
    private CheckBox agnos;
    @FXML
    private CheckBox atheis;
    @FXML
    private CheckBox single;
    @FXML
    private CheckBox divorced;
    @FXML
    private CheckBox widow;
    @FXML
    private ComboBox<?> distance;
    @FXML
    private ComboBox<?> login;
    @FXML
    private HBox smokeYes;
    @FXML
    private HBox smokeNo;
    @FXML
    private HBox drinkYes;
    @FXML
    private HBox drinkNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(online.isGender()){
            divorced.setText("Divorcée");
            widow.setText("Veuve");
        }
        
        try {
            cards = Matching.getInstance().getMatches(online);
            for(MatchCard card : cards){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/MatchCardView.fxml"));
                Parent p = loader.load();
                MatchCardViewController c = loader.getController();
                c.setCard(card);
                c.fill();
                result.getChildren().add(p);
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onSearchButtonClick(MouseEvent event) {
    }

    @FXML
    private void onCancelButtonClick(MouseEvent event) {
    }
    
}
