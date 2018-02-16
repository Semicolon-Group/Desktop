/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Feedback;
import services.FeedbackService;
import services.Service;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLGetFeedController extends Service implements Initializable {

    @FXML
    private TableView<Feedback> table;
    @FXML
    private TableColumn<FeedbackService, String> content;
      private ObservableList<Feedback> data;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficher(ActionEvent event) throws SQLException {
        
             String query = "select * from Feedback";
        PreparedStatement pst=CONNECTION.prepareStatement(query);
               
	ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        
            data= FXCollections.observableArrayList();
	
	while (rs.next()) {
            
	    Feedback f = new Feedback(rs.getString("content"));
                data.add(f);  
            } 
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        table.setItems(null);
        table.setItems(data);
        
    }
    
}
