/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Answer;
import models.Choice;
import models.Enumerations;
import models.Question;
import services.AnswerService;
import services.ChoiceService;
import services.QuestionService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class AddAnswerViewController implements Initializable {

    @FXML
    private ComboBox<Enumerations.Topic> topicBox;
    @FXML
    private ComboBox<Question> questionBox;
    @FXML
    private ComboBox<Enumerations.Importance> importanceBox;
    @FXML
    private VBox selectedChoicesVBox;
    @FXML
    private VBox acceptedChoicesVBox;
    
    private Answer answer;
    private Stage dialog;
    private SelfProfileViewController selfProfileViewController;
    private List<Answer> answers;
    private List<CheckBox> possibleSelectedChoices;
    private List<CheckBox> possibleAcceptedChoices;
    private List<Choice> choices;
    private List<Question> questions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        possibleSelectedChoices = new ArrayList<>();
        possibleAcceptedChoices = new ArrayList<>();
    }
    
    public void setAnswers(List<Answer> answers){
        this.answers = answers;
        populateFields();
    }
    
    public void setAnswer(Answer answer){
        this.answer = answer;
        populateFields();
        populateFieldsFromAnswer();
    }
    
    public void setDialog(Stage dialog){
        this.dialog = dialog;
    }

    public void setSelfProfileViewController(SelfProfileViewController selfProfileViewController) {
        this.selfProfileViewController = selfProfileViewController;
    }
    
    private void populateFields(){
        topicBox.getItems().setAll(Enumerations.Topic.values());
        topicBox.getSelectionModel().selectedItemProperty().addListener((obv, oldValue, newValue) -> subjectChanged(obv, oldValue, newValue));
        topicBox.getSelectionModel().select(0);
        importanceBox.getItems().setAll(Enumerations.Importance.values());
        importanceBox.getSelectionModel().select(0);
    }
    
    private void populateFieldsFromAnswer(){
        try {
            Question question = QuestionService.getInstance().get(new Question(answer.getQuestionId()));
            topicBox.getItems().clear();
            topicBox.getItems().add(question.getTopic());
            topicBox.getSelectionModel().select(0);
            importanceBox.getSelectionModel().select(answer.getImportance());
            questionBox.getSelectionModel().selectedItemProperty().addListener((obv, oldValue, newValue) -> questionChanged(obv, oldValue, newValue));
            questionBox.getSelectionModel().select(question);
            for(CheckBox checkBox : possibleSelectedChoices){
                int checkBoxId = Integer.parseInt(checkBox.getId());
                checkBox.setSelected(answer.getSelectedChoices().stream().anyMatch(c -> c.getId() == checkBoxId));
            }
            for(CheckBox checkBox : possibleAcceptedChoices){
                int checkBoxId = Integer.parseInt(checkBox.getId());
                checkBox.setSelected(answer.getAcceptedChoices().stream().anyMatch(c -> c.getId() == checkBoxId));
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, AddAnswerViewController.class.getName(), null);
        }
    }

    private void subjectChanged(ObservableValue<? extends Enumerations.Topic> arg0, Enumerations.Topic arg1, Enumerations.Topic arg2) {
        try {
            if(answer == null){
                questions = QuestionService.getInstance().getAll(new Question(null, topicBox.getSelectionModel().getSelectedItem()));
                for(Answer a:answers){
                    questions.remove(questions.stream().filter(q -> q.getId() == a.getQuestionId()).findFirst().orElse(null));
                }
                questionBox.getItems().clear();
                questionBox.getItems().addAll(questions);
                questionBox.getSelectionModel().selectedItemProperty().addListener((obv, oldValue, newValue) -> questionChanged(obv, oldValue, newValue));
                questionBox.getSelectionModel().select(0);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, AddAnswerViewController.class.getName(), null);        
        }
    }

    private void questionChanged(ObservableValue<? extends Question> arg0, Question arg1, Question arg2) {
        try {
            selectedChoicesVBox.getChildren().clear();
            acceptedChoicesVBox.getChildren().clear();
            possibleSelectedChoices.clear();
            possibleAcceptedChoices.clear();
            if(arg2 == null)return;
            choices = ChoiceService.getInstance().getAll(
                    new Choice(0, arg2.getId(), null));
            for(Choice choice: choices){
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_LEFT);
                
                CheckBox selectedCheckBox = new CheckBox(choice.getChoice());
                selectedCheckBox.setId(choice.getId()+"");
                selectedCheckBox.setStyle("-fx-font-size: 13pt;");
                selectedCheckBox.setPadding(new Insets(0, 0, 15, 0));
                
                CheckBox acceptedCheckBox = new CheckBox(choice.getChoice());
                acceptedCheckBox.setId(choice.getId()+"");
                acceptedCheckBox.setStyle("-fx-font-size: 13pt;");
                acceptedCheckBox.setPadding(new Insets(0, 0, 15, 0));
                
                possibleSelectedChoices.add(selectedCheckBox);
                
                possibleAcceptedChoices.add(acceptedCheckBox);
                
                selectedChoicesVBox.getChildren().add(selectedCheckBox);
                acceptedChoicesVBox.getChildren().add(acceptedCheckBox);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, AddAnswerViewController.class.getName(), null);
        }
    }

    @FXML
    private void addAnswer(ActionEvent event) {
        try {
            if(answer != null){
                answer.setImportance(importanceBox.getValue());
                answer.getSelectedChoices().clear();
                for(CheckBox checkBox: possibleSelectedChoices){
                    if(checkBox.isSelected()){
                        Choice ch = choices.stream().filter(c -> c.getId() == Integer.parseInt(checkBox.getId())).findFirst().orElse(null);
                        answer.getSelectedChoices().add(ch);
                    }
                }
                
                answer.getAcceptedChoices().clear();
                for(CheckBox checkBox: possibleAcceptedChoices){
                    if(checkBox.isSelected()){
                        Choice ch = choices.stream().filter(c -> c.getId() == Integer.parseInt(checkBox.getId())).findFirst().orElse(null);
                        answer.getAcceptedChoices().add(ch);
                    }
                }
                
                AnswerService.getInstance().update(answer);
            }else{
                Answer a = new Answer(
                        questionBox.getSelectionModel().getSelectedItem().getId(),
                        null, importanceBox.getSelectionModel().getSelectedItem(), MySoulMate.MEMBER_ID);
                for(CheckBox checkBox: possibleSelectedChoices){
                    if(checkBox.isSelected()){
                        Choice ch = choices.stream().filter(c -> c.getId() == Integer.parseInt(checkBox.getId())).findFirst().orElse(null);
                        a.getSelectedChoices().add(ch);
                    }
                }
                for(CheckBox checkBox: possibleAcceptedChoices){
                    if(checkBox.isSelected()){
                        Choice ch = choices.stream().filter(c -> c.getId() == Integer.parseInt(checkBox.getId())).findFirst().orElse(null);
                        a.getAcceptedChoices().add(ch);
                    }
                }
                AnswerService.getInstance().create(a);
            }
            selfProfileViewController.makeAnswersPane();
            dialog.close();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, AddAnswerViewController.class.getName(), null);        
        }
    }
    
}
