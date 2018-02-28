/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.Choice;
import models.Enumerations.Topic;
import models.Notification;
import models.Question;
import org.controlsfx.control.Notifications;
import services.ChoiceService;
import services.QuestionService;
import util.SendSMS2;


/**
 * FXML Controller class
 *
 * @author vaider
 */
public class AjoutquestionController implements Initializable {
    @FXML
    private TextField txt_question;
    @FXML
    private ComboBox<String> topicsBox;
    @FXML
    private Button bt_ajout;
    ObservableList<String> topicsList = FXCollections.observableArrayList();
    @FXML
    private Button bt_qst;
    private TextField txt_choix1;
    private ObservableList<Pane> choiceBoxs;
    @FXML
    private VBox choicesBox;
    @FXML
    private Button bt_choice;

    /**
     * Initializes the controller class.
     */
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        choiceBoxs=FXCollections.observableArrayList();
        choiceBoxs.addListener((ListChangeListener)(c->onChoiceListChange()));
        choiceBoxs.add(createChoiceBox());
        choiceBoxs.add(createChoiceBox());
         for (Topic i : Topic.values()){
            topicsList.add(i.toString());
        }
        topicsBox.setItems(topicsList);
        
        
    }
    
    private void onChoiceListChange(){
        
        choicesBox.getChildren().add(choiceBoxs.get(choiceBoxs.size()-1));
        
    }

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
       
        Question question=new Question(txt_question.getText(),Topic.valueOf(topicsBox.getValue()));
        QuestionService questionService= QuestionService.getInstance();
        question=questionService.create(question);
        
        
        ChoiceService choiceService=ChoiceService.getInstance();
        
            for(Pane n : choiceBoxs){
                String c=((TextField)((HBox)n.getChildren().get(0)).getChildren().get(1)).getText();
                choiceService.create(new Choice(0, question.getId(), c));
            }
            
             }
    
    private Pane createChoiceBox(){
        Pane p=new Pane();
        HBox hb=new HBox();
        
        
        Label l=new Label("Choix numero "+(choiceBoxs.size()+1));
        TextField field=new TextField();
        hb.getChildren().clear();
        hb.getChildren().add(l);
        hb.getChildren().add(field);
        
        p.getChildren().clear();
        p.getChildren().add(hb);
        
        return p;
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AdminGlobalViewController.getInstance().setMainContent("/view/Question.fxml");
//        bt_qst.getScene().setRoot(FXMLLoader.load(getClass().getResource("/view/Question.fxml")));
    }

    @FXML
    private void ajoutChoix(ActionEvent event) {
        
        Pane p=createChoiceBox();
        choiceBoxs.add(p);
        
    }
    
}
