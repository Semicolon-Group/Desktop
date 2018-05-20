/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.NumberStringConverter;
import models.Address;
import models.Enumerations;
import models.Member;
import org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent;
import services.MemberService;
import util.GooglePlacesAPI;

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
    private List<Address> addresses;
    private SuggestionProvider<Address> provider;
    @FXML
    private TextField phoneNumberField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        heightFiled.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        childNumberFiled.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        phoneNumberField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        cityField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue && !addresses.stream().anyMatch(a -> a.getCity().equals(cityField.getText())))
                    countryField.setText("");
            }
        });
        addresses = new ArrayList<>();
        provider = SuggestionProvider.create(addresses);
        AutoCompletionTextFieldBinding actfb = new AutoCompletionTextFieldBinding<>(cityField, provider);
        actfb.setOnAutoCompleted(manageAutoCompletion);
    }
    
    private EventHandler<AutoCompletionEvent<Address>> manageAutoCompletion =
        new EventHandler<AutoCompletionEvent<Address>>() {
            @Override
            public void handle(AutoCompletionEvent<Address> event) {
                Address address = GooglePlacesAPI.getPlaceDetails(event.getCompletion());
                member.getAddress().setCity(address.getCity());
                member.getAddress().setCountry(address.getCountry());
                member.getAddress().setLatitude(address.getLatitude());
                member.getAddress().setLongitude(address.getLongitude());
                cityField.setText(member.getAddress().getCity());
                countryField.setText(member.getAddress().getCountry());
            }
        };
    
    @FXML
    private void checkPlaces(KeyEvent event) {
        cityField.getStyleClass().remove("error");
        addresses.clear();
        addresses.addAll(GooglePlacesAPI.autoCompleteAddress(cityField.getText()));
        if(addresses.isEmpty())
            countryField.setText("");
        provider.clearSuggestions();
        provider.addPossibleSuggestions(addresses);
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
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getScene().setFill(Color.TRANSPARENT);
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
        phoneNumberField.setText(member.getPhone()+"");
    }

    @FXML
    private void update(ActionEvent event) {
        try {
            if(firstNameField.getText().isEmpty()){
                activateError(firstNameField, "Firstname");
                return;
            }else if(lastnameField.getText().isEmpty()){
                activateError(lastnameField, "Lastname");
                return;
            }else if(birthdayPicker.getValue() == null){
                activateError(birthdayPicker, "Birthdate");
                return;
            }else if(((new Date()).getYear() - java.sql.Date.valueOf(birthdayPicker.getValue()).getYear()) < 18){
                birthdayPicker.getStyleClass().add("error");
                Alert alert = new Alert(Alert.AlertType.ERROR, "You must at least 18 years old!", ButtonType.OK);
                alert.show();
                return;
            }else if(heightFiled.getText().isEmpty()){
                activateError(heightFiled, "Height");
                return;
            }else if(cityField.getText().isEmpty()){
                activateError(cityField, "Address");
                return;
            }else if(countryField.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid address!", ButtonType.OK);
                alert.show();
                cityField.getStyleClass().add("error");
                return;
            }else if(childNumberFiled.getText().isEmpty()){
                activateError(childNumberFiled, "Number of children");
                return;
            }else if(phoneNumberField.getText().isEmpty()){
                activateError(phoneNumberField, "Phone number");
                return;
            }else{
                int n = -1;
                try{
                    n = Integer.parseInt(childNumberFiled.getText());
                }catch(NumberFormatException ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number of children!", ButtonType.OK);
                    alert.show();
                    childNumberFiled.getStyleClass().add("error");
                    return;
                }
                if(n < 0 || n > 15){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number of children!", ButtonType.OK);
                    alert.show();
                    childNumberFiled.getStyleClass().add("error");
                    return;
                }
                double h = -1;
                try{
                    h = Double.parseDouble(heightFiled.getText());
                }catch(NumberFormatException ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid height!", ButtonType.OK);
                    alert.show();
                    heightFiled.getStyleClass().add("error");
                    return;
                }
                if(h < 1 || h > 2.5){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid height!", ButtonType.OK);
                    alert.show();
                    heightFiled.getStyleClass().add("error");
                    return;
                }
            }
            member.setPhone(Integer.parseInt(phoneNumberField.getText().replace(",", "")));
            member.setFirstname(firstNameField.getText());
            member.setLastname(lastnameField.getText());
            if(maleRadio.isSelected()) member.setGender(true); else member.setGender(false);
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
            util.Logger.writeLog(ex, EditProfileViewController.class.getName(), "Connexion to database failed");
        }
    }
    
    private void activateError(Node field, String fieldName){
        field.getStyleClass().add("error");
        Alert alert = new Alert(Alert.AlertType.ERROR, fieldName+" can't be emply!", ButtonType.OK);
        alert.show();
    }

    @FXML
    private void close(MouseEvent event) {
        dialog.close();
    }

    @FXML
    private void removeError(KeyEvent event) {
        ((Node)event.getTarget()).getStyleClass().remove("error");
    }

    @FXML
    private void removeDateError(ActionEvent event) {
        if(birthdayPicker.getValue()!=null)
            ((Node)event.getTarget()).getStyleClass().remove("error");
    }
    
}
