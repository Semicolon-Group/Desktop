/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

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
    
    private EventHandler notificationPaneHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            String id = ((Node)event.getTarget()).getId();
            if(id != null && (id.equals("notification_icon") || id.equals("notification_icon_box"))){
                notificationPane.setVisible(!notificationPane.isVisible());
                notificationPane.setPrefHeight(600);
                conversationPane.setVisible(false);
                conversationPane.setPrefHeight(0);
                accountPane.setVisible(false);
                accountPane.setPrefHeight(0);
            }else if(id != null && (id.equals("message_icon") || id.equals("message_icon_box"))){
                conversationPane.setVisible(!conversationPane.isVisible());
                conversationPane.setPrefHeight(600);
                notificationPane.setVisible(false);
                notificationPane.setPrefHeight(0);
                accountPane.setVisible(false);
                accountPane.setPrefHeight(0);
            }else if(id != null && (id.equals("account_icon") || id.equals("account_icon_box"))){
                accountPane.setVisible(!accountPane.isVisible());
                accountPane.setPrefHeight(600);
                conversationPane.setVisible(false);
                conversationPane.setPrefHeight(0);
                notificationPane.setVisible(false);
                notificationPane.setPrefHeight(0);
            }else{
                if(!((Node)event.getTarget()).getStyleClass().contains(new String("notificationClickable"))){
                notificationPane.setVisible(false);
                notificationPane.setPrefHeight(0);
                }
                if(!((Node)event.getTarget()).getStyleClass().contains(new String("conversationClickable"))){
                    conversationPane.setVisible(false);
                    conversationPane.setPrefHeight(0);
                }
                if(!((Node)event.getTarget()).getStyleClass().contains(new String("accountClickable"))){
                    accountPane.setVisible(false);
                    accountPane.setPrefHeight(0);
                }
            }
        }
    };
    @FXML
    private ImageView accountIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainAnchor.addEventFilter(MouseEvent.MOUSE_CLICKED, notificationPaneHandler);
        scroll.vvalueProperty().addListener( (observable, oldValue, newValue) -> {
            double yTranslate = (content.getHeight()*newValue.doubleValue())-(scroll.getHeight()*newValue.doubleValue());
            navBar.translateYProperty().setValue(yTranslate);
        });
        notificationPane.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                notificationIcon.getStyleClass().remove(notificationIcon.getStyleClass().size()-1);
                activeIcon(notificationIcon, "notification");
                return;
            }
            notificationIcon.getStyleClass().add("hoverable");
            releaseIcon(notificationIcon, "notification");
        });
        conversationPane.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                messageIcon.getStyleClass().remove(messageIcon.getStyleClass().size()-1);
                activeIcon(messageIcon, "message");
                return;
            }
            messageIcon.getStyleClass().add("hoverable");
            releaseIcon(messageIcon, "message");
        });
        accountPane.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                accountIcon.getStyleClass().remove(accountIcon.getStyleClass().size()-1);
                activeIcon(accountIcon, "account");
                return;
            }
            accountIcon.getStyleClass().add("hoverable");
            releaseIcon(accountIcon, "account");
        });
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
        setContent("InstantMessagingView.fxml");
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

    @FXML
    private void hover(MouseEvent event) {
        Pane target = ((Pane)event.getTarget());
        ImageView icon = ((ImageView)target.getChildren().get(0));
        if(!icon.getStyleClass().contains(new String("hoverable"))) return;
        String id = target.getId();
        activeIcon(icon, id.substring(0, id.indexOf("_")));
    }

    @FXML
    private void exit(MouseEvent event) {
        Pane target = ((Pane)event.getTarget());
        ImageView icon = ((ImageView)target.getChildren().get(0));
        if(!icon.getStyleClass().contains(new String("hoverable"))) return;
        String id = target.getId();
        releaseIcon(icon, id.substring(0, id.indexOf("_")));
    }
    
    private void activeIcon(ImageView imageView, String imageName){
        String url = getClass().getResource("/view/assets/icons/hovered/"+imageName+".png").toExternalForm();
        imageView.setImage(new Image(url));
    }
    
    private void releaseIcon(ImageView imageView, String imageName){
        String url = getClass().getResource("/view/assets/icons/natural/"+imageName+".png").toExternalForm();
        imageView.setImage(new Image(url));
    }
}