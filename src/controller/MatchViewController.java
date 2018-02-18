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
import javafx.scene.control.ComboBox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                //System.out.println(card);
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
