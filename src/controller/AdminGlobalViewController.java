/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
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
public class AdminGlobalViewController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox contentVBox;
    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane navBar;
    private VBox homeBox;
    private VBox matchBox;
    private VBox recommandationBox;
    @FXML
    private Button membersButton;
    @FXML
    private Button questionsButton;
    @FXML
    private Button reportsButton;
    @FXML
    private Button feddbacksButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private VBox questionsBox;
    @FXML
    private VBox reportsBox;
    @FXML
    private VBox feedbacksBox;
    @FXML
    private VBox statisticsBox;
    @FXML
    private VBox membersBox;
    @FXML
    private VBox statisticsBox1;
    @FXML
    private Button logoutButton;
    
    private static AdminGlobalViewController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        scroll.vvalueProperty().addListener( (observable, oldValue, newValue) -> {
            double yTranslate = (content.getHeight()*newValue.doubleValue())-(scroll.getHeight()*newValue.doubleValue());
            navBar.translateYProperty().setValue(yTranslate);
        });
        showMembersContent(null);
    }
    
    public static AdminGlobalViewController getInstance(){
        return controller;
    }
    
    public FXMLLoader setMainContent(String path){
        return setContent(path, content);
    }
    
    public void clearMainContent(){
        clearContent(content);
    }
    
    private FXMLLoader setContent(String path, Pane container){
        try {
            scroll.setVvalue(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane newLoadedPane =  loader.load();
            VBox.setVgrow(scroll, Priority.ALWAYS);
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            newLoadedPane.prefWidthProperty().bind(container.widthProperty());
            newLoadedPane.prefHeightProperty().bind(container.heightProperty());
            return loader;
        } catch (IOException ex) {
            util.Logger.writeLog(ex, GlobalViewController.class.getCanonicalName(), null);
        }
        return null;
    }
    
    private void clearContent(Pane container){
        container.getChildren().clear();
    }

    @FXML
    private void showMembersContent(ActionEvent event) {
        setMainContent("/view/Membre.fxml");
        membersBox.setId("selected");
        questionsBox.setId("");
        feedbacksBox.setId("");
        reportsBox.setId("");
        statisticsBox.setId("");
    }

    @FXML
    private void showQuestionsContent(ActionEvent event) {
        setMainContent("/view/Question.fxml");
        membersBox.setId("");
        questionsBox.setId("selected");
        feedbacksBox.setId("");
        reportsBox.setId("");
        statisticsBox.setId("");
    }

    @FXML
    private void showReportsContent(ActionEvent event) {
        membersBox.setId("");
        questionsBox.setId("");
        feedbacksBox.setId("");
        reportsBox.setId("selected");
        statisticsBox.setId("");
    }

    @FXML
    private void showFeedbacksContent(ActionEvent event) {
        membersBox.setId("");
        questionsBox.setId("");
        feedbacksBox.setId("selected");
        reportsBox.setId("");
        statisticsBox.setId("");
    }

    @FXML
    private void showStatisticsContent(ActionEvent event) {
        setMainContent("/view/StatistiquesView.fxml");
        membersBox.setId("");
        questionsBox.setId("");
        feedbacksBox.setId("");
        reportsBox.setId("");
        statisticsBox.setId("selected");
    }

    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit My Soulmate?", ButtonType.YES, ButtonType.CANCEL);
        Optional<ButtonType> rs = alert.showAndWait();
        if(rs.get() == ButtonType.YES)
            Platform.exit();
    }
}
