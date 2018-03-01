/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Answer;
import models.Question;
import services.AnswerService;
import services.QuestionService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class OthersAnswerViewController implements Initializable {

    @FXML
    private Label topicLabel;
    @FXML
    private Label questionLabel;
    
    private Answer answer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setAnswer(Answer answer){
        this.answer = answer;
        populate();
    }
    
    private void populate(){
        try {
            Question question = QuestionService.getInstance().get(new Question(answer.getQuestionId()));
            topicLabel.setText(question.getTopic().name());
            questionLabel.setText(question.getQuestion());
        } catch (SQLException ex) {
            Logger.getLogger(OthersAnswerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addAnswer(MouseEvent event) {
        try {
            List<Answer> answers = AnswerService.getInstance().getAll(new Answer(0, null, null, MySoulMate.MEMBER_ID));
            if(answers.stream().anyMatch(a -> a.getQuestionId() == answer.getQuestionId())){
                Alert alert = new Alert(Alert.AlertType.ERROR, "You have already answered this question!", ButtonType.OK);
                alert.show();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to answer this question?", ButtonType.YES, ButtonType.CLOSE);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.YES){
                showAddDialog();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OthersAnswerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showAddDialog(){
        try {
            final Stage dialog = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AnswerAddView.fxml"));
            AnchorPane pane = loader.load();
            Question q = QuestionService.getInstance().get(new Question(answer.getQuestionId()));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(MySoulMate.mainStage);
            Scene dialogScene = new Scene(pane, 1066, 465);
            dialog.setScene(dialogScene);
            ((AnswerAddViewController) loader.getController()).setParams(
                    MySoulMate.MEMBER_ID,
                    q,
                    dialog
                );
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(OthersAnswerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OthersAnswerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
