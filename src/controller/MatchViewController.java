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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import models.Enumerations;
import models.Enumerations.BodyType;
import models.Enumerations.LastLogin;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import models.MatchCard;
import services.Filter;
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
    private ComboBox<String> sort;
    @FXML
    private FlowPane result;
    
    private List<MatchCard> cards;
    @FXML
    private ComboBox<String> ageMin;
    @FXML
    private ComboBox<String> ageMax;
    @FXML
    private ComboBox<String> heightMin;
    @FXML
    private ComboBox<String> heightMax;
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
    private ComboBox<String> distance;
    @FXML
    private ComboBox<String> login;
    @FXML
    private HBox smokeYes;
    @FXML
    private HBox smokeNo;
    @FXML
    private HBox drinkYes;
    @FXML
    private HBox drinkNo;
    
    private Filter f;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        f = new Filter();
        
        ageMin.getItems().add("");
        ageMax.getItems().add("");
        for(int i=18; i < 91; i++){
            ageMin.getItems().add("" + i);
            ageMax.getItems().add("" + i);
        }
        
        heightMin.getItems().add("");
        heightMax.getItems().add("");
        for(int i=150; i < 220; i++){
            heightMin.getItems().add("" + i);
            heightMax.getItems().add("" + i);
        }
        
        login.getItems().add("");
        for(LastLogin ll : LastLogin.values()){
            login.getItems().add(ll.toString());
        }
        
        distance.getItems().addAll("", "< 10km","< 50km", "< 100km","< 250km", "< 500km", "Partout");
        
        sort.getItems().addAll("Match %","Distance","Last Login");
        sort.setValue("Match %");
        
        if(online.isGender()){
            divorced.setText("Divorcée");
            widow.setText("Veuve");
        }
        try {
            fill(f);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void fill(Filter F) throws SQLException, IOException{
        result.getChildren().clear();
        cards = Matching.getInstance().getMatches(online,F);
        for(MatchCard card : cards){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MatchCardView.fxml"));
            Parent p = loader.load();
            MatchCardViewController c = loader.getController();
            c.setCard(card);
            c.fill();
            result.getChildren().add(p);
        }
    }
    
    @FXML
    private void onSearchButtonClick(MouseEvent event) {
        if(mince.isSelected())
            f.getBodyType().add(BodyType.MINCE);
        if(forme.isSelected())
            f.getBodyType().add(BodyType.FORME);
        if(gros.isSelected())
            f.getBodyType().add(BodyType.GROS);
        if(islam.isSelected())
            f.getReligion().add(Enumerations.Religion.ISLAM);
        if(juda.isSelected())
            f.getReligion().add(Enumerations.Religion.JUDAISME);
        if(christ.isSelected())
            f.getReligion().add(Enumerations.Religion.CHRISTIANISME);
        if(atheis.isSelected())
            f.getReligion().add(Enumerations.Religion.ATHEISME);
        if(agnos.isSelected())
            f.getReligion().add(Enumerations.Religion.AGNOSTICISM);
        if(single.isSelected())
            f.getMaritalStatus().add(MaritalStatus.CELIBATAIRE);
        if(divorced.isSelected())
            f.getMaritalStatus().add(MaritalStatus.DIVORCE);
        if(widow.isSelected())
            f.getMaritalStatus().add(MaritalStatus.VEUF);
        if(!ageMin.getSelectionModel().isEmpty() && !ageMin.getValue().equals(""))
            f.setAgeMin(Integer.parseInt(ageMin.getValue()));
        if(!ageMax.getSelectionModel().isEmpty() && !ageMax.getValue().equals(""))
            f.setAgeMax(Integer.parseInt(ageMax.getValue()));
        if(!heightMax.getSelectionModel().isEmpty() && !heightMin.getValue().equals(""))
            f.setHeightMax(Integer.parseInt(heightMax.getValue().substring(0, heightMax.getValue().length() - 3)));
        if(!heightMin.getSelectionModel().isEmpty() && !heightMax.getValue().equals(""))
            f.setHeightMin(Integer.parseInt(heightMin.getValue().substring(0, heightMin.getValue().length() - 3)));
        if(!login.getSelectionModel().isEmpty() && !login.getValue().equals(""))
            f.setLastLogin(LastLogin.valueOf(login.getValue()));
        if(!distance.getSelectionModel().isEmpty()){
            if(!distance.getValue().equals("Partout") && !distance.getValue().equals("")){
                f.setDistance(Double.parseDouble(distance.getValue().substring(2, distance.getValue().length() - 2)));
                System.out.println(distance.getValue().substring(2, distance.getValue().length() - 2));
            }
        }  
        
        try {
            fill(f);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onCancelButtonClick(MouseEvent event) {
        
    }

    @FXML
    private void onSmokeYesClick(MouseEvent event) {
        if(f.getSmokes() == 1){
            smokeYes.getStyleClass().removeAll("yesBoxSelected");
            f.setSmokes(-1);
        }
        else{
            smokeYes.getStyleClass().addAll("yesBoxSelected");
            f.setSmokes(1);
        }
    }

    @FXML
    private void onSmokeNoClick(MouseEvent event) {
        if(f.getSmokes() == 0)
            f.setSmokes(-1);
        else
            f.setSmokes(0);
    }

    @FXML
    private void onDrinkYesClick(MouseEvent event) {
        if(f.getDrinks() == 1)
            f.setDrinks(-1);
        else
            f.setDrinks(1);
    }

    @FXML
    private void onDrinkNoClick(MouseEvent event) {
        if(f.getDrinks() == 0)
            f.setDrinks(-1);
        else
            f.setDrinks(0);
    }
    
}
