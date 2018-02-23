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
import javafx.beans.value.ObservableValue;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Answer;
import models.Enumerations;
import models.Enumerations.PhotoType;
import models.Like;
import models.Member;
import models.Photo;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import services.AnswerService;
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
    List<Member> members;
    @FXML
    private VBox photosVBox;
    @FXML
    private ScrollPane photoScrollPane;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TextArea aboutTextarea;
    @FXML
    private VBox answersVBox;
    
    private List<Answer> answers;
    @FXML
    private VBox likesVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = GlobalViewController.getInstance();
        photosVBox.fillWidthProperty().bind(photoScrollPane.fitToWidthProperty());
        aboutTextarea.focusedProperty().addListener((o, oldValue, newValue) -> updateAbout(o, oldValue, newValue));
        members = new ArrayList<>();
        makeCoverPicture();
        makeProfilePicture();
        populateFields();
        populatePhotosPane();
        makeAnswersPane();
    }
    
    public List<Answer> getAnswers(){
        return answers;
    }
    
    public void makeAnswersPane(){
        try {
            answersVBox.getChildren().clear();
            answers = AnswerService.getInstance().getAll(new Answer(0, null, null, MySoulMate.MEMBER_ID));
            for(Answer answer: answers){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AnswerView.fxml"));
                AnchorPane pane = loader.load();
                ((AnswerViewController)loader.getController()).setAnswer(answer);
                answersVBox.getChildren().add(pane);
            }
            AnchorPane buttomPane = new AnchorPane();
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(new ImageView(getClass().getResource("/view/assets/icons/add.png").toExternalForm()));
            buttomPane.getChildren().add(hBox);
            AnchorPane.setBottomAnchor(hBox, 0.0);
            AnchorPane.setLeftAnchor(hBox, 0.0);
            AnchorPane.setRightAnchor(hBox, 0.0);
            AnchorPane.setTopAnchor(hBox, 0.0);
            buttomPane.setPrefHeight(80);
            buttomPane.getStyleClass().add("add_answer");
            buttomPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showAddAnswerDialog(e));
            answersVBox.getChildren().add(buttomPane);
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void showAddAnswerDialog(MouseEvent e){
        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(MySoulMate.mainStage);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AnswerAddView.fxml"));
            Pane content = loader.load();
            ((AnswerAddViewController)loader.getController()).setParams(MySoulMate.MEMBER_ID, this);
            Scene dialogScene = new Scene(content, 752, 400);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(SelfProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populatePhotosPane(){
        photosVBox.getChildren().clear();
        try {
            List<Photo> photos = PhotoService.getInstance().getAll(new Photo(MySoulMate.MEMBER_ID));
            
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
    
    private void makeProfilePicture(){
        try {
            Circle imageClip = new Circle(profileImage.getX()+(profileImage.getFitWidth()/2), profileImage.getY()+(profileImage.getFitHeight()/2), 135);
            profileImage.setClip(imageClip);
            Circle contenairClip = new Circle(profileImgPane.getLayoutX()+(profileImgPane.getPrefWidth()/2), profileImgPane.getLayoutY()+(profileImgPane.getPrefHeight()/2), 145);
            profileImgPane.setClip(contenairClip);
            Photo photo = PhotoService.getInstance().get(new Photo(0, MySoulMate.MEMBER_ID, null, null, PhotoType.PROFILE));
            String photoPath ="";
            if(photo == null){
                photoPath = "/view/assets/icons/member.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photo.getUrl();
            }
            System.out.println(photoPath);
            profileImage.setImage(new Image(photoPath));
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    private void makeCoverPicture(){
        try {
            coverImage.fitWidthProperty().bind(coverContainer.widthProperty());
            Photo photo = PhotoService.getInstance().get(new Photo(0, MySoulMate.MEMBER_ID, null, null, PhotoType.COVER));
            String photoPath ="";
            if(photo == null){
                photoPath = "/view/assets/img/banner.jpg";
            }else{
                photoPath = MySoulMate.UPLOAD_URL+photo.getUrl();
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

    private void toOtherProfile(MouseEvent event) {
        int memberId = Integer.parseInt(((HBox)event.getSource()).getChildren().get(0).getId());
        FXMLLoader loader = controller.setMainContent("/view/OthersProfileView.fxml");
        ((OthersProfileViewController)loader.getController()).setUserId(memberId);
    }

    @FXML
    private void toEditView(ActionEvent event) {
        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(MySoulMate.mainStage);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
            Pane content = loader.load();
            ((EditProfileViewController)loader.getController()).setMember(MemberService.getInstance().get(new Member(MySoulMate.MEMBER_ID)));
            ((EditProfileViewController)loader.getController()).setController(this);
            ((EditProfileViewController)loader.getController()).setDialog(dialog);
            Scene dialogScene = new Scene(content, 1200, 775);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (IOException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, SelfProfileViewController.class.getName(), null);
        }
    }
    
    public void updateMemberInfo(){
        populateFields();
    }

    @FXML
    private void editAbout(MouseEvent event) {
        aboutTextarea.setText(aboutText.getText());
        aboutText.setVisible(false);
        aboutTextarea.setVisible(true);
        aboutTextarea.requestFocus();
    }
    
    private void updateAbout(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue){
        if(!newPropertyValue){
            try {
                Member m = MemberService.getInstance().get(new Member(MySoulMate.MEMBER_ID));
                m.setAbout(aboutTextarea.getText());
                MemberService.getInstance().update(m);
                aboutTextarea.setVisible(false);
                aboutText.setVisible(true);
                populateFields();
            } catch (SQLException ex) {
                Logger.getLogger(SelfProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
