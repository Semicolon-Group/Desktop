/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Elyes
 */
public class MySoulMate extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
//	Button btn = new Button();
//	btn.setText("Say 'Hello World'");
//	btn.setOnAction(new EventHandler<ActionEvent>() {
//	    
//	    @Override
//	    public void handle(ActionEvent event) {
//		System.out.println("Hello World!");
//	    }
//	});
//	
//	StackPane root = new StackPane();
//	root.getChildren().add(btn);
//	
//	Scene scene = new Scene(root, 300, 250);
//	
//	primaryStage.setTitle("Hello World!");
//	primaryStage.setScene(scene);
//	primaryStage.show();

        Parent globalPane = FXMLLoader.load(getClass().getResource("/view/GlobalView.fxml"));
        Scene scene = new Scene(globalPane);
        primaryStage.setScene(scene);
        
        javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        primaryStage.show();
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
