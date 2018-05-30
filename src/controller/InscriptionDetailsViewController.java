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
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import services.MemberService;
import util.GooglePlacesAPI;
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
//    private ToggleGroup maritalGroup;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXTextField country;
    @FXML
    private JFXTextField city;
    private VBox statusVBox;

//    private List<CheckBox> statusesCheckBoxes = new ArrayList<>();
//    private List<CheckBox> relationsCheckBoxes = new ArrayList<>();

    private VBox relationsVBox;
    @FXML
    private JFXTextField phoneText;

    private InscriptionContainerViewController container;
    
    private List<Address> addresses;
    private SuggestionProvider<Address> provider;
    

    public void setContainer(InscriptionContainerViewController container) {
        this.container = container;
    }
    
    @FXML
    private void checkPlaces(KeyEvent event) {
        addresses.clear();
        addresses.addAll(GooglePlacesAPI.autoCompleteAddress(city.getText()));
        provider.clearSuggestions();
        provider.addPossibleSuggestions(addresses);
    }
    
    private EventHandler<AutoCompletionBinding.AutoCompletionEvent<Address>> manageAutoCompletion =
        new EventHandler<AutoCompletionBinding.AutoCompletionEvent<Address>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<Address> event) {
                Address address = GooglePlacesAPI.getPlaceDetails(event.getCompletion());
                member.getAddress().setCity(address.getCity());
                member.getAddress().setCountry(address.getCountry());
                member.getAddress().setLatitude(address.getLatitude());
                member.getAddress().setLongitude(address.getLongitude());
                city.setText(member.getAddress().getCity());
                country.setText(member.getAddress().getCountry());
            }
        };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addresses = new ArrayList<>();
        provider = SuggestionProvider.create(addresses);
        AutoCompletionTextFieldBinding actfb = new AutoCompletionTextFieldBinding<>(city, provider);
        actfb.setOnAutoCompleted(manageAutoCompletion);
        
        
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
//        for (Enumerations.MaritalStatus maritalStatus : Enumerations.MaritalStatus.values()) {
//            CheckBox cb = new CheckBox(maritalStatus.name());
//            cb.setId(maritalStatus.ordinal() + "");
//            cb.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    CheckBox b = (CheckBox) event.getTarget();
//                    if (b.isSelected()) {
//                        statusesCheckBoxes.add(b);
//                    } else {
//                        statusesCheckBoxes.remove(b);
//                    }
//                }
//            });
//            statusVBox.getChildren().add(cb);
//        }

//        for (RelationType type : RelationType.values()) {
//            CheckBox cb = new CheckBox(type.name());
//            cb.setId(type.ordinal() + "");
//            cb.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    CheckBox c = (CheckBox) event.getTarget();
//                    if (c.isSelected()) {
//                        relationsCheckBoxes.add(c);
//                    } else {
//                        relationsCheckBoxes.remove(c);
//                    }
//                }
//            });
//            relationsVBox.getChildren().add(cb);
//        }
    }

    public void setMember(Member member) {
        this.member = member;
        member.setAddress(new Address());
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
            
//            if(country.getText().equals("")){
//                addressLabel.setText("Invalid address");
//                addressLabel.setVisible(true);
//                valid = false;
//            }else{
//                addressLabel.setText("");
//            }

            if (!valid) {
                return;
            }

//            m.setAddress(new Address(0, 0, country.getText(), city.getText()));
            m.setChildrenNumber(Integer.parseInt(childrenNum.getText()));
            m.setMinAge(Integer.parseInt(minAge.getText()));
            m.setMaxAge(Integer.parseInt(maxAge.getText()));
            m.setHeight(Float.parseFloat(height.getText().replace(",", ".")));
            m.setBodyType(BodyType.valueOf(bodyBox.getValue()));
            m.setReligion(Religion.valueOf(religionBox.getValue()));
            m.setReligionImportance(Importance.valueOf(importanceBox.getValue()));
            m.setPhone(Integer.parseInt(phoneText.getText()));
            m.setMaritalStatus(MaritalStatus.valueOf(statusBox.getValue()));
            m.setDrinker(Drinker.isSelected());
            m.setSmoker(Smoker.isSelected());
            m.setAbout(about.getText());

//            for (CheckBox cb : statusesCheckBoxes) {
//                m.getPreferedStatuses().add(MaritalStatus.values()[Integer.parseInt(cb.getId())]);
//            }
//            for (CheckBox cb : relationsCheckBoxes) {
//                m.getPreferedRelations().add(RelationType.values()[Integer.parseInt(cb.getId())]);
//            }

            //TODO
            MemberService.getInstance().create(m);
            SendMail sm = new SendMail(m.getEmail(), " Confirmation d'inscription ", " Bonjour " + m.getFirstname() + "Felicitations! Vous etes maintenant inscrit à MySoulMate");

            SendMessage s = new SendMessage();
            s.sendSms("Felicitations! Vous etes Maintenant Inscrit à Mysoulmate ", phoneText.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have been successfully registered.", ButtonType.OK);
            alert.showAndWait();
            MySoulMate.getInstance().showAuthenticationView();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, InscriptionDetailsViewController.class.getName(), null);
        }
    }

    @FXML
    private void returnIns(ActionEvent event) {
        FXMLLoader loader = container.switchView("InsView");
        ((InsViewController) loader.getController()).setContainer(container);
    }

}
