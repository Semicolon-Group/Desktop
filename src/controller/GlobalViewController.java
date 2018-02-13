/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class GlobalViewController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private VBox contentVBox;
    @FXML
    private AnchorPane navBar;
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scroll.vvalueProperty().addListener( (observable, oldValue, newValue) -> {
            double yTranslate = (content.getHeight()*newValue.doubleValue())-(scroll.getHeight()*newValue.doubleValue());
            navBar.translateYProperty().setValue(yTranslate);
        });
        // TODO
    }
    
    private void setContent(String path){
        try {
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource(path));
            VBox.setVgrow(scroll, Priority.ALWAYS);
            content.getChildren().add(newLoadedPane);
            newLoadedPane.prefWidthProperty().bind(content.widthProperty());
            newLoadedPane.prefHeightProperty().bind(content.heightProperty());
        } catch (IOException ex) {
            util.Logger.writeLog(ex, GlobalViewController.class.getCanonicalName(), null);
        }
    }

    @FXML
    private void ShowContent(ActionEvent event) {
        setContent("/view/HomeView.fxml");
    }
}