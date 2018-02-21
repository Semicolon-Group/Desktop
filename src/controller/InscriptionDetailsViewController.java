/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import static controller.InsViewController.m;
import static controller.MainAchref.container3;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.Address;
import models.Enumerations.BodyType;
import models.Enumerations.Importance;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import models.Enumerations.RelationType;
import models.Enumerations.Religion;
import models.Member;
import services.MemberService;


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

    @FXML
    private ComboBox<String> maritalBox;
    ObservableList<String> maritalList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> relationBox;
    ObservableList<String> relationList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> proximityBox;
    ObservableList<String> proximityList = FXCollections.observableArrayList();

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
    private JFXTextField Adress;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

        for (MaritalStatus m : MaritalStatus.values()) {
            maritalList.add(m.toString());
        }
        maritalBox.setItems(maritalList);

        for (RelationType t : RelationType.values()) {
            relationList.add(t.toString());
        }
        relationBox.setItems(relationList);

        for (Proximity p : Proximity.values()) {
            proximityList.add(p.toString());
        }
        proximityBox.setItems(proximityList);

//        final ToggleGroup group = new ToggleGroup();
//        Nsmoker.setToggleGroup(group);
//        Nsmoker.setSelected(true);
//        Smoker.setToggleGroup(group);
//
//        final ToggleGroup group2 = new ToggleGroup();
//        Ndrinker.setToggleGroup(group2);
//        Ndrinker.setSelected(true);
//        Drinker.setToggleGroup(group2);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @FXML
    private void AjouterMembre(ActionEvent event) throws AddressException, MessagingException {
        try {
            boolean valid = true;
//            if (Adress.getText().equals("")) {
//                addressLabel.setText("Field is empty !");
//                addressLabel.setVisible(true);
//                valid = false;
//            } else {
//                addressLabel.setText("");
//            }
            
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
            
            if (maritalBox.getValue() == null) {
                maritalLabel.setText("Field is empty !");
                maritalLabel.setVisible(true);
                valid = false;
            } else {
                maritalLabel.setText("");
            }
            
            if (importanceBox.getValue() == null) {
                importanceLabel.setText("Field is empty !");
                importanceLabel.setVisible(true);
                valid = false;
            } else {
                importanceLabel.setText("");
            }
            
            if (relationBox.getValue() == null) {
                relationLabel.setText("Field is empty !");
                relationLabel.setVisible(true);
                valid = false;
            } else {
                relationLabel.setText("");
            }
            
            if (proximityBox.getValue() == null) {
                proximityLabel.setText("Field is empty !");
                proximityLabel.setVisible(true);
                valid = false;
            } else {
                proximityLabel.setText("");
            }
            if(!valid) return;
            
            m.setAddress(new Address(0, 0, "Tunisie", "Ariana"));
            m.setChildrenNumber(Integer.parseInt(childrenNum.getText()));
            m.setMinAge(Integer.parseInt(minAge.getText()));
            m.setMaxAge(Integer.parseInt(maxAge.getText()));
            m.setHeight(Float.parseFloat(height.getText()));
            m.setBodyType(BodyType.valueOf(bodyBox.getValue()));
            m.setReligion(Religion.valueOf(religionBox.getValue()));
            m.setReligionImportance(Importance.valueOf(importanceBox.getValue()));
            m.setProximity(Proximity.valueOf(proximityBox.getValue()));
            m.getPreferedRelations().add(RelationType.valueOf(relationBox.getValue()));
            m.getPreferedStatuses().add(MaritalStatus.valueOf(maritalBox.getValue()));
            m.setDrinker(Drinker.isSelected());
            m.setSmoker(Smoker.isSelected());
            
            
            //TODO
            
            MemberService.getInstance().create(m);
		
            
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, InscriptionDetailsViewController.class.getName(), null);
        }
    }

    @FXML
    private void returnIns(ActionEvent event) {

        container3.switchView("InsView");
    }

}