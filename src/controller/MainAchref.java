/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
/**
 *
 * @author asus
 */
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Elyes
 */
public class MainAchref extends Application {
    
    @Override
    public void start(Stage primaryStage) {
//////
//        try {
//            Parent root = FXMLLoader.
//                    load(getClass().getResource("/view/ajoutFeedback.fxml"));
//            
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
////    try {
////            Parent root = FXMLLoader.
////                    load(getClass().getResource("/view/ajoutSignal.fxml"));
////            
////            Scene scene = new Scene(root);
////            primaryStage.setScene(scene);
////            primaryStage.show();
////
////        } catch (IOException ex) {
////            System.out.println(ex.getMessage());
////        }
     try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("/view/FXMLGetFeed.fxml"));
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//   try {
//            Parent root = FXMLLoader.
//                    load(getClass().getResource("/view/FXMLInscrit.fxml"));
//            
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//   try {
//            Parent root = FXMLLoader.
//                    load(getClass().getResource("/view/FXMLInscrit.fxml"));
//            
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
     
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}