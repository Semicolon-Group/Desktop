/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Enumerations;
import models.Photo;
import services.PhotoService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class ImageViewController implements Initializable {

    @FXML
    private AnchorPane imageBackground;
    @FXML
    private ImageView imageContainer;
    
    private ImageView image;
    private AnchorPane parentAnchorPane;
    private SelfProfileViewController selfProfileViewController;
    private OthersProfileViewController othersProfileViewController;
    @FXML
    private AnchorPane buttonsPane;
    
    private Photo photo;
    private boolean owner;
    private SelfProfileViewController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setParentAnchorPane(AnchorPane pane){
        this.parentAnchorPane = pane;
    }
    
    public void setImage(ImageView image){
        this.image = image;
        this.owner = false;
        imageContainer.setImage(image.getImage());
        GlobalViewController.getInstance().lockScrollToTop();
    }
    
    public void setParams(ImageView image, SelfProfileViewController controller){
        this.image = image;
        this.owner = true;
        this.controller = controller;
        photo = PhotoService.getInstance().getPhoto(Integer.parseInt(image.getId()));
        imageContainer.setImage(image.getImage());
        GlobalViewController.getInstance().lockScrollToTop();
    }

    @FXML
    private void dismiss(MouseEvent event) {
        if(event.getTarget() instanceof VBox){
            parentAnchorPane.getChildren().remove(parentAnchorPane.getChildren().size()-1);
            GlobalViewController.getInstance().releaseScroll();
        }
    }

    @FXML
    private void showButtons(MouseEvent event) {
        buttonsPane.setVisible(owner && (photo.getType()==Enumerations.PhotoType.REGULAR));
    }

    @FXML
    private void hideButtons(MouseEvent event) {
        buttonsPane.setVisible(false);
    }

    @FXML
    private void setImageAsCover(ActionEvent event) {
        PhotoService.getInstance().setAsCoverPhoto(photo.getId());
        buttonsPane.setVisible(false);
        controller.updatePictures();
    }

    @FXML
    private void setImageAsProfile(ActionEvent event) {
        PhotoService.getInstance().setAsProfilePhoto(photo.getId());
        buttonsPane.setVisible(false);
        controller.updatePictures();
    }
    
}
