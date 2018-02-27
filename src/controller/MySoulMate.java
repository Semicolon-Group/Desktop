/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Dimension;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Member;
import services.MemberService;
import util.FileUploader;

/**
 *
 * @author Elyes
 */
public class MySoulMate extends Application {
    
    public static int MEMBER_ID=2;
    public static final String UPLOAD_URL = "http://localhost/mysoulmateuploads/images/";
    public static Stage mainStage;
    private static MySoulMate instance;
    private double loginWidth, loginHeight;
    
    public static MySoulMate getInstance(){
        return instance;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        instance=this;
        mainStage = primaryStage;
        AnchorPane globalPane = FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
        Scene scene = new Scene(globalPane);
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.show();
        loginWidth = globalPane.getWidth();
        loginHeight = globalPane.getHeight();
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
    
    public void ChangeToAdminHomeScene(){
        try {
            Parent globalPane = FXMLLoader.load(getClass().getResource("/view/AdminGlobalView.fxml"));
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
    
    public void logOut(){
        try {
            Member member = MemberService.getInstance().get(new Member(MEMBER_ID));
            member.setLastLogin(new Timestamp(new Date().getTime()));
            MemberService.getInstance().update(member);
            MEMBER_ID = 0;
            AnchorPane globalPane = FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
            Scene scene = new Scene(globalPane);
            
            javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            mainStage.setX(bounds.getMinX() + ((bounds.getWidth()/2) - (loginWidth/2)));
            mainStage.setY(bounds.getMinY() + ((bounds.getHeight()/2) - (loginHeight/2)));
            mainStage.setWidth(loginWidth);
            mainStage.setHeight(loginHeight);
            mainStage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MySoulMate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySoulMate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showAuthenticationView(){
        try {
            AnchorPane globalPane = FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
            Scene scene = new Scene(globalPane);
            
            javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            mainStage.setX(bounds.getMinX() + ((bounds.getWidth()/2) - (loginWidth/2)));
            mainStage.setY(bounds.getMinY() + ((bounds.getHeight()/2) - (loginHeight/2)));
            mainStage.setWidth(loginWidth);
            mainStage.setHeight(loginHeight);
            mainStage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MySoulMate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
