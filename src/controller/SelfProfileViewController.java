/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import models.Like;
import models.Member;
import models.Photo;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import services.LikeService;
import services.MemberService;
import services.PhotoService;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class SelfProfileViewController implements Initializable {

    @FXML
    private AnchorPane coverContainer;
    @FXML
    private ImageView coverImage;
    @FXML
    private ImageView profileImage;
    @FXML
    private AnchorPane profileImgPane;
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
    private Label childNumLabel;
    @FXML
    private Text aboutText;
    
    private GlobalViewController controller;
    @FXML
    private Label civilStatusLabel;
    @FXML
    private Label createdAtLabel;
    private VBox leftLikesPane;
    List<Member> members;
    @FXML
    private ImageView likeImage1;
    @FXML
    private Label likeName1;
    @FXML
    private Label likeDate1;
    @FXML
    private ImageView likeImage2;
    @FXML
    private Label likeName2;
    @FXML
    private Label likeDate2;
    @FXML
    private ImageView likeImage3;
    @FXML
    private Label likeName3;
    @FXML
    private Label likeDate3;
    @FXML
    private ImageView likeImage4;
    @FXML
    private Label likeName4;
    @FXML
    private Label likeDate4;
    @FXML
    private ImageView likeImage5;
    @FXML
    private Label likeName5;
    @FXML
    private Label likeDate5;
    @FXML
    private ImageView likeImage6;
    @FXML
    private Label likeName6;
    @FXML
    private Label likeDate6;
    
    private List<Label> likeNameLabels;
    private List<Label> likeDateLabels;
    private List<ImageView> likeImageViews;
    @FXML
    private VBox photosVBox;
    @FXML
    private ScrollPane photoScrollPane;
    @FXML
    private AnchorPane mainAnchorPane;

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
        makeCoverPicture();
        makeProfilePicture();
        populateFields();
        populatePhotosPane();
    }
    
    private void populatePhotosPane(){
        photosVBox.getChildren().clear();
        try {
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(0, MySoulMate.MEMBER_ID, null, null));
            
            for(Photo photo:photos){
                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.setAlignment(Pos.CENTER);
                Button button = new Button("Supprimer");
                button.setOnAction(e-> supprimerPhoto(e));
                button.getStyleClass().add("regular_button");
                button.setId(photo.getId()+"");
                ImageView imageView = new ImageView(MySoulMate.UPLOAD_URL+photo.getUrl());
                imageView.setCursor(Cursor.HAND);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showImage(e));
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(300);
                hBox.getChildren().add(imageView);
                hBox.getChildren().add(button);
                photosVBox.getChildren().add(hBox);
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void showImage(MouseEvent event){
        try {
            Image image = ((ImageView)event.getTarget()).getImage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ImageView.fxml"));
            Pane newLoadedPane =  loader.load();
            ((ImageViewController)loader.getController()).setImage(image);
            ((ImageViewController)loader.getController()).setParentAnchorPane(mainAnchorPane);
            mainAnchorPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void supprimerPhoto(ActionEvent event){
        try {
            PhotoService.getInstance().delete(new Photo(Integer.parseInt(((Button)event.getTarget()).getId())));
            populatePhotosPane();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void populateFields(){
        try {
            MemberService memberService = MemberService.getInstance();
            Member member = memberService.get(new Member(MySoulMate.MEMBER_ID));
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
            
            makeMemberLikePane();
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), "Probleme de connéction à la base de donnée");
        }catch (Exception ex){
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void makeMemberLikePane(){
        try {
            List<Like> likes = LikeService.getInstance().getAll(new Like(MySoulMate.MEMBER_ID, 0, null)).stream().limit(6)
                    .collect(Collectors.toList());
            int i = 0;
            for(Like like: likes){
                Member member = MemberService.getInstance().get(new Member(like.getReceiverId()));
                List<Photo> photos = PhotoService.getInstance().getAll(new Photo(0, member.getId(), null, null));
                String photoPath="";
                if(photos.isEmpty()){
                    photoPath = "/view/assets/icons/member.jpg";
                }else{
                    photoPath = MySoulMate.UPLOAD_URL+photos.get(0).getUrl();
                }
                likeImageViews.get(i).setImage(new Image(photoPath));
                likeImageViews.get(i).setId(member.getId()+"");
                likeNameLabels.get(i).setText(member.getFirstname()+" "+member.getLastname());
                likeDateLabels.get(i).setText("Depuis: "+(new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(like.getDate())));
                i++;
            }
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void makeProfilePicture(){
        try {
            Circle imageClip = new Circle(profileImage.getX()+(profileImage.getFitWidth()/2), profileImage.getY()+(profileImage.getFitHeight()/2), 135);
            profileImage.setClip(imageClip);
            Circle contenairClip = new Circle(profileImgPane.getLayoutX()+(profileImgPane.getPrefWidth()/2), profileImgPane.getLayoutY()+(profileImgPane.getPrefHeight()/2), 145);
            profileImgPane.setClip(contenairClip);
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(0, MySoulMate.MEMBER_ID, null, null));
            String photoPath ="";
            if(photos.isEmpty()){
                photoPath = "/view/assets/icons/member.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photos.get(0).getUrl();
            }
            profileImage.setImage(new Image(photoPath));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void makeCoverPicture(){
        try {
            coverImage.fitWidthProperty().bind(coverContainer.widthProperty());
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(0, MySoulMate.MEMBER_ID, null, null));
            String photoPath ="";
            if(photos.size()<=1){
                photoPath = "/view/assets/img/banner.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photos.get(1).getUrl();
            }
            coverImage.setImage(new Image(photoPath));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }

    @FXML
    private void showFileChooser(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choisir une photo");
        File photo = chooser.showOpenDialog(MySoulMate.mainStage);
        System.out.println(photo.getAbsolutePath());
//        FTPClient con = null;
//
//            try
//            {
//                con = new FTPClient();
//                String server = "ftp.icm.edu.pl";
//                int port = 21;
//                String user = "anonymous";
//                String pass = "me@nowhere.com";
//                con.connect(server,port);                 // Its dummy Address
//
//                if (con.login(user, pass))
//                {
//                    con.enterLocalPassiveMode();                   // Very Important
//
//                    con.setFileType(FTP.BINARY_FILE_TYPE);        //  Very Important
//                    String data = photo.getAbsolutePath();
//
//                    FileInputStream in = new FileInputStream(new File(data));
//                    boolean result = con.storeFile("/mysoulmateuploads/yoo.png", in);
//                    in.close();
//                    if (result) System.out.println("Upload succeeded");
//                    con.logout();
//                    con.disconnect();
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }   
    }

    @FXML
    private void toOtherProfile(MouseEvent event) {
        int memberId = Integer.parseInt(((HBox)event.getSource()).getChildren().get(0).getId());
        FXMLLoader loader = controller.setMainContent("/view/OthersProfileView.fxml");
        ((OthersProfileViewController)loader.getController()).setUserId(memberId);
    }
}
