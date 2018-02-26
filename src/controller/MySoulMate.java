/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
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
        Parent globalPane = FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
        Scene scene = new Scene(globalPane);
            mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.show();
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
            
        } catch (IOException ex) {
            util.Logger.writeLog(ex, GlobalViewController.class.getCanonicalName(), null);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);

 
 
    }
    
    public static void showAlert(Alert.AlertType alertType, String content, ButtonType... buttonType){
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType, content, buttonType);
            alert.show();
        });
    }
    
}
