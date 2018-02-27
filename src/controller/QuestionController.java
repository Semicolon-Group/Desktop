/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.QuestionService;
import util.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Choice;
import models.Enumerations;
import models.Question;
import org.controlsfx.control.textfield.TextFields;
import services.ChoiceService;
import services.Service;

/**
 * FXML Controller class
 *
 * @author vaider
 */
public class QuestionController extends Service implements Initializable {
   
    @FXML
    private TableView<Question> table;

     @FXML
    private TableColumn<Question, String> c_question;
    @FXML
    private TableColumn<Question, String> c_topic;
    private ObservableList<Question> data;
    @FXML
    private Button bt_ajout;
    @FXML
    private TableColumn<Choice,String> c_choices;
    @FXML
    private Button bt_supp;
    @FXML
    private TextField txt_id;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        txt_id.setVisible(false);
        data= FXCollections.observableArrayList();
	List<Question> questions = null;
        try {
            questions = QuestionService.getInstance().getAll(new Question());
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
                for(Question i : questions){
                    
                    data.add(i);
                

        c_question.setCellValueFactory(new PropertyValueFactory<>("Question"));
        
        c_topic.setCellValueFactory(new PropertyValueFactory<>("Topic"));
        c_choices.setCellValueFactory(new PropertyValueFactory<>("choices"));
        
        
        }
        
        
        table.setItems(null);
        table.setItems(data);
        

         
    
    
                }
    @FXML
    private void movetoajoutquestion(ActionEvent event) throws IOException {
        AdminGlobalViewController.getInstance().setMainContent("/view/AjoutQuestion.fxml");
    }

    @FXML
    private void SupprimerAction(ActionEvent event) throws SQLException {
        QuestionService qs=QuestionService.getInstance();
        qs.deleteq(Integer.parseInt(txt_id.getText()));
        AdminGlobalViewController.getInstance().setMainContent("/view/Question.fxml");
    }

    @FXML
    private void Supp_click(MouseEvent event) {
        QuestionService qs=QuestionService.getInstance();
        Question q=(Question) table.getSelectionModel().getSelectedItem();
        
        txt_id.setText(String.valueOf(q.getId()));
    }


}    