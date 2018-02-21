/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private AnchorPane mainPane;
    @FXML
    private VBox restaurantVBox;
    private VBox cafeVBox;
    @FXML
    private VBox parkVBox;

    private List<PlaceSuggestion> restaurants;
    private List<PlaceSuggestion> parks;
    private List<PlaceSuggestion> cafees;
    
    private List<PlaceSuggestion> displayedRests;
    private List<PlaceSuggestion> displayedParks;
    private List<PlaceSuggestion> displayedCafees;
    
    private Address address;
    @FXML
    private TabPane mainTabPane;
    @FXML
    private Slider restaurantRangeSlider;

    private List<String> sortTypes = new ArrayList<>();
    @FXML
    private ComboBox<String> restSortComboBox;
    @FXML
    private TextField restSearchField;
    @FXML
    private CheckBox restOpenCheck;
    @FXML
    private Label cafeRangeLabel;
    @FXML
    private Slider cafeRangeSlider;
    @FXML
    private ComboBox<String> cafeSortComboBox;
    @FXML
    private TextField cafeSearchField;
    @FXML
    private CheckBox cafeeOpenCheck;
    @FXML
    private VBox cafeeVBox;
    @FXML
    private Label parkRangeLabel;
    @FXML
    private Slider parkRangeSlider;
    @FXML
    private ComboBox<String> parkSortComboBox;
    @FXML
    private TextField parkSearchField;
    @FXML
    private CheckBox parkOpenCheck;
    @FXML
    private Label restRangeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sortTypes.addAll(Arrays.asList(
                new String[]{"La plus notée", "La moins notée",
                    "La plus proche", "La moins proches"}
        ));
        
        restSortComboBox.getItems().addAll(sortTypes);
        restSortComboBox.getSelectionModel().select(0);
        restSortComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                applyRestaurantFilter();
            }
        });
        
        cafeSortComboBox.getItems().addAll(sortTypes);
        cafeSortComboBox.getSelectionModel().select(0);
        cafeSortComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                applyCafeeFilter();
            }
        });
        
        parkSortComboBox.getItems().addAll(sortTypes);
        parkSortComboBox.getSelectionModel().select(0);
        parkSortComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                applyParkFilter();
            }
        });
        
        populate();
    }
    
    private double distanceStringToInt(String distance){
        if(distance == null || distance.length() <= 3) return 0;
        return Double.parseDouble(distance.substring(0, distance.length()-3));
    }

    @FXML
    private void updateRestRange(MouseEvent event) {
        restRangeLabel.setText("(" + String.valueOf(restaurantRangeSlider.getValue()) + " Km)");
        populateRestaurants();
    }
    @FXML
    private void updateCafeRange(MouseEvent event) {
        cafeRangeLabel.setText("(" + String.valueOf(cafeRangeSlider.getValue()) + " Km)");
        populateCafees();
    }

    @FXML
    private void updateParkRange(MouseEvent event) {
        parkRangeLabel.setText("(" + String.valueOf(parkRangeSlider.getValue()) + " Km)");
        populateParks();
    }

    private void populate() {
        mainPane.prefHeightProperty().bind(mainTabPane.prefWidthProperty());
        try {
            address = AddressService.getInstance().get(new Address(MySoulMate.MEMBER_ID));
            populateRestaurants();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, RecommandationViewController.class.getName(), null);
        }
    }

    @FXML
    private void setCafeePane(Event event) {
        if (address == null || restaurantVBox == null || mainTabPane == null) {
            return;
        }
        if (cafees == null) {
            populateCafees();
        }else{
            cafeeVBox.setPrefHeight(displayedCafees.size() * 356);
            mainTabPane.setPrefHeight(cafeeVBox.getPrefHeight() + 260);
        }
    }
    
    @FXML
    private void setParkPane(Event event) {
        if (address == null || parkVBox == null || mainTabPane == null) {
            return;
        }
        if (parks == null) {
            populateParks();
        }else{
            parkVBox.setPrefHeight(displayedParks.size() * 356);
            mainTabPane.setPrefHeight(parkVBox.getPrefHeight() + 260);
        }
    }

    @FXML
    private void setRestaurantsPane(Event event) {
        if (address == null || restaurantVBox == null || mainTabPane == null) {
            return;
        }
        if (restaurants == null) {
            populateRestaurants();
        }else{
            restaurantVBox.setPrefHeight(displayedRests.size() * 356);
            mainTabPane.setPrefHeight(restaurantVBox.getPrefHeight() + 260);
        }
    }
    
    private void populateParks(){
        int range = (int) (parkRangeSlider.getValue() * 1000);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                parks = GooglePlacesAPI.getNearByPlaces(address, GooglePlacesAPI.TYPE.PARK, range);
                parks.sort((c1, c2) -> Double.compare(c2.getRating(), c1.getRating()));
                displayedParks = parks;
                return null;
            }
        };
        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    applyParkFilter();
                }
            }
        });
        new Thread(task).start();
    }
    
    private void changeParksView(){
        parkVBox.getChildren().clear();
        try {
            for (PlaceSuggestion suggestion : displayedParks) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceSuggestionView.fxml"));
                AnchorPane pane = loader.load();
                ((PlaceSuggestionViewController) loader.getController()).setSuggestion(suggestion);
                parkVBox.getChildren().add(pane);
            }
            parkVBox.setPrefHeight(displayedParks.size() * 356);
            mainTabPane.setPrefHeight(parkVBox.getPrefHeight() + 260);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, RecommandationViewController.class.getName(), null);
        }
    }

    private void populateCafees() {
        int range = (int) (cafeRangeSlider.getValue() * 1000);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                cafees = GooglePlacesAPI.getNearByPlaces(address, GooglePlacesAPI.TYPE.CAFE, range);
                cafees.sort((c1, c2) -> Double.compare(c2.getRating(), c1.getRating()));
                displayedCafees = cafees;
                return null;
            }
        };
        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    applyCafeeFilter();
                }
            }
        });
        new Thread(task).start();
    }
    
    private void changeCafeesView(){
        cafeeVBox.getChildren().clear();
        try {
            for (PlaceSuggestion suggestion : displayedCafees) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceSuggestionView.fxml"));
                AnchorPane pane = loader.load();
                ((PlaceSuggestionViewController) loader.getController()).setSuggestion(suggestion);
                cafeeVBox.getChildren().add(pane);
            }
            cafeeVBox.setPrefHeight(displayedCafees.size() * 356);
            mainTabPane.setPrefHeight(cafeeVBox.getPrefHeight() + 260);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, RecommandationViewController.class.getName(), null);
        }
    }

    private void populateRestaurants() {
        int range = (int) (restaurantRangeSlider.getValue() * 1000);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                restaurants = GooglePlacesAPI.getNearByPlaces(address, GooglePlacesAPI.TYPE.REST, range);
                restaurants.sort((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
                displayedRests = restaurants;
                return null;
            }
        };
        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    applyRestaurantFilter();
                }
            }
        });
        new Thread(task).start();
    }

    private void changeRestarantsView() {
        restaurantVBox.getChildren().clear();
        try {
            for (PlaceSuggestion suggestion : displayedRests) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceSuggestionView.fxml"));
                AnchorPane pane = loader.load();
                ((PlaceSuggestionViewController) loader.getController()).setSuggestion(suggestion);
                restaurantVBox.getChildren().add(pane);
            }
            restaurantVBox.setPrefHeight(displayedRests.size() * 356);
            mainTabPane.setPrefHeight(restaurantVBox.getPrefHeight() + 260);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, RecommandationViewController.class.getName(), null);
        }
    }

    @FXML
    private void applyResaurantSearch(KeyEvent event) {
        applyRestaurantFilter();
    }

    @FXML
    private void restOpenActivated(ActionEvent event) {
        applyRestaurantFilter();
    }
    
    private void applyRestaurantFilter(){
        if(restSearchField.getText().isEmpty())
            displayedRests = restaurants;
        else{
            displayedRests = restaurants.stream()
                    .filter(r -> r.getName().toLowerCase().startsWith(restSearchField.getText().toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if(restOpenCheck.isSelected()){
            displayedRests = displayedRests.stream().filter(r -> r.isOpen()).collect(Collectors.toList());
        }
        
        switch (restSortComboBox.getValue()){
            case "La plus notée":
                displayedRests.sort((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
                break;
            case "La moins notée":
                displayedRests.sort((r1, r2) -> Double.compare(r1.getRating(), r2.getRating()));
                break;
            case "La plus proche":
                displayedRests.sort((r1, r2) -> Double.compare(distanceStringToInt(r1.getDistance()), distanceStringToInt(r2.getDistance())));
                break;
            case "La moins proches":
                displayedRests.sort((r1, r2) -> Double.compare(distanceStringToInt(r2.getDistance()), distanceStringToInt(r1.getDistance())));
                break;
        }
        
        changeRestarantsView();
    }
    
    private void applyCafeeFilter(){
        if(cafeSearchField.getText().isEmpty())
            displayedCafees = cafees;
        else{
            displayedCafees = cafees.stream()
                    .filter(r -> r.getName().toLowerCase().startsWith(cafeSearchField.getText().toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if(cafeeOpenCheck.isSelected()){
            displayedCafees = displayedCafees.stream().filter(r -> r.isOpen()).collect(Collectors.toList());
        }
        
        switch (cafeSortComboBox.getValue()){
            case "La plus notée":
                displayedCafees.sort((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
                break;
            case "La moins notée":
                displayedCafees.sort((r1, r2) -> Double.compare(r1.getRating(), r2.getRating()));
                break;
            case "La plus proche":
                displayedCafees.sort((r1, r2) -> Double.compare(distanceStringToInt(r1.getDistance()), distanceStringToInt(r2.getDistance())));
                break;
            case "La moins proches":
                displayedCafees.sort((r1, r2) -> Double.compare(distanceStringToInt(r2.getDistance()), distanceStringToInt(r1.getDistance())));
                break;
        }
        changeCafeesView();
    }
    
    private void applyParkFilter(){
        if(parkSearchField.getText().isEmpty())
            displayedParks = parks;
        else{
            displayedParks = parks.stream()
                    .filter(r -> r.getName().toLowerCase().startsWith(parkSearchField.getText().toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if(parkOpenCheck.isSelected()){
            displayedParks = displayedParks.stream().filter(r -> r.isOpen()).collect(Collectors.toList());
        }
        
        switch (parkSortComboBox.getValue()){
            case "La plus notée":
                displayedParks.sort((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
                break;
            case "La moins notée":
                displayedParks.sort((r1, r2) -> Double.compare(r1.getRating(), r2.getRating()));
                break;
            case "La plus proche":
                displayedParks.sort((r1, r2) -> Double.compare(distanceStringToInt(r1.getDistance()), distanceStringToInt(r2.getDistance())));
                break;
            case "La moins proches":
                displayedParks.sort((r1, r2) -> Double.compare(distanceStringToInt(r2.getDistance()), distanceStringToInt(r1.getDistance())));
                break;
        }
        changeParksView();
    }

    @FXML
    private void applyCafeeSearch(KeyEvent event) {
        applyCafeeFilter();
    }

    @FXML
    private void cafeOpenActivated(ActionEvent event) {
        applyCafeeFilter();
    }

    @FXML
    private void parcOpenActivated(ActionEvent event) {
        applyParkFilter();
    }

    @FXML
    private void applyParkSearch(KeyEvent event) {
        applyParkFilter();
    }
}
