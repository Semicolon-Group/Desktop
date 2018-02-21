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
import models.MatchCard;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class MatchCardViewController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Label pseudo;
    @FXML
    private Label age;
    @FXML
    private Label city;
    @FXML
    private Label match;
    @FXML
    private Label enemy;
    
    private MatchCard card;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void fill(){
        photo.setImage(new Image(card.getPhotoUrl()));
        pseudo.setText(card.getPseudo());
        age.setText(Integer.toString(card.getAge()));
        city.setText(card.getCity());
        match.setText(Integer.toString(card.getMatch()) + "%");
        enemy.setText(Integer.toString(card.getEnemy()) + "%");
    }

    public MatchCard getCard() {
        return card;
    }

    public void setCard(MatchCard card) {
        this.card = card;
    }
    
}
