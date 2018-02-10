/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Address;
import models.Enumerations;
import models.Enumerations.BodyType;
import models.Enumerations.Importance;
import models.Enumerations.Proximity;
import models.Enumerations.Religion;
import models.Enumerations.SignalReason;
import models.Feedback;
import models.Member;
import models.Signal;
import services.FeedbackService;
import services.MemberService;
import services.SignalService;
import util.DbConfigParser;

/**
 *
 * @author Elyes
 */
public class MySoulMate extends Application {
    
    @Override
    public void start(Stage primaryStage) {
	Button btn = new Button();
	btn.setText("Say 'Hello World'");
	btn.setOnAction(new EventHandler<ActionEvent>() {
	    
	    @Override
	    public void handle(ActionEvent event) {
		System.out.println("Hello World!");
	    }
	});
	
	StackPane root = new StackPane();
	root.getChildren().add(btn);
	
	Scene scene = new Scene(root, 300, 250);
	
	primaryStage.setTitle("Hello World!");
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
	launch(args);
         
       // FeedbackService f = new FeedbackService();
       // Feedback f1 = new Feedback(1, "bug", true,null);
      //   Feedback f2 = new Feedback(2, "buga", true,null);
      //  f.create(f1);
       // Feedback f1 = new Feedback();
       // f1.setId(1);
       // f1.setState(true);
      //  f.update(f1);
       //  Feedback f2 = new Feedback();
         //f2.setId(1);
        // f.get(f2);
      //  SignalService s = new SignalService();
        //Signal s1 = new Signal( 1,2,SignalReason.RACISME ,  true, null);
        //s.create(s1);
        
      //  Signal s2 = new Signal();
        //s2.setId(1);
       // s2.setState(false);
       // s.update(s2);
      //  s.get(s2);
        
       // MemberService mem = new MemberService();
       // Member m1 = new Member(null, true , 1.8f , BodyType.GROS , 2 , Religion.ISLAM, Importance.IMPORTANT, true, false , 15 , 18, Proximity.DISTANT, null , 1 , "aa" , "achref", "achref7", "bj", "achref@gmail.com", "achref07", "192.168.1.0", 3303);
        
      
         
        
        
        
         
    }
    
    public static void showAlert(Alert.AlertType alertType, String content, ButtonType... buttonType){
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType, content, buttonType);
            alert.show();
        });
    }
    
}
