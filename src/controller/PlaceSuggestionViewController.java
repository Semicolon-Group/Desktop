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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.PlaceSuggestion;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setSuggestion(PlaceSuggestion suggestion){
        this.suggestion = suggestion;
        populateFields();
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
    
}
