/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controller.MainAchref.container3;
import java.net.URL;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.Member;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class InsViewController implements Initializable {

    @FXML
    private JFXTextField Firstname;
    @FXML
    private JFXTextField Last_name;
    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private JFXPasswordField Rpassword;
    @FXML
    private DatePicker birth_date;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Label labelfirst;
    @FXML
    private Label labelname;
    @FXML
    private Label labelps;
    @FXML
    private Label labelemail;
    @FXML
    private Label labelpass;
    @FXML
    private Label labelrepass;
    @FXML
    private Label labeldate;
    @FXML
    private Button btnR;

    public static Member m;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RotateTransition rt = new RotateTransition(Duration.seconds(35),image);
        rt.setByAngle(9*360);
        rt.play();

    }

    @FXML
    public void Continuer(ActionEvent event) {
        boolean valid = true;
        if (Firstname.getText().equals("")) {
            labelfirst.setText("Field is empty !");
            labelfirst.setVisible(true);
            valid = false;
        } else {
            labelfirst.setText("");
        }

        if (Last_name.getText().equals("")) {
            labelname.setText("Field is empty !");
            labelname.setVisible(true);
            valid = false;
        } else {
            labelname.setText("");
        }

        if (pseudo.getText().equals("")) {
            labelps.setText("Field is empty !");
            labelps.setVisible(true);
            valid = false;
        } else {
            labelps.setText("");
        }
        if (email.getText().equals("")) {
            labelemail.setText("Field is empty !");
            labelemail.setVisible(true);
            valid = false;
        }

        if (!email.getText().contains("@") && !email.getText().contains(".")) {
            labelemail.setText("E-mail is not valid !");
            labelemail.setVisible(true);
            valid = false;
        } else {
            labelemail.setText("");
        }

        if (Password.getText().equals("")) {
            labelpass.setText("Field is empty !");
            labelpass.setVisible(true);
            valid = false;
        } else {
            labelpass.setText("");
        }

        if (Rpassword.getText().equals("")) {
            labelrepass.setText("Field is empty !");
            labelrepass.setVisible(true);
            valid = false;
        }

        if (!Password.getText().equals(Rpassword.getText())) {
            labelrepass.setText("Password doesn't match !");
            labelrepass.setVisible(true);
            valid = false;
        } else {
            labelrepass.setText("Password match !");
            labelrepass.setTextFill(Color.web("GREEN"));;
            labelrepass.setVisible(true);
        }

        if (birth_date.getValue() == null) {
            labeldate.setText("Field is empty !");
            labeldate.setVisible(true);
            valid = false;
        }
        if(!valid) return;
        
        m = new Member();
        m.setFirstname(Firstname.getText());
        m.setLastname(Last_name.getText());
        m.setPseudo(pseudo.getText());
        m.setEmail(email.getText());
        m.setPassword(Password.getText());
        m.setGender(male.isSelected());
        m.setBirthDate(java.sql.Date.valueOf(birth_date.getValue()));

        FXMLLoader loader = container3.switchView("InscriptionDetailsView");
        ((InscriptionDetailsViewController)loader.getController()).setMember(m);
         
            
    }

    @FXML
    private void Reset(ActionEvent event) {

        Firstname.clear();
        Last_name.clear();
        pseudo.clear();
        email.clear();
        Password.clear();
        Rpassword.clear();
        

    }

}
