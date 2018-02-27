/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Answer;
import models.Choice;
import models.Question;
import services.AnswerService;
import services.QuestionService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class AnswerViewController implements Initializable {

    @FXML
    private Label questionLabel;
    @FXML
    private Label topicLabel;
    @FXML
    private Label answerLabel;
    private Answer answer;
    @FXML
    private Label acceptedChoicesLabel;
    @FXML
    private Label importanceLabel;
    @FXML
    private AnchorPane mainAnchorPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setAnswer(Answer answer){
        this.answer = answer;
        poulateFields();
    }
    
    private void poulateFields(){
        try {
            Question question = QuestionService.getInstance().get(new Question(answer.getQuestionId()));
            questionLabel.setText(question.getQuestion());
            String selectedChoices ="";
            for(Choice choice : answer.getSelectedChoices()){
                selectedChoices+=choice.getChoice()+", ";
            }
            answerLabel.setText(selectedChoices.length()!=0?selectedChoices.substring(0, selectedChoices.length()-2):"");
            String acceptedChoices="";
            for(Choice choice:answer.getAcceptedChoices()){
                acceptedChoices+=choice.getChoice()+", ";
            }
            acceptedChoicesLabel.setText(acceptedChoices.length()!=0?acceptedChoices.substring(0, acceptedChoices.length()-2):"");
            topicLabel.setText(question.getTopic().name());
            importanceLabel.setText(answer.getImportance().name());
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, AnswerViewController.class.getName(), null);
        }
    }
}
