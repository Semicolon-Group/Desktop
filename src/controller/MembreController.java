/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import static java.lang.Thread.State.NEW;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Address;
import models.Enumerations;
import models.Member;
import services.AddressService;
import services.MemberService;
import services.Service;

/**
 * FXML Controller class
 *
 * @author vaider
 */
public class MembreController extends Service implements Initializable {
    @FXML
    private TableView<Member> table;
    @FXML
    private Button bt_affiche;
    @FXML
    private TableColumn<MemberService, String> Pseudo;
    @FXML
    private TableColumn<MemberService, String> firstname;
    @FXML
    private TableColumn<MemberService, String> lastname;
    @FXML
    private TableColumn<MemberService, String> Email;
    @FXML
    private TableColumn<MemberService, String> password;
    @FXML
    private TableColumn<MemberService, Date> birth_date;
    @FXML
    private TableColumn<MemberService, Boolean> gender;
    @FXML
    private TableColumn<MemberService, Float> height;
    @FXML
    private TableColumn<MemberService, String> body_type;
    @FXML
    private TableColumn<MemberService, Integer> children_nember;
    @FXML
    private TableColumn<MemberService, String> relegion;
    @FXML
    private TableColumn<MemberService, String> relegion_importance;
    @FXML
    private TableColumn<MemberService, Boolean> smoker;
    @FXML
    private TableColumn<MemberService, Boolean> drinker;
    @FXML
    private TableColumn<MemberService, Integer> min_age;
    @FXML
    private TableColumn<MemberService, Integer> max_age;
    @FXML
    private TableColumn<MemberService, String> proximity;
    @FXML
    private TableColumn<MemberService, Timestamp> last_login;
    @FXML
    private TableColumn<MemberService, Short> locked;
    @FXML
    private TableColumn<MemberService, String> ip;
    @FXML
    private TableColumn<MemberService, Integer> port;
    @FXML
    private TableColumn<MemberService, String> adresse;
    private ObservableList<Member> data;
    @FXML
    private Button bt_banne;
    @FXML
    private TextField txt_lock;
    @FXML
    private TextField txt_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txt_id.setVisible(false);
         data= FXCollections.observableArrayList();
            List<Member> membres = null;
        try {
            membres = MemberService.getInstance().getAll(new Member());
        } catch (SQLException ex) {
            Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
                for(Member i : membres){
                    data.add(i);
                }
                
        Pseudo.setCellValueFactory(new PropertyValueFactory<>("Pseudo"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        birth_date.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        body_type.setCellValueFactory(new PropertyValueFactory<>("body_type"));
        children_nember.setCellValueFactory(new PropertyValueFactory<>("children_nember"));
        relegion.setCellValueFactory(new PropertyValueFactory<>("relegion"));
        relegion_importance.setCellValueFactory(new PropertyValueFactory<>("relegion_importance"));
        smoker.setCellValueFactory(new PropertyValueFactory<>("smoker"));
        drinker.setCellValueFactory(new PropertyValueFactory<>("drinker"));
        min_age.setCellValueFactory(new PropertyValueFactory<>("min_age"));
        max_age.setCellValueFactory(new PropertyValueFactory<>("max_age"));
        proximity.setCellValueFactory(new PropertyValueFactory<>("proximity"));
        last_login.setCellValueFactory(new PropertyValueFactory<>("last_login"));
        locked.setCellValueFactory(new PropertyValueFactory<>("locked"));
        ip.setCellValueFactory(new PropertyValueFactory<>("ip"));
        port.setCellValueFactory(new PropertyValueFactory<>("port"));
        //adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        
        
        
        
        table.setItems(null);
        table.setItems(data);
    }    

    @FXML
    private void affichage(ActionEvent event) throws SQLException {
        

       
        data= FXCollections.observableArrayList();
            List<Member> membres=MemberService.getInstance().getAll(new Member());
                for(Member i : membres){
                    data.add(i);
                }
                
        Pseudo.setCellValueFactory(new PropertyValueFactory<>("Pseudo"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        birth_date.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        body_type.setCellValueFactory(new PropertyValueFactory<>("body_type"));
        children_nember.setCellValueFactory(new PropertyValueFactory<>("children_nember"));
        relegion.setCellValueFactory(new PropertyValueFactory<>("relegion"));
        relegion_importance.setCellValueFactory(new PropertyValueFactory<>("relegion_importance"));
        smoker.setCellValueFactory(new PropertyValueFactory<>("smoker"));
        drinker.setCellValueFactory(new PropertyValueFactory<>("drinker"));
        min_age.setCellValueFactory(new PropertyValueFactory<>("min_age"));
        max_age.setCellValueFactory(new PropertyValueFactory<>("max_age"));
        proximity.setCellValueFactory(new PropertyValueFactory<>("proximity"));
        last_login.setCellValueFactory(new PropertyValueFactory<>("last_login"));
        locked.setCellValueFactory(new PropertyValueFactory<>("locked"));
        ip.setCellValueFactory(new PropertyValueFactory<>("ip"));
        port.setCellValueFactory(new PropertyValueFactory<>("port"));
        //adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        
        
        
        
        table.setItems(null);
        table.setItems(data);
                    
    
            } 

   

    @FXML
    private void Banner_click(MouseEvent event) {
        MemberService ms=MemberService.getInstance();
        Member m=(Member) table.getSelectionModel().getSelectedItem();
        txt_lock.setText(String.valueOf(m.getLocked()));
        txt_id.setText(String.valueOf(m.getId()));
    }

    @FXML
    private void BannerAction(ActionEvent event) throws SQLException {
        MemberService ms=MemberService.getInstance();
        ms.updatelock(Integer.parseInt(txt_id.getText()),Short.parseShort(txt_lock.getText()));
    }
    }
    

