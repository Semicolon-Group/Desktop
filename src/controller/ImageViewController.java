/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    
    private Image image;
    private AnchorPane parentAnchorPane;
    private SelfProfileViewController selfProfileViewController;
    private OthersProfileViewController othersProfileViewController;

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
    
    public void setImage(Image image){
        this.image = image;
        imageContainer.setImage(image);
    }

    @FXML
    private void dismiss(MouseEvent event) {
        if(event.getTarget() instanceof VBox){
            parentAnchorPane.getChildren().remove(parentAnchorPane.getChildren().size()-1);
        }
    }
    
}
