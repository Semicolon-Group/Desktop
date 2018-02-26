/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.Address;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class MapsViewController implements Initializable {

    @FXML
    private WebView mapsView;
    private AnchorPane parent;
    @FXML
    private ProgressIndicator loadIndicator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setParams(double originLat, double originLng, double destLat, double destLng, AnchorPane parent){
        this.parent = parent;
        WebEngine engine = mapsView.getEngine();
        engine.load(getClass().getResource("/view/assets/maps_view/MapsView.html").toString());
        
        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    engine.executeScript("initialize("+originLat+", "+originLng+", "+destLat+", "+destLng+")");
                    loadIndicator.getParent().setVisible(false);
                    GlobalViewController.getInstance().lockScrollToTop();
                }
            }
        });
    }

    @FXML
    private void dismiss(MouseEvent event) {
        if(event.getTarget() instanceof AnchorPane){
            parent.getChildren().remove(parent.getChildren().size()-1);
            GlobalViewController.getInstance().releaseScroll();
        }
    }
    
}
