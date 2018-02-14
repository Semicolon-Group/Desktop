/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Member;
import services.MemberService;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField pw;
    @FXML
    private Button button;
    @FXML
    private ImageView close_btn;
    @FXML
    private ImageView fb_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goAuthentification(ActionEvent event) throws SQLException, IOException {
        Member m = new Member();
        MemberService memberService = MemberService.getInstance();
        m.setPseudo(username.getText());
        m.setPassword(pw.getText());

        try {
            m = memberService.get(m);
            
                System.out.println(m.getId() + " " + m.getPseudo() + " " + m.getFirstname() + " " + m.getLastname() + " " + m.getEmail() + " " + m.getPassword() + " " + m.getBirthDate() + " " + m.isGender() + " " + m.getHeight() + " " + m.getHeight() + " " + m.getBodyType() + " " + m.getChildrenNumber() + " " + m.getReligion() + " " + m.getReligionImportance() + " " + m.isSmoker() + " "
                        + m.isDrinker() + " " + m.getMaxAge() + " " + m.getMinAge() + " " + m.getProximity() + " " + m.getLastLogin() + " " + m.getLocked() + " " + m.getIp() + " " + m.getPort() + " " + m.getPreferedRelations() + " " + m.getPreferedStatuses());

//             FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("a.fxml"));
//            
//                Parent root2 = (Parent) fxmlLoader2.load();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root2));
//                stage.show();
                button.getScene().setRoot(FXMLLoader.load(getClass().getResource("a.fxml")));
                 
        }
        catch(SQLException e) {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Empty username");
            alert.setHeaderText("Username ");
            alert.setContentText("No username was inserted");

            alert.showAndWait();
        }

//        if (username.getText().isEmpty()) {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Empty username");
//            alert.setHeaderText("Username ");
//            alert.setContentText("No username was inserted");
//
//            alert.showAndWait();
//
//        } else if (pw.getText().isEmpty()) {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Empty password");
//            alert.setHeaderText(" Password ");
//            alert.setContentText("No Password was inserted");
//
//            alert.showAndWait();
//        } else {
//            try {
//                Member m = new Member();
//                MemberService memberService = MemberService.getInstance();
//                m.setPseudo(username.getText());
//                m.setPassword(pw.getText());
////                m = memberService.get(m);
//
//                if (memberService.get(m) == null) {
//                    Alert alert = new Alert(AlertType.ERROR);
//
//                    alert.setTitle("No one !");
//                    alert.setHeaderText(" No one ! ");
//                    alert.setContentText("No one !");
//
//                    alert.showAndWait();
//                  
//                }else{
//                FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("a.fxml"));
//                Parent root2 = (Parent) fxmlLoader2.load();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root2));
//                stage.show();
//                    
//                }
//          
//                System.out.println(m.getId() + " " + m.getPseudo() + " " + m.getFirstname() + " " + m.getLastname() + " " + m.getEmail() + " " + m.getPassword() + " " + m.getBirthDate() + " " + m.isGender() + " " + m.getHeight() + " " + m.getHeight() + " " + m.getBodyType() + " " + m.getChildrenNumber() + " " + m.getReligion() + " " + m.getReligionImportance() + " " + m.isSmoker() + " "
//                        + m.isDrinker() + " " + m.getMaxAge() + " " + m.getMinAge() + " " + m.getProximity() + " " + m.getLastLogin() + " " + m.getLocked() + " " + m.getIp() + " " + m.getPort() + " " + m.getPreferedRelations() + " " + m.getPreferedStatuses());
//            } catch (Exception e) {
//            }
//             Stage stagex = (Stage) username.getScene().getWindow();
//        
//        }
    }

    @FXML
    private void goClose(MouseEvent event) {
    }

    @FXML
    private void goFb(MouseEvent event) {
        
        String domain ="http://www.badis.com/";
        String appId ="212394559315715";
        String auth = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,user_events,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";
    }
}
