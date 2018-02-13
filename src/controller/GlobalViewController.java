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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private AnchorPane notificationPane;
    @FXML
    private AnchorPane conversationPane;
    @FXML
    private AnchorPane conversationContent;
    @FXML
    private AnchorPane notificationContent;
    @FXML
    private ImageView notificationIcon;
    @FXML
    private ImageView messageIcon;
    @FXML
    private AnchorPane accountPane;
    @FXML
    private AnchorPane accountContent;
    @FXML
    private Button homeButton;
    @FXML
    private Button quickSearchButton;
    @FXML
    private Button blindDateButton;
    @FXML
    private Button recommandationButton;
    @FXML
    private Button matchButton;
    @FXML
    private VBox homeBox;
    @FXML
    private VBox matchBox;
    @FXML
    private VBox quickSearchBox;
    @FXML
    private VBox recommandationBox;
    @FXML
    private VBox blindDateBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainAnchor.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String id = ((Node)event.getTarget()).getId();
                if(id==null) return;
                if(id.equals("notification_icon") || id.equals("notification_icon_box")){
                    notificationPane.setVisible(true);
                    conversationPane.setVisible(false);
                    accountPane.setVisible(false);
                }else if(id.equals("message_icon") || id.equals("message_icon_box")){
                    conversationPane.setVisible(true);
                    notificationPane.setVisible(false);
                    accountPane.setVisible(false);
                }else if(id.equals("account_icon") || id.equals("account_icon_box")){
                    accountPane.setVisible(true);
                    conversationPane.setVisible(false);
                    notificationPane.setVisible(false);
                }else{
                    notificationPane.setVisible(false);
                    conversationPane.setVisible(false);
                    accountPane.setVisible(false);
                }
            }
        });
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
    private void showHomeContent(ActionEvent event) {
        setContent("/view/HomeView.fxml");
        homeBox.setId("selected");
        matchBox.setId("");
        quickSearchBox.setId("");
        blindDateBox.setId("");
        recommandationBox.setId("");
        
    }

    @FXML
    private void showMatchContent(ActionEvent event) {
        homeBox.setId("");
        matchBox.setId("selected");
        quickSearchBox.setId("");
        blindDateBox.setId("");
        recommandationBox.setId("");
    }

    @FXML
    private void showQuickSearchContent(ActionEvent event) {
        homeBox.setId("");
        matchBox.setId("");
        quickSearchBox.setId("selected");
        blindDateBox.setId("");
        recommandationBox.setId("");
    }

    @FXML
    private void showBlindDateContent(ActionEvent event) {
        homeBox.setId("");
        matchBox.setId("");
        quickSearchBox.setId("");
        blindDateBox.setId("selected");
        recommandationBox.setId("");
    }

    @FXML
    private void showRecommandationContent(ActionEvent event) {
        homeBox.setId("");
        matchBox.setId("");
        quickSearchBox.setId("");
        blindDateBox.setId("");
        recommandationBox.setId("selected");
    }
}