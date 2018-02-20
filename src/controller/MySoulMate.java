/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Address;
import models.Enumerations;
import models.Member;
import models.PlaceSuggestion;
import services.MemberService;
import util.GooglePlacesAPI;

/**
 *
 * @author Elyes
 */
public class MySoulMate extends Application {
    
    public static int MEMBER_ID=2;
    public static final String UPLOAD_URL = "http://localhost/mysoulmateuploads/";
    public static Stage mainStage;
    private static MySoulMate instance;
    
    public static MySoulMate getInstance(){
        return instance;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        instance=this;
        mainStage = primaryStage;
//        Parent globalPane = FXMLLoader.load(getClass().getResource("/view/GlobalView.fxml"));
//        Scene scene = new Scene(globalPane);
//        mainStage.setScene(scene);
       mainStage.initStyle(StageStyle.UNDECORATED);
//        mainStage.show();
        ChangeToHomeScene();
    }
    
    public void ChangeToHomeScene(){
        try {
            Parent globalPane = FXMLLoader.load(getClass().getResource("/view/GlobalView.fxml"));
            Scene scene = new Scene(globalPane);
            mainStage.setScene(scene);

            javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            mainStage.setX(primaryScreenBounds.getMinX());
            mainStage.setY(primaryScreenBounds.getMinY());
            mainStage.setWidth(primaryScreenBounds.getWidth());
            mainStage.setHeight(primaryScreenBounds.getHeight());

            mainStage.setResizable(false);

            mainStage.show();
        } catch (IOException ex) {
            util.Logger.writeLog(ex, GlobalViewController.class.getCanonicalName(), null);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
//        List<PlaceSuggestion> suggestions = GooglePlacesAPI.getNearByPlaces(new Address(10.310501, 36.8964803, null, null),
//                GooglePlacesAPI.TYPE.REST, 1000);
//        suggestions.forEach(System.out::println);
        launch(args);
    }
    
    public static void showAlert(Alert.AlertType alertType, String content, ButtonType... buttonType){
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType, content, buttonType);
            alert.show();
        });
    }
    
}
