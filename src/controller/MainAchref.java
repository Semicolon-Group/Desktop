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
import java.sql.SQLException;
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
import models.Member;
import services.MemberService;

/**
 *
 * @author Elyes
 */
public class MainAchref extends Application {
    
    public static FeedbackContainerViewController container;
    public static GetSignalContainerViewController container2;
    public static InscriptionContainerViewController container3;
    public static AjouterSignalContainerViewController container4;
    @Override
    public void start(Stage primaryStage) throws SQLException {
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
//
//    try {
//            FXMLLoader loader = new FXMLLoader();
//            Parent root = loader.
//                    load(getClass().getResource("/view/FeedbackContainerView.fxml").openStream());
//            container = loader.getController();
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//                try {
//            Parent root = FXMLLoader.
//                    load(getClass().getResource("/view/GetSignalView.fxml"));
//            
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    try {
//            FXMLLoader loader = new FXMLLoader();
//            Parent root = loader.
//                    load(getClass().getResource("/view/GetSignalContainerView.fxml").openStream());
//            container2 = loader.getController();
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        System.out.println(MemberService.getInstance().get(new Member(1)));
     try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.
                    load(getClass().getResource("/view/InscriptionContainerView.fxml").openStream());
            container3 = loader.getController();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
////   
//
//     try {
//            FXMLLoader loader = new FXMLLoader();
//            Parent root = loader.
//                    load(getClass().getResource("/view/AjouterSignalContainerView.fxml").openStream());
//            container4 = loader.getController();
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//try {
//            Parent root = FXMLLoader.
//                    load(getClass().getResource("/view/InsView.fxml"));
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