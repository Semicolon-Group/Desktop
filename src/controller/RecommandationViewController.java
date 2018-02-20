/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Address;
import models.PlaceSuggestion;
import services.AddressService;
import util.GooglePlacesAPI;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class RecommandationViewController implements Initializable {

    @FXML
    private VBox recommandationsVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populate();
    }
    
    private void populate(){
        try {
            Address address = AddressService.getInstance().get(new Address(MySoulMate.MEMBER_ID));
            List<PlaceSuggestion> suggestions = GooglePlacesAPI.getNearByPlaces(address, GooglePlacesAPI.TYPE.REST, 1000);
            for(PlaceSuggestion suggestion : suggestions){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceSuggestionView.fxml"));
                AnchorPane pane = loader.load();
                ((PlaceSuggestionViewController)loader.getController()).setSuggestion(suggestion);
                recommandationsVBox.getChildren().add(pane);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, RecommandationViewController.class.getName(), null);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, RecommandationViewController.class.getName(), null);
        }
    }
    
}
