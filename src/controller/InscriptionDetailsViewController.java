/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static controller.InsViewController.m;
import static controller.MainAchref.container3;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.Address;
import models.Enumerations;
import models.Enumerations.BodyType;
import models.Enumerations.Importance;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import models.Enumerations.RelationType;
import models.Enumerations.Religion;
import models.Member;
import services.MemberService;
import util.SendMail;
import util.SendMessage;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriptionDetailsViewController implements Initializable {

    @FXML
    private ComboBox<String> bodyBox;
    ObservableList<String> bodyList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> religionBox;
    ObservableList<String> religionList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> importanceBox;
    ObservableList<String> importanceList = FXCollections.observableArrayList();

//    private ComboBox<String> relationBox;
    ObservableList<String> relationList = FXCollections.observableArrayList();



    @FXML
    private Button confirmer;
    @FXML
    private Button back;
    @FXML
    private RadioButton Smoker;
    @FXML
    private RadioButton Nsmoker;
    @FXML
    private RadioButton Drinker;
    @FXML
    private RadioButton Ndrinker;
    @FXML
    private Label heightLabel;
    @FXML
    private Label bodyLabel;
    @FXML
    private Label religionLabel;
    @FXML
    private Label importanceLabel;
    @FXML
    private Label maritalLabel;
    @FXML
    private Label childrenLabel;
    @FXML
    private Label minLabel;
    @FXML
    private Label maxLabel;
    @FXML
    private Label relationLabel;
    @FXML
    private Label proximityLabel;
    @FXML
    private JFXTextField childrenNum;
    @FXML
    private JFXTextField minAge;
    @FXML
    private JFXTextField maxAge;
    @FXML
    private Label addressLabel;
    @FXML
    private JFXTextField height;

    private Member member;
    @FXML
    private ToggleGroup smoking;
    @FXML
    private ToggleGroup drink;
    @FXML
    private JFXTextArea about;
    @FXML
    private ComboBox<String> statusBox;
    ObservableList<String> statusList = FXCollections.observableArrayList();
    private ToggleGroup maritalGroup;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXTextField country;
    @FXML
    private JFXTextField city;
    @FXML
    private VBox statusVBox;
    
    private List<CheckBox> statusesCheckBoxes = new ArrayList<>();
    private List<CheckBox> relationsCheckBoxes = new ArrayList<>();
    
    @FXML
    private VBox relationsVBox;
    @FXML
    private JFXTextField phoneText;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        height.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        childrenNum.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        minAge.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        maxAge.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        
        for (BodyType b : BodyType.values()) {
            bodyList.add(b.toString());
        }
        bodyBox.setItems(bodyList);

        for (Religion r : Religion.values()) {
            religionList.add(r.toString());
        }
        religionBox.setItems(religionList);

        for (Importance i : Importance.values()) {
            importanceList.add(i.toString());
        }
        importanceBox.setItems(importanceList);

        
         for (MaritalStatus mt : MaritalStatus.values()) {
            statusList.add(mt.toString());
        }
        statusBox.setItems(statusList);
        for(Enumerations.MaritalStatus maritalStatus : Enumerations.MaritalStatus.values()){
            CheckBox cb = new CheckBox(maritalStatus.name());
            cb.setId(maritalStatus.ordinal()+"");
            cb.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    CheckBox b = (CheckBox)event.getTarget();
                    if(b.isSelected())
                        statusesCheckBoxes.add(b);
                    else
                        statusesCheckBoxes.remove(b);
                }
            });
            statusVBox.getChildren().add(cb);
        }
        
        for(RelationType type : RelationType.values()){
            CheckBox cb = new CheckBox(type.name());
            cb.setId(type.ordinal()+"");
            cb.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    CheckBox c = (CheckBox) event.getTarget();
                    if(c.isSelected())
                        relationsCheckBoxes.add(c);
                    else
                        relationsCheckBoxes.remove(c);
                }
            });
            relationsVBox.getChildren().add(cb);
        }
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @FXML
    private void AjouterMembre(ActionEvent event) throws AddressException, MessagingException {
        try {
            boolean valid = true;
            
            
            if (height.getText().equals("")) {
                heightLabel.setText("Field is empty !");
                heightLabel.setVisible(true);
                valid = false;
            } else {
                heightLabel.setText("");
            }
            
            if (childrenNum.getText().equals("")) {
                childrenLabel.setText("Field is empty !");
                childrenLabel.setVisible(true);
                valid = false;
            } else {
                childrenLabel.setText("");
            }
            
            if (minAge.getText().equals("")) {
                minLabel.setText("Field is empty !");
                minLabel.setVisible(true);
                valid = false;
            } else {
                minLabel.setText("");
            }
            
            if (maxAge.getText().equals("")) {
                maxLabel.setText("Field is empty !");
                maxLabel.setVisible(true);
                valid = false;
            } else {
                maxLabel.setText("");
            }
            
            if (bodyBox.getValue() == null) {
                bodyLabel.setText("Field is empty !");
                bodyLabel.setVisible(true);
                valid = false;
            } else {
                bodyLabel.setText("");
            }
            
            if (religionBox.getValue() == null) {
                religionLabel.setText("Field is empty !");
                religionLabel.setVisible(true);
                valid = false;
            } else {
                religionLabel.setText("");
            }
            
            if (importanceBox.getValue() == null) {
                importanceLabel.setText("Field is empty !");
                importanceLabel.setVisible(true);
                valid = false;
            } else {
                importanceLabel.setText("");
            }
            

            if(!valid) return;
            
            m.setAddress(new Address(0, 0, country.getText(), city.getText()));
            m.setChildrenNumber(Integer.parseInt(childrenNum.getText()));
            m.setMinAge(Integer.parseInt(minAge.getText()));
            m.setMaxAge(Integer.parseInt(maxAge.getText()));
            m.setHeight(Float.parseFloat(height.getText()));
            m.setBodyType(BodyType.valueOf(bodyBox.getValue()));
            m.setReligion(Religion.valueOf(religionBox.getValue()));
            m.setReligionImportance(Importance.valueOf(importanceBox.getValue()));
            m.setPhone(Integer.parseInt(phoneText.getText()));
            m.setMaritalStatus(MaritalStatus.valueOf(statusBox.getValue()));
            m.setDrinker(Drinker.isSelected());
            m.setSmoker(Smoker.isSelected());
            m.setAbout(about.getText());
            for(CheckBox cb : statusesCheckBoxes){
                m.getPreferedStatuses().add(MaritalStatus.values()[Integer.parseInt(cb.getId())]);
            }
            for(CheckBox cb : relationsCheckBoxes){
                m.getPreferedRelations().add(RelationType.values()[Integer.parseInt(cb.getId())]);
            }
            
            
            //TODO
            
          MemberService.getInstance().create(m);
          SendMail sm = new SendMail(m.getEmail(), " Confirmation d'inscription ", " Bonjour " + m.getFirstname() + " Vous etes maintenant inscrit à MySoulMate" );
          
//          SendMessage s=new  SendMessage();
//          s.sendSms(" Vous etes Maintenant Inscrit à Mysoulmate ",phoneText.getText());
            
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, InscriptionDetailsViewController.class.getName(), null);
        }
    }

    @FXML
    private void returnIns(ActionEvent event) {

        container3.switchView("InsView");
    }

}
