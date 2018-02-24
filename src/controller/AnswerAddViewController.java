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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class AnswerAddViewController implements Initializable {

    @FXML
    private Label topicLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private HBox answerChoicesBox;
    @FXML
    private HBox acceptedChoiceBox;

    private int userId;
    @FXML
    private HBox answerChoicesBox1;
    @FXML
    private ComboBox<Enumerations.Importance> importanceCombo;

    private List<CheckBox> selectedBoxs = new ArrayList<>();
    private Question question;
    private ToggleGroup answerGroup;
    private SelfProfileViewController controller;
    private Stage dialog;
    private int i = -1;

    @FXML
    private VBox noMore;
    @FXML
    private Button finishBt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        question = null;
    }

    public void setParams(int userId, SelfProfileViewController controller, Stage dialog) {
        this.userId = userId;
        this.controller = controller;
        this.dialog = dialog;
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getScene().setFill(Color.TRANSPARENT);
        populate();
    }

    public void setParams(int userId, Question question, Stage dialog) {
        this.userId = userId;
        this.question = question;
        this.dialog = dialog;
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getScene().setFill(Color.TRANSPARENT);
        populate();
    }

    public void setParams(int userId, Stage dialog) {
        this.userId = userId;
        this.dialog = dialog;
        finishBt.setVisible(true);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getScene().setFill(Color.TRANSPARENT);
        i=0;
        populateObligatory();
    }

    private void populateObligatory() {
        if (i == 14) {
            finishBt.setVisible(true);
            populate();
            return;
        }
        try {
            question = getRandomObligatoryQuestion();
            if (question == null) {
                finishBt.setVisible(true);
                populate();
                return;
            }
            importanceCombo.getItems().clear();
            importanceCombo.getItems().addAll(Enumerations.Importance.values());
            importanceCombo.getSelectionModel().select(0);
            topicLabel.setText(question.getTopic().name());
            questionLabel.setText(question.getQuestion());
            List<Choice> choices = ChoiceService.getInstance().getAll(new Choice(0, question.getId(), null));
            answerGroup = new ToggleGroup();
            answerChoicesBox.getChildren().clear();
            acceptedChoiceBox.getChildren().clear();
            for (Choice choice : choices) {
                RadioButton radio = new RadioButton(choice.getChoice());
                radio.setId(choice.getId() + "");
                radio.setToggleGroup(answerGroup);
                answerChoicesBox.getChildren().add(radio);

                CheckBox box = new CheckBox(choice.getChoice());
                box.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        CheckBox cb = (CheckBox) event.getSource();
                        if (cb.isSelected()) {
                            selectedBoxs.add(cb);
                        } else {
                            selectedBoxs.remove(cb);
                        }
                    }
                });
                box.setId(choice.getId() + "");
                acceptedChoiceBox.getChildren().add(box);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerAddViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Question getRandomObligatoryQuestion() {
        try {
            List<Answer> answers = AnswerService.getInstance().getAll(new Answer(0, null, null, userId));
            List<Question> questions = QuestionService.getInstance().getAll(new Question(null, Enumerations.Topic.Mandatory));
            List<Question> answeredQuestions = new ArrayList<>();
            for (Answer answer : answers) {
                answeredQuestions.add(questions.stream().filter(q -> q.getId() == answer.getQuestionId()).findFirst().orElse(null));
            }
            questions.removeAll(answeredQuestions);
            if (questions.size() == 0) {
                return null;
            }
            int index = (new Random()).nextInt(questions.size());
            return questions.get(index);
        } catch (SQLException ex) {
            Logger.getLogger(AnswerAddViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void populate() {
        try {
            if (question == null) {
                question = getRandomQuestion();
                if (question == null) {
                    noMore.setVisible(true);
                    return;
                }
            }
            importanceCombo.getItems().clear();
            importanceCombo.getItems().addAll(Enumerations.Importance.values());
            importanceCombo.getSelectionModel().select(0);
            topicLabel.setText(question.getTopic().name());
            questionLabel.setText(question.getQuestion());
            List<Choice> choices = ChoiceService.getInstance().getAll(new Choice(0, question.getId(), null));
            answerGroup = new ToggleGroup();
            answerChoicesBox.getChildren().clear();
            acceptedChoiceBox.getChildren().clear();
            for (Choice choice : choices) {
                RadioButton radio = new RadioButton(choice.getChoice());
                radio.setId(choice.getId() + "");
                radio.setToggleGroup(answerGroup);
                answerChoicesBox.getChildren().add(radio);

                CheckBox box = new CheckBox(choice.getChoice());
                box.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        CheckBox cb = (CheckBox) event.getSource();
                        if (cb.isSelected()) {
                            selectedBoxs.add(cb);
                        } else {
                            selectedBoxs.remove(cb);
                        }
                    }
                });
                box.setId(choice.getId() + "");
                acceptedChoiceBox.getChildren().add(box);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerAddViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Question getRandomQuestion() {
        try {
            List<Answer> answers = AnswerService.getInstance().getAll(new Answer(0, null, null, userId));
            List<Question> questions = QuestionService.getInstance().getAll(new Question());
            List<Question> answeredQuestions = new ArrayList<>();
            for (Answer answer : answers) {
                answeredQuestions.add(questions.stream().filter(q -> q.getId() == answer.getQuestionId()).findFirst().orElse(null));
            }
            questions.removeAll(answeredQuestions);
            if (questions.size() == 0) {
                return null;
            }
            int index = (new Random()).nextInt(questions.size());
            return questions.get(index);
        } catch (SQLException ex) {
            Logger.getLogger(AnswerAddViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void addAnswer(ActionEvent event) {
        try {
            RadioButton radio = (RadioButton) answerGroup.getSelectedToggle();
            if (radio == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select an answer!", ButtonType.OK);
                alert.show();
                return;
            } else if (selectedBoxs.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You have to selected one or more accepted answers!", ButtonType.OK);
                alert.show();
                return;
            }
            Answer answer = new Answer(question.getId(), null, importanceCombo.getValue(), userId);
            answer.getSelectedChoices().add(ChoiceService.getInstance().get(new Choice(Integer.parseInt(radio.getId()))));
            for (CheckBox cb : selectedBoxs) {
                answer.getAcceptedChoices().add(ChoiceService.getInstance().get(new Choice(Integer.parseInt(cb.getId()))));
            }
            AnswerService.getInstance().create(answer);
            if (controller != null) {
                controller.makeAnswersPane();
            }
            question = null;
            if(i==-1){
                populate();
            }else{
                i++;
                populateObligatory();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerAddViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dismiss(MouseEvent event) {
        dialog.close();
    }

    @FXML
    private void close(ActionEvent event) {
        dialog.close();
    }

}
