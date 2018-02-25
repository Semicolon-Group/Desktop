/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Address;
import models.Answer;
import models.Block;
import models.Enumerations;
import models.Enumerations.PhotoType;
import models.Like;
import models.Member;
import models.Photo;
import models.Signal;
import services.AddressService;
import services.AnswerService;
import services.BlockService;
import services.LikeService;
import services.MemberService;
import services.PhotoService;
import services.SignalService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class OthersProfileViewController implements Initializable {

    @FXML
    private AnchorPane coverContainer;
    @FXML
    private ImageView coverImage;
    @FXML
    private AnchorPane profileImgPane;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label bdLabel;
    @FXML
    private Label heightLabel;
    @FXML
    private Label bodyTypeLabel;
    @FXML
    private Label smokerLabel;
    @FXML
    private Label drinkerLabel;
    @FXML
    private Label religionLabel;
    @FXML
    private Label civilStatusLabel;
    @FXML
    private Label childNumLabel;
    @FXML
    private Label createdAtLabel;
    @FXML
    private Text aboutText;
    @FXML
    private ScrollPane photoScrollPane;
    @FXML
    private VBox photosVBox;
    private GlobalViewController controller;
    List<Member> members;
    
    private int userId;
    @FXML
    private Button likeButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button dislikeButton;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private AnchorPane sideMenu;
    @FXML
    private HBox blockBox;
    @FXML
    private HBox signalBox;
    @FXML
    private VBox answersVBox;
    @FXML
    private VBox likesVBox;
    @FXML
    private Button meetButton;
    @FXML
    private Label prefRelationsLabel;
    @FXML
    private Label prefStatusLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = GlobalViewController.getInstance();
        photosVBox.fillWidthProperty().bind(photoScrollPane.fitToWidthProperty());
        members = new ArrayList<>();
    }
    
    private void makeMemberLikePane(){
        try {
            List<Like> likes = LikeService.getInstance().getAll(new Like(userId, 0, null)).stream().limit(6)
                    .collect(Collectors.toList());
            likesVBox.getChildren().clear();
            for(Like like: likes){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LikedUserView.fxml"));
                AnchorPane likedUserPane = loader.load();
                ((LikedUserViewController)loader.getController()).setLike(like);
                ((LikedUserViewController)loader.getController()).setController(controller);
                likesVBox.getChildren().add(likedUserPane);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    public void makeAnswersPane(){
        try {
            answersVBox.getChildren().clear();
            List<Answer> answers = AnswerService.getInstance().getAll(new Answer(0, null, null, userId));
            for(Answer answer : answers){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OthersAnswerView.fxml"));
                AnchorPane pane = loader.load();
                ((OthersAnswerViewController)loader.getController()).setAnswer(answer);
                answersVBox.getChildren().add(pane);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void checkForLike(){
        try {
            if(LikeService.getInstance().get(new Like(MySoulMate.MEMBER_ID, userId, null))!=null){
                likeButton.setVisible(false);
                dislikeButton.setVisible(true);
                meetButton.setVisible(true);
                messageButton.setVisible(true);
            }else{
                likeButton.setVisible(true);
                dislikeButton.setVisible(false);
                meetButton.setVisible(false);
                messageButton.setVisible(false);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
        }
    }
    
    private void populate(){
        checkForLike();
        makeCoverPicture();
        makeProfilePicture();
        populateFields();
        populatePhotosPane();
    }
    
    public void setUserId(int userId){
        this.userId = userId;
        populate();
        makeMemberLikePane();
        makeAnswersPane();
    }
    
    private void populatePhotosPane(){
        photosVBox.getChildren().clear();
        try {
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(userId));
            
            for(Photo photo:photos){
                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.setAlignment(Pos.CENTER);
                ImageView imageView = new ImageView(MySoulMate.UPLOAD_URL+photo.getUrl());
                imageView.setCursor(Cursor.HAND);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showImage(e));
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(300);
                hBox.getChildren().add(imageView);
                photosVBox.getChildren().add(hBox);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void showImage(MouseEvent event){
        try {
            ImageView image = ((ImageView)event.getTarget());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ImageView.fxml"));
            Pane newLoadedPane =  loader.load();
            ((ImageViewController)loader.getController()).setImage(image);
            ((ImageViewController)loader.getController()).setParentAnchorPane(mainAnchorPane);
            mainAnchorPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void populateFields(){
        try {
            MemberService memberService = MemberService.getInstance();
            Member member = memberService.get(new Member(userId));
            nameLabel.setText(member.getFirstname()+" "+member.getLastname());
            String age = ""+((new Date()).getYear()-member.getBirthDate().getYear());
            ageLabel.setText(age);
            addressLabel.setText(member.getAddress().getCity()+", "+member.getAddress().getCountry());
            genderLabel.setText(member.isGender()?"Male":"Female");
            bdLabel.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(member.getBirthDate()));
            heightLabel.setText(member.getHeight()+"");
            bodyTypeLabel.setText(member.getBodyType().name().substring(0, 1) + member.getBodyType().name().substring(1).toLowerCase());
            smokerLabel.setText(member.isSmoker()?"Yes":"No");
            drinkerLabel.setText(member.isDrinker()?"Yes":"No");
            religionLabel.setText(member.getReligion().name().substring(0, 1) + member.getReligion().name().substring(1).toLowerCase());
            childNumLabel.setText(member.getChildrenNumber()+"");
            aboutText.setText(member.getAbout());
            civilStatusLabel.setText(member.getMaritalStatus().name().substring(0, 1) + member.getMaritalStatus().name().substring(1).toLowerCase());
            createdAtLabel.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(member.getCreatedAt()));
            String relationsString = "";
            for(Enumerations.RelationType type : member.getPreferedRelations()){
                relationsString+=type.name().toLowerCase()+", ";
            }
            prefRelationsLabel.setText(relationsString.isEmpty()?relationsString:relationsString.substring(0, relationsString.length()-2));
            String statusesString = "";
            for(Enumerations.MaritalStatus status : member.getPreferedStatuses()){
                statusesString+=status.name().toLowerCase()+", ";
            }
            prefStatusLabel.setText(statusesString.isEmpty()?statusesString:statusesString.substring(0, statusesString.length() -2));
            
            
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), "Connection de database failed");
        }catch (Exception ex){
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void makeProfilePicture(){
        try {
            Circle imageClip = new Circle(profileImage.getX()+(profileImage.getFitWidth()/2), profileImage.getY()+(profileImage.getFitHeight()/2), 135);
            profileImage.setClip(imageClip);
            Circle contenairClip = new Circle(profileImgPane.getLayoutX()+(profileImgPane.getPrefWidth()/2), profileImgPane.getLayoutY()+(profileImgPane.getPrefHeight()/2), 145);
            profileImgPane.setClip(contenairClip);
            Photo photo = PhotoService.getInstance().get(new Photo(0, userId, null, null, PhotoType.PROFILE));
            String photoPath ="";
            if(photo == null){
                photoPath = "/view/assets/icons/member.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photo.getUrl();
            }
            profileImage.setImage(new Image(photoPath));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
        }
    }
    
    private void makeCoverPicture(){
        try {
            coverImage.fitWidthProperty().bind(coverContainer.widthProperty());
            Photo photo = PhotoService.getInstance().get(new Photo(0, userId, null, null, PhotoType.COVER));
            String photoPath ="";
            if(photo== null){
                photoPath = "/view/assets/img/banner.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photo.getUrl();
            }
            coverImage.setImage(new Image(photoPath));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
        }
    }

    @FXML
    private void startChat(ActionEvent event) {
    }

    @FXML
    private void doLike(ActionEvent event) {
        try {
            LikeService.getInstance().create(new Like(MySoulMate.MEMBER_ID, userId, null));
            checkForLike();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
        }
    }

    @FXML
    private void doUnlike(ActionEvent event) {
        try {
            LikeService.getInstance().delete(new Like(MySoulMate.MEMBER_ID, userId, null));
            checkForLike();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
        }
    }

    @FXML
    private void showMenu(MouseEvent event) {
        if(sideMenu.isVisible()){
            hideSideMenu();
            return;
        }
        sideMenu.setPrefWidth(202);
        sideMenu.setPrefHeight(164);
        sideMenu.setVisible(true);
    }
    
    private void hideSideMenu(){
        sideMenu.setPrefWidth(0);
        sideMenu.setPrefHeight(0);
        sideMenu.setVisible(false);
    }

    @FXML
    private void showBlockAlert(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to block this person?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait().ifPresent(response -> {
            hideSideMenu();
            if(response == ButtonType.YES){
                createBlock();
            }
        });
    }
    
    private void createBlock(){
        try {
            BlockService.getInstance().create(new Block(MySoulMate.MEMBER_ID, userId, null));
            LikeService.getInstance().delete(new Like(MySoulMate.MEMBER_ID, userId, null));
            LikeService.getInstance().delete(new Like(userId, MySoulMate.MEMBER_ID, null));
            controller.setMainContent("/view/SelfProfileView.fxml");
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
        }
    }

    @FXML
    private void showSignalAlert(MouseEvent event) {
        ChoiceDialog<Enumerations.SignalReason> dialog = new ChoiceDialog<>(Enumerations.SignalReason.values()[0], Enumerations.SignalReason.values());
        dialog.setTitle("Report");
        dialog.setContentText("Select the reason of the report");
        dialog.showAndWait().ifPresent(reason -> {
            try {
                SignalService.getInstance().create(new Signal(MySoulMate.MEMBER_ID, userId, reason, false, null));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to block this person?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait().ifPresent(response -> {
                    hideSideMenu();
                    if(response == ButtonType.YES){
                        createBlock();
                    }
                });
            } catch (SQLException ex) {
                util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
            }
        });
    }
    
    @FXML
    private void showSug(ActionEvent event) {
        try {
            FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/RecommandationView.fxml");
            Address selfAddress = AddressService.getInstance().get(new Address(MySoulMate.MEMBER_ID));
            Address othersAddress = AddressService.getInstance().get(new Address(userId));
            Address centerAddress = new Address(
                    (selfAddress.getLongitude()+othersAddress.getLongitude())/2, 
                    (selfAddress.getLatitude()+othersAddress.getLatitude())/2, 
                    "", "");
            ((RecommandationViewController)loader.getController()).setAddress(centerAddress, userId);
        }catch (SQLException ex) {
            Logger.getLogger(OthersProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showCoverPic(MouseEvent event) {
        try {
            ImageView image = ((ImageView)event.getTarget());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ImageView.fxml"));
            Pane newLoadedPane =  loader.load();
            ((ImageViewController)loader.getController()).setImage(image);
            ((ImageViewController)loader.getController()).setParentAnchorPane(mainAnchorPane);
            mainAnchorPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }

    @FXML
    private void showProfilePic(MouseEvent event) {
        try {
            ImageView image = ((ImageView)event.getTarget());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ImageView.fxml"));
            Pane newLoadedPane =  loader.load();
            ((ImageViewController)loader.getController()).setImage(image);
            ((ImageViewController)loader.getController()).setParentAnchorPane(mainAnchorPane);
            mainAnchorPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
}
