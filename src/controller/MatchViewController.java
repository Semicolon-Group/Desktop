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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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

    private List<MatchCard> cards;
    private Filter f;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        f = new Filter();

        ageMin.getItems().add("");
        ageMax.getItems().add("");
        for (int i = 18; i < 91; i++) {
            ageMin.getItems().add("" + i);
            ageMax.getItems().add("" + i);
        }

        heightMin.getItems().add("");
        heightMax.getItems().add("");
        for (int i = 150; i < 220; i++) {
            heightMin.getItems().add("" + i + " cm");
            heightMax.getItems().add("" + i + " cm");
        }

        login.getItems().add("");
        for (LastLogin ll : LastLogin.values()) {
            login.getItems().add(ll.toString());
        }

        distance.getItems().addAll("", "< 10km", "< 50km", "< 100km", "< 250km", "< 500km", "Partout");

        sort.getItems().addAll("Match %", "Distance", "Last Online");
        sort.setValue("Match %");

        if (online.isGender()) {
            widow.setText("Widow");
        }
        try {
            cards = Matching.getInstance().getMatches(online, f);
            fill();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sort.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue.equals("Match %")) {
                cards.sort((a, b) -> b.getMatch() - a.getMatch());
            }
            if (newValue.equals("Distance")) {
                cards.sort((a, b) -> (int) (a.getDistance() - b.getDistance()));
            }
            if (newValue.equals("Last Online")) {
                cards.sort((a, b) -> a.getLastLogin() - b.getLastLogin());
            }
            try {
                fill();
            } catch (SQLException | IOException ex) {
                Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ageMin.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("")) {
                    Platform.runLater(() -> ageMin.getSelectionModel().clearSelection());
                }
            }
        });
        ageMax.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("")) {
                    Platform.runLater(() -> ageMax.getSelectionModel().clearSelection());
                }
            }
        });
        heightMin.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("")) {
                    Platform.runLater(() -> heightMin.getSelectionModel().clearSelection());
                }
            }
        });
        heightMax.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("")) {
                    Platform.runLater(() -> heightMax.getSelectionModel().clearSelection());
                }
            }
        });
        distance.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("")) {
                    Platform.runLater(() -> distance.getSelectionModel().clearSelection());
                }
            }
        });
        login.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("")) {
                    Platform.runLater(() -> login.getSelectionModel().clearSelection());
                }
            }
        });
    }

    private void fill() throws SQLException, IOException {
        result.getChildren().clear();
        for (MatchCard card : cards) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MatchCardView.fxml"));
            Parent p = loader.load();
            MatchCardViewController c = loader.getController();
            c.setCard(card);
            c.fill();
            result.getChildren().addAll(p);
        }
    }

    @FXML
    private void onSearchButtonClick(MouseEvent event) {
        Filter tempF = new Filter();
        tempF.setSmokes(f.getSmokes());
        tempF.setDrinks(f.getDrinks());
        f = tempF;
        if (mince.isSelected()) {
            f.getBodyType().add(BodyType.THIN);
        }
        if (forme.isSelected()) {
            f.getBodyType().add(BodyType.FIT);
        }
        if (gros.isSelected()) {
            f.getBodyType().add(BodyType.CURVY);
        }
        if (islam.isSelected()) {
            f.getReligion().add(Enumerations.Religion.ISLAM);
        }
        if (juda.isSelected()) {
            f.getReligion().add(Enumerations.Religion.JUDAISM);
        }
        if (christ.isSelected()) {
            f.getReligion().add(Enumerations.Religion.CHRISTIANITY);
        }
        if (atheis.isSelected()) {
            f.getReligion().add(Enumerations.Religion.ATHEISM);
        }
        if (agnos.isSelected()) {
            f.getReligion().add(Enumerations.Religion.AGNOSTICISM);
        }
        if (single.isSelected()) {
            f.getMaritalStatus().add(MaritalStatus.SINGLE);
        }
        if (divorced.isSelected()) {
            f.getMaritalStatus().add(MaritalStatus.DIVORCED);
        }
        if (widow.isSelected()) {
            f.getMaritalStatus().add(MaritalStatus.WIDOW);
        }
        if (!ageMin.getSelectionModel().isEmpty() && !ageMin.getValue().equals("")) {
            f.setAgeMin(Integer.parseInt(ageMin.getValue()));
        }
        if (!ageMax.getSelectionModel().isEmpty() && !ageMax.getValue().equals("")) {
            f.setAgeMax(Integer.parseInt(ageMax.getValue()));
        }
        if (!heightMax.getSelectionModel().isEmpty() && !heightMax.getValue().equals("")) {
            f.setHeightMax(Integer.parseInt(heightMax.getValue().substring(0, heightMax.getValue().length() - 3)));
        }
        if (!heightMin.getSelectionModel().isEmpty() && !heightMin.getValue().equals("")) {
            f.setHeightMin(Integer.parseInt(heightMin.getValue().substring(0, heightMin.getValue().length() - 3)));
        }
        if (!login.getSelectionModel().isEmpty() && !login.getValue().equals("")) {
            f.setLastLogin(LastLogin.valueOf(login.getValue()));
        }
        if (!distance.getSelectionModel().isEmpty()) {
            if (!distance.getValue().equals("Partout") && !distance.getValue().equals("")) {
                f.setDistance(Double.parseDouble(distance.getValue().substring(2, distance.getValue().length() - 2)));
                System.out.println(distance.getValue().substring(2, distance.getValue().length() - 2));
            }
        }

        try {
            cards = Matching.getInstance().getMatches(online, f);
            fill();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onCancelButtonClick(MouseEvent event) {
        ageMin.getSelectionModel().clearSelection();
        ageMax.getSelectionModel().clearSelection();
        heightMin.getSelectionModel().clearSelection();
        heightMax.getSelectionModel().clearSelection();
        distance.getSelectionModel().clearSelection();
        login.getSelectionModel().clearSelection();
        mince.setSelected(false);
        forme.setSelected(false);
        gros.setSelected(false);
        single.setSelected(false);
        divorced.setSelected(false);
        widow.setSelected(false);
        islam.setSelected(false);
        christ.setSelected(false);
        juda.setSelected(false);
        atheis.setSelected(false);
        agnos.setSelected(false);
        smokeYes.getStyleClass().removeAll("yesBoxSelected");
        smokeNo.getStyleClass().removeAll("noBoxSelected");
        drinkYes.getStyleClass().removeAll("yesBoxSelected");
        drinkNo.getStyleClass().removeAll("noBoxSelected");
        try {
            f = new Filter();
            cards = Matching.getInstance().getMatches(online, f);
            fill();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MatchViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onSmokeYesClick(MouseEvent event) {
        if (f.getSmokes() == 1) {
            smokeYes.getStyleClass().removeAll("yesBoxSelected");
            f.setSmokes(-1);
        } else {
            smokeYes.getStyleClass().addAll("yesBoxSelected");
            smokeNo.getStyleClass().removeAll("noBoxSelected");
            f.setSmokes(1);
        }
    }

    @FXML
    private void onSmokeNoClick(MouseEvent event) {
        if (f.getSmokes() == 0) {
            smokeNo.getStyleClass().removeAll("noBoxSelected");
            f.setSmokes(-1);
        } else {
            smokeYes.getStyleClass().removeAll("yesBoxSelected");
            smokeNo.getStyleClass().addAll("noBoxSelected");
            f.setSmokes(0);
        }
    }

    @FXML
    private void onDrinkYesClick(MouseEvent event) {
        if (f.getDrinks() == 1) {
            drinkYes.getStyleClass().removeAll("yesBoxSelected");
            f.setDrinks(-1);
        } else {
            drinkYes.getStyleClass().addAll("yesBoxSelected");
            drinkNo.getStyleClass().removeAll("noBoxSelected");
            f.setDrinks(1);
        }
    }

    @FXML
    private void onDrinkNoClick(MouseEvent event) {
        if (f.getDrinks() == 0) {
            drinkNo.getStyleClass().removeAll("noBoxSelected");
            f.setDrinks(-1);
        } else {
            drinkYes.getStyleClass().removeAll("yesBoxSelected");
            drinkNo.getStyleClass().addAll("noBoxSelected");
            f.setDrinks(0);
        }
    }

}
