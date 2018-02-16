/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import models.Like;
import models.Member;
import models.Photo;
import services.LikeService;
import services.MemberService;
import services.PhotoService;

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
    private Label matchPercentageLabel;
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
    private ImageView likeImage1;
    private Label likeName1;
    private Label likeDate1;
    private ImageView likeImage2;
    private Label likeName2;
    private Label likeDate2;
    private ImageView likeImage3;
    private Label likeName3;
    private Label likeDate3;
    private ImageView likeImage4;
    private Label likeName4;
    private Label likeDate4;
    private ImageView likeImage5;
    private Label likeName5;
    private Label likeDate5;
    private ImageView likeImage6;
    private Label likeName6;
    private Label likeDate6;
    @FXML
    private Text aboutText;
    @FXML
    private ScrollPane photoScrollPane;
    @FXML
    private VBox photosVBox;
    private List<Label> likeNameLabels;
    private List<Label> likeDateLabels;
    private List<ImageView> likeImageViews;
    private GlobalViewController controller;
    List<Member> members;
    
    private int userId;
    @FXML
    private Button likeButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button dislikeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = GlobalViewController.getInstance();
        photosVBox.fillWidthProperty().bind(photoScrollPane.fitToWidthProperty());
        members = new ArrayList<>();
        likeNameLabels = new ArrayList<>();
        likeNameLabels.addAll(Arrays.asList(likeName1, likeName2, likeName3, likeName4, likeName5, likeName6));
        likeDateLabels = new ArrayList<>();
        likeDateLabels.addAll(Arrays.asList(likeDate1, likeDate2, likeDate3, likeDate4, likeDate5, likeDate6));
        likeImageViews = new ArrayList<>();
        likeImageViews.addAll(Arrays.asList(likeImage1, likeImage2, likeImage3, likeImage4, likeImage5, likeImage6));
    }
    
    private void checkForLike(){
        try {
            if(LikeService.getInstance().get(new Like(MySoulMate.MEMBER_ID, userId, null))!=null){
                likeButton.setVisible(false);
                dislikeButton.setVisible(true);
                messageButton.setVisible(true);
            }else{
                likeButton.setVisible(true);
                dislikeButton.setVisible(false);
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
    }
    
    private void populatePhotosPane(){
        photosVBox.getChildren().clear();
        try {
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(0, userId, null, null));
            
            for(Photo photo:photos){
                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.setAlignment(Pos.CENTER);
                ImageView imageView = new ImageView(MySoulMate.UPLOAD_URL+photo.getUrl());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(300);
                hBox.getChildren().add(imageView);
                photosVBox.getChildren().add(hBox);
            }
        } catch (SQLException ex) {
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
            genderLabel.setText(member.isGender()?"Homme":"Femme");
            bdLabel.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(member.getBirthDate()));
            heightLabel.setText(member.getHeight()+"");
            bodyTypeLabel.setText(member.getBodyType().name().substring(0, 1) + member.getBodyType().name().substring(1).toLowerCase());
            smokerLabel.setText(member.isSmoker()?"Oui":"Non");
            drinkerLabel.setText(member.isDrinker()?"Oui":"Non");
            religionLabel.setText(member.getReligion().name().substring(0, 1) + member.getReligion().name().substring(1).toLowerCase());
            childNumLabel.setText(member.getChildrenNumber()+"");
            aboutText.setText(member.getAbout());
            civilStatusLabel.setText(member.getMaritalStatus().name().substring(0, 1) + member.getMaritalStatus().name().substring(1).toLowerCase());
            createdAtLabel.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(member.getCreatedAt()));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), "Probleme de connéction à la base de donnée");
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
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(0, userId, null, null));
            String photoPath ="";
            if(photos.isEmpty()){
                photoPath = "/view/assets/icons/member.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photos.get(0).getUrl();
            }
            profileImage.setImage(new Image(photoPath));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, OthersProfileViewController.class.getName(), null);
        }
    }
    
    private void makeCoverPicture(){
        try {
            coverImage.fitWidthProperty().bind(coverContainer.widthProperty());
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(0, userId, null, null));
            String photoPath ="";
            if(photos.size()<=1){
                photoPath = "/view/assets/img/banner.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photos.get(1).getUrl();
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
    
}
