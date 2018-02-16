/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Member;
import services.MemberService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLInscritController implements Initializable {

    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button confirmer;
    @FXML
    private Button cancel;
    @FXML
    private TextField pseudo;
    @FXML
    private DatePicker birth_date;

    /**
     * Initializes the controller class.
     */
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void ajouterMem(ActionEvent event) throws SQLException {
        
        
        MemberService ms = MemberService.getInstance();
        Member m = new  Member(pseudo.getText(), first_name.getText(), last_name.getText(), email.getText(), password.getText(), Date.valueOf(birth_date.getValue()));
        ms.create(m);
    }

    
}
