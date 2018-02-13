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
import javafx.scene.layout.AnchorPane;
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
    private ScrollPane content;
    @FXML
    private AnchorPane navBar;
    @FXML
    private AnchorPane mainAnchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(getClass().getResourceAsStream("/assets/fonts/fontawesome-webfont3e6e.ttf"), 14);
        // TODO
    }
    
    private void setContent(String path){
        try {
            VBox newLoadedPane =  FXMLLoader.load(getClass().getResource(path));
            content.setContent(newLoadedPane);
//            content.getChildren().clear();
//            content.getChildren().add(newLoadedPane);
            
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