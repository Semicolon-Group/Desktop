/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.MainAchref.container4;
import static controller.MySoulMate.UPLOAD_URL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Enumerations;
import models.Signal;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterSignalViewController implements Initializable {

    @FXML
    private RadioButton contenu;
    @FXML
    private RadioButton racisme;
    @FXML
    private RadioButton violence;
    @FXML
    private RadioButton harcelement;
    @FXML
    private Button continuer;
    @FXML
    private RadioButton autre;
    @FXML
    private RadioButton fauxprofil;
    
    public static Signal signal;
    @FXML
    private ToggleGroup reasons;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView signalPhoto;
    
    public static Signal sign;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        signal = new Signal();
        if(contenu.isSelected()){
            signal.setReason(Enumerations.SignalReason.INAPPROPRIATE_CONTENT);
        }else if(violence.isSelected()){
            signal.setReason(Enumerations.SignalReason.VIOLENCE);
        }else if(racisme.isSelected()){
            signal.setReason(Enumerations.SignalReason.RACISM);
        }else if(harcelement.isSelected()){
            signal.setReason(Enumerations.SignalReason.HARRASSMENT);
        }else if(fauxprofil.isSelected()){
            signal.setReason(Enumerations.SignalReason.FALSE_PROFILE);
        }else{
            signal.setReason(Enumerations.SignalReason.OTHER);
        }
//         Image img1 = new Image(UPLOAD_URL + sign.getUrlPhoto());
//            signalPhoto.setImage(img1);
    }    
    
    @FXML
    private void Continuer(ActionEvent event) {
         FXMLLoader loader = container4.switchView("AjouterSignalDetailsView");
        ((AjouterSignalDetailsViewController)loader.getController()).setSignal(signal);
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Platform.exit();
    }
}
