/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import static controller.MySoulMate.mainStage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Member;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import services.MemberService;
import util.BCrypt;
import util.SendMail;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class AuthentificationController implements Initializable {

    int logged = 0;
    @FXML
    private TextField username;
    @FXML
    private JFXPasswordField pw;
    @FXML
    private Button button;
    @FXML
    private ImageView close_btn;
    @FXML
    private ImageView fb_btn;
    @FXML
    private Label recover;
    @FXML
    private Button buttonAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button.requestFocus();
    }

    @FXML
    private void goAuthentification(ActionEvent event) throws SQLException, IOException {

        MemberService memberService = MemberService.getInstance();
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Insert your Pseudo");
            alert.setHeaderText("Insert your Pseudo");
            alert.setContentText("Insert your Pseudo");

            alert.showAndWait();

        } else if (pw.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Insert your Password");
            alert.setHeaderText("Insert your Password");
            alert.setContentText("Insert your Password");

            alert.showAndWait();
        } else {

            try {

                Member m = new Member();

                m.setPseudo(username.getText());

                m = memberService.get(m);
                String myName = m.getPassword();
                char[] myNameChars = myName.toCharArray();
                myNameChars[2] = 'a';
                myName = String.valueOf(myNameChars);

                System.out.println("check : " + BCrypt.checkpw(pw.getText(), myName));

                if (m != null) {

                    if (m.getPseudo().equals(username.getText()) && BCrypt.checkpw(pw.getText(), myName) == true) {
                        if (m.getLocked() == 0) {
                            m.setConnected(true);
                            memberService.update(m);
                            MySoulMate.MEMBER_ID = m.getId();
			GlobalViewController.online = MemberService.getInstance().get(new Member(m.getId()));
                            MySoulMate.getInstance().ChangeToHomeScene();
                        } else if (m.getLocked() == 1) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Blocked");
                            alert.setHeaderText("You are blocked ! ");
                            alert.setContentText(" You are blocked !");

                            alert.showAndWait();
                        } else if (m.getLocked() == 2) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Account deactivated");
                            alert.setHeaderText("Account deactivated");
                            alert.setContentText(" Account deactivated");

                            alert.showAndWait();

                        }

                    } else {
                        Alert alert = new Alert(AlertType.ERROR);

                        alert.setTitle("Wrong Password ! ");
                        alert.setHeaderText("Wrong Password ! ");
                        alert.setContentText("Wrong Password ! ");

                        alert.showAndWait();

                    }

                } else {

                    Alert alert = new Alert(AlertType.ERROR);

                    alert.setTitle("Error  ");
                    alert.setHeaderText("This account doesn't exist ! ");
                    alert.setContentText("This account doesn't exist ! ");

                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            Stage stagex = (Stage) username.getScene().getWindow();

        }
    }

    @FXML
    private void goClose(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void goFb(MouseEvent event) throws IOException {

        String appSecret = "15378d7426361fe464f5af2e08f780e3";
        String domain = "http://localhost";
        String appId = "212394559315715";
        String auth = "http://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_pages,publish_actions,read_insights,user_friends,read_page_mailboxes,rsvp_event";
        System.setProperty("Webdriver.chrome.driver", "C:/Users/badis/Desktop/3/Mobile/chromedriver.exe");
        WebDriver dr1 = new ChromeDriver();
        String accessToken = null;
        dr1.get(auth);
        while (true) {
            if (!dr1.getCurrentUrl().contains("www.facebook.com")) {

                String url = dr1.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

                FacebookClient fbClient = new DefaultFacebookClient(accessToken, appSecret, com.restfb.Version.UNVERSIONED);
                User user = fbClient.fetchObject("me", User.class);
                Member m = new Member();
                MemberService memberService = MemberService.getInstance();
                m.setPseudo(user.getName());

                try {
                    m = memberService.get(m);
                    if (m != null) {
                        if (m.getLocked() == 0) {
                            m.setConnected(true);
                            memberService.update(m);
                            MySoulMate.MEMBER_ID = m.getId();
                            MySoulMate.getInstance().ChangeToHomeScene();
                        } else if (m.getLocked() == 1) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("You are blocked !");
                            alert.setHeaderText("You are blocked !");
                            alert.setContentText("You are blocked !");

                            alert.showAndWait();
                        } else if (m.getLocked() == 2) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Account deactivated");
                            alert.setHeaderText("Account deactivated");
                            alert.setContentText("Account deactivated");

                            alert.showAndWait();

                        }
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error ");
                        alert.setHeaderText("This account doesn't exist ! ");
                        alert.setContentText("This account doesn't exist ! ");

                        alert.showAndWait();
                    }

                } catch (SQLException e) {

                    System.out.println(e);
                }

                if (dr1.getCurrentUrl().contains("localhost")) {
                    return;
                }

                dr1.quit();
                dr1 = null;
            }
        }
    }

    @FXML
    private void goRecover1(MouseEvent event) throws IOException {
        Parent globalPane = FXMLLoader.load(getClass().getResource("/view/RecoveryView.fxml"));
        Scene scene = new Scene(globalPane);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void goAdmin(ActionEvent event) throws IOException {

        Parent globalPane = FXMLLoader.load(getClass().getResource("/view/AuthAdminView.fxml"));
        Scene scene = new Scene(globalPane);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void goInscri(MouseEvent event) throws IOException {
        Parent globalPane = FXMLLoader.load(getClass().getResource("/view/InscriptionContainerView.fxml"));
        Scene scene = new Scene(globalPane);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
