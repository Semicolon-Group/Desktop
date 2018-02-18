/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.MainAchref.container;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Feedback;
import services.FeedbackService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GetFeedViewController implements Initializable {

    @FXML
    private TableColumn<Feedback,Integer > Sender;
    @FXML
    private TableColumn<Feedback, Timestamp> Date;
    @FXML
    private TableColumn<Feedback, Boolean> State;
    @FXML
    private TableView<Feedback> table;
    private ObservableList<Feedback> data;
    
    public static Feedback f1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(GetFeedViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     private void afficher() throws SQLException {
               
        data= FXCollections.observableArrayList(FeedbackService.getInstance().getFalse(null));
	
        Sender.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        State.setCellValueFactory(new PropertyValueFactory<>("stateName"));      
        table.setItems(null);
        table.setItems(data);
        
    }
     
     
          private void afficherboth() throws SQLException {       
              
        data= FXCollections.observableArrayList(FeedbackService.getInstance().getAll(null));
	
        Sender.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        State.setCellValueFactory(new PropertyValueFactory<>("stateName"));
        
        table.setItems(null);
        table.setItems(data);
        
    }

    @FXML
    private void onMouseClick(MouseEvent event) throws IOException, SQLException {
        
//        GetFeedDetailsViewController.feed = 
//       
            FeedbackService feed = FeedbackService.getInstance();
             f1 = (Feedback) table.getSelectionModel().getSelectedItem();
                
////        
          container.switchView("GetFeedDetailsView");
//    }
    }
}
