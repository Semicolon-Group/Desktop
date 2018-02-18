/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import models.Enumerations;
import models.Member;
import services.MemberService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class EditProfileViewController implements Initializable {

    @FXML
    private ToggleGroup gender;
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private TextField heightFiled;
    @FXML
    private ComboBox<Enumerations.BodyType> bodyTypeBox;
    @FXML
    private ToggleGroup smoker;
    @FXML
    private Label drinkerLabel;
    @FXML
    private ToggleGroup drinker;
    @FXML
    private ComboBox<Enumerations.Religion> ReligionBox;
    @FXML
    private ComboBox<Enumerations.Importance> religonImportanceBox;
    @FXML
    private ComboBox<Enumerations.MaritalStatus> civicStateBox;
    
    private Member member;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private RadioButton smokerRadio;
    @FXML
    private RadioButton noneSmokerRadio;
    @FXML
    private RadioButton drinkerRadio;
    @FXML
    private RadioButton noneDrinkerRadio;
    @FXML
    private TextField childNumberFiled;
    
    private Stage dialog;
    
    private SelfProfileViewController controller;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        heightFiled.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        childNumberFiled.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        // TODO
    }  
    
    public void setMember(Member member){
        this.member = member;
        populateFields();
    }
    
    public void setController(SelfProfileViewController controller){
        this.controller = controller;
    }
    
    public void setDialog(Stage dialog){
        this.dialog = dialog;
    }
    
    private void populateFields(){
        firstNameField.setText(member.getFirstname());
        lastnameField.setText(member.getLastname());
        cityField.setText(member.getAddress().getCity());
        countryField.setText(member.getAddress().getCountry());
        if(member.isGender()) maleRadio.setSelected(true); else femaleRadio.setSelected(true);
        birthdayPicker.setValue(member.getBirthDate().toLocalDate());
        heightFiled.setText(member.getHeight()+"");
        bodyTypeBox.getItems().setAll(Enumerations.BodyType.values());
        bodyTypeBox.getSelectionModel().select(member.getBodyType());
        if(member.isSmoker()) smokerRadio.setSelected(true); else noneSmokerRadio.setSelected(true);
        if(member.isDrinker()) drinkerRadio.setSelected(true); else noneDrinkerRadio.setSelected(true);
        ReligionBox.getItems().setAll(Enumerations.Religion.values());
        ReligionBox.getSelectionModel().select(member.getReligion());
        religonImportanceBox.getItems().setAll(Enumerations.Importance.values());
        religonImportanceBox.getSelectionModel().select(member.getReligionImportance());
        civicStateBox.getItems().setAll(Enumerations.MaritalStatus.values());
        civicStateBox.getSelectionModel().select(member.getMaritalStatus());
        childNumberFiled.setText(member.getChildrenNumber()+"");
    }

    @FXML
    private void update(ActionEvent event) {
        try {
            member.setFirstname(firstNameField.getText());
            member.setLastname(lastnameField.getText());
            member.getAddress().setCity(cityField.getText());
            member.getAddress().setCountry(countryField.getText());
            if(maleRadio.isSelected()) member.setGender(true); else member.setGender(false);
            System.out.println(java.sql.Date.valueOf(birthdayPicker.getValue()));
            member.setBirthDate(java.sql.Date.valueOf(birthdayPicker.getValue()));
            member.setHeight(Float.parseFloat(heightFiled.getText()));
            member.setBodyType(bodyTypeBox.getSelectionModel().getSelectedItem());
            if(smokerRadio.isSelected()) member.setSmoker(true); else member.setSmoker(false);
            if(drinkerRadio.isSelected()) member.setDrinker(true); else member.setDrinker(false);
            member.setReligion(ReligionBox.getSelectionModel().getSelectedItem());
            member.setReligionImportance(religonImportanceBox.getSelectionModel().getSelectedItem());
            member.setMaritalStatus(civicStateBox.getSelectionModel().getSelectedItem());
            member.setChildrenNumber(Integer.parseInt(childNumberFiled.getText()));
            
            MemberService.getInstance().update(member);
            controller.updateMemberInfo();
            dialog.close();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, EditProfileViewController.class.getName(), "Connection à la base de donnée impossible");
        }
    }
    
}
