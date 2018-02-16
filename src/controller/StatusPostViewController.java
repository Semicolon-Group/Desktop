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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import models.StatusPost;

/**
 * FXML Controller class
 *
 * @author Elyes
 */
public class StatusPostViewController implements Initializable {

    private VBox post;
    @FXML
    private ImageView photo;
    @FXML
    private Label name;
    @FXML
    private Label time;
    @FXML
    private TextArea status;
    @FXML
    private ImageView smile;
    @FXML
    private ImageView love;
    @FXML
    private ImageView laugh;
    @FXML
    private ImageView scowl;
    @FXML
    private VBox statusPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void fill(Image photo, String text, String name, String time){
        this.photo.setImage(photo);
        this.status.setText(text);
        this.name.setText(name);
        this.time.setText(time);
    }
    

    public void setPhoto(ImageView photo) {
        this.photo.setImage(photo.getImage());
    }

    public void setName(Label name) {
        this.name = name;
    }

    public void setTime(Label time) {
        this.time = time;
    }

    public void setStatus(TextArea status) {
        this.status = status;
    }

    public VBox getPost() {
        return post;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public Label getName() {
        return name;
    }

    public Label getTime() {
        return time;
    }

    public TextArea getStatus() {
        return status;
    }

    public ImageView getSmile() {
        return smile;
    }

    public ImageView getLove() {
        return love;
    }

    public ImageView getLaugh() {
        return laugh;
    }

    public ImageView getScowl() {
        return scowl;
    }
    
}
