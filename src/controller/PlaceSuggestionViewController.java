/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Address;
import models.PlaceSuggestion;
import services.AddressService;
import util.GooglePlacesAPI;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class PlaceSuggestionViewController implements Initializable {

    @FXML
    private Label placeNameLabel;
    @FXML
    private Label ratingLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label distanceLabel;
    @FXML
    private Label openCloseLabel;
    
    private PlaceSuggestion suggestion;
    @FXML
    private ImageView placeImageView;
    private AnchorPane parent;
    private Address origin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setParams(PlaceSuggestion suggestion, AnchorPane parent){
        try {
            this.suggestion = suggestion;
            this.origin = AddressService.getInstance().get(new Address(MySoulMate.MEMBER_ID));
            this.parent = parent;
            populateFields();
        } catch (SQLException ex) {
            Logger.getLogger(PlaceSuggestionViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateFields(){
        placeNameLabel.setText(suggestion.getName());
        addressLabel.setText(suggestion.getAddress());
        durationLabel.setText(suggestion.getDuration());
        distanceLabel.setText(suggestion.getDistance());
        openCloseLabel.setText(suggestion.isOpen()?"Ouvert":"Fermée");
        openCloseLabel.setStyle("-fx-text-fill: "+(suggestion.isOpen()?"#079d23;":"#e14b3b;"));
        ratingLabel.setText(String.valueOf(suggestion.getRating()));
        if(suggestion.getPhotoRef() != null && !suggestion.getPhotoRef().isEmpty()){
            Image image = GooglePlacesAPI.getPhoto(suggestion.getPhotoRef(), 485);
            if(image != null)
            placeImageView.setImage(image);
        }
    }

    @FXML
    private void showMap(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MapsView.fxml"));
            AnchorPane newPane = loader.load();
            parent.getChildren().add(newPane);
            ((MapsViewController)loader.getController()).setParams(
                    origin.getLatitude(), origin.getLongitude(),
                    suggestion.getLat(), suggestion.getLng(), parent);
        } catch (IOException ex) {
            Logger.getLogger(PlaceSuggestionViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
