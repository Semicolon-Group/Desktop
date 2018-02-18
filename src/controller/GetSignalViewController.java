/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.MainAchref.container;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Enumerations.SignalReason;
import models.Signal;
import services.SignalService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GetSignalViewController implements Initializable {

    @FXML
    private TableColumn<Signal, Integer> sender;
    @FXML
    private TableColumn<Signal, Integer> receiver;
    @FXML
    private TableColumn<Signal, SignalReason > reason;
    @FXML
    private TableColumn<Signal, Boolean> state;
    @FXML
    private TableColumn<Signal, Timestamp> date;
    @FXML
    private TableView<Signal> table;
    private ObservableList<Signal> data;
    public static Signal s1;

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
    
    public void afficher() throws SQLException{
        data= FXCollections.observableArrayList(SignalService.getInstance().getAll(null));
	
        sender.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        receiver.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        state.setCellValueFactory(new PropertyValueFactory<>("stateName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        table.setItems(null);
        table.setItems(data);
    
    
    }
    private void onMouseClick(MouseEvent event) throws IOException, SQLException {
        
          SignalService signal = SignalService.getInstance();
          s1 = (Signal) table.getSelectionModel().getSelectedItem();
           container.switchView("GetSignalDetailsView");
}
}