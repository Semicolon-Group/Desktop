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
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import static controller.MainAchref.container3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.Email;
import models.Member;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class InsViewController implements Initializable {

    @FXML
    private JFXTextField Firstname;
    @FXML
    private JFXTextField Last_name;
    @FXML
    private JFXTextField pseudo;
    
    @FXML
    private JFXPasswordField Password;
    @FXML
    private JFXPasswordField Rpassword;
    @FXML
    private DatePicker birth_date;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Label labelfirst;
    @FXML
    private Label labelname;
    @FXML
    private Label labelps;
    @FXML
    private Label labelemail;
    @FXML
    private Label labelpass;
    @FXML
    private Label labelrepass;
    @FXML
    private Label labeldate;
    @FXML
    private Button btnR;

    public static Member m;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextField emailTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RotateTransition rt = new RotateTransition(Duration.seconds(120),image);
        rt.setByAngle(9*360);
        rt.play();

    }

    @FXML
    public void Continuer(ActionEvent event) {
        boolean valid = true;
        if (Firstname.getText().equals("")) {
            labelfirst.setText("Field is empty !");
            labelfirst.setVisible(true);
            valid = false;
        } else {
            labelfirst.setText("");
        }

        if (Last_name.getText().equals("")) {
            labelname.setText("Field is empty !");
            labelname.setVisible(true);
            valid = false;
        } else {
            labelname.setText("");
        }

        if (pseudo.getText().equals("")) {
            labelps.setText("Field is empty !");
            labelps.setVisible(true);
            valid = false;
        } else {
            labelps.setText("");
        }
        if (emailTextField.getText().equals("")) {
            labelemail.setText("Field is empty !");
            labelemail.setVisible(true);
            valid = false;
        }

        if (!emailTextField.getText().contains("@") && !emailTextField.getText().contains(".")) {
            labelemail.setText("E-mail is not valid !");
            labelemail.setVisible(true);
            valid = false;
        } else {
            labelemail.setText("");
        }

        if (Password.getText().equals("")) {
            labelpass.setText("Field is empty !");
            labelpass.setVisible(true);
            valid = false;
        } else {
            labelpass.setText("");
        }

        if (Rpassword.getText().equals("")) {
            labelrepass.setText("Field is empty !");
            labelrepass.setVisible(true);
            valid = false;
        }

        if (!Password.getText().equals(Rpassword.getText())) {
            labelrepass.setText("Password doesn't match !");
            labelrepass.setVisible(true);
            valid = false;
        } else {
            labelrepass.setText("Password match !");
            labelrepass.setTextFill(Color.web("GREEN"));;
            labelrepass.setVisible(true);
        }

        if (birth_date.getValue() == null) {
            labeldate.setText("Field is empty !");
            labeldate.setVisible(true);
            valid = false;
        }
        if(!valid) return;
        
        m = new Member();
        m.setFirstname(Firstname.getText());
        m.setLastname(Last_name.getText());
        m.setPseudo(pseudo.getText());
        m.setEmail(emailTextField.getText());
        m.setPassword(Password.getText());
        m.setGender(male.isSelected());
        m.setBirthDate(java.sql.Date.valueOf(birth_date.getValue()));
        


        FXMLLoader loader = container3.switchView("InscriptionDetailsView");
        System.out.println(loader);
        ((InscriptionDetailsViewController)loader.getController()).setMember(m);
        
         
            
    }

    @FXML
    private void Reset(ActionEvent event) {

        Firstname.clear();
        Last_name.clear();
        pseudo.clear();
        emailTextField.clear();
        Password.clear();
        Rpassword.clear();
        

    }

    @FXML
    private void fbAuth(ActionEvent event) {
         
        String appId = "160006897984731";
        String appSecretKey = "490b349f7387626f63ea4b4e2b3d2c2f";
        String domain = "http://whitedisplay.com/";
        String code="";
        String userAccessToken="";
        
        //permission lel email
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        scopeBuilder.addPermission(FacebookPermissions.USER_LOCATION);
        scopeBuilder.addPermission(FacebookPermissions.USER_BIRTHDAY);
        

        //client jdid w nvl boite de dialg
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_6);
        String loginDialogUrlString = client.getLoginDialogUrl(appId, domain, scopeBuilder);

        //chome driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(loginDialogUrlString);

        /*while true bch tab9a tet3awed dima psk sans l while yodkhel lel if une seule fois
        w it ll get the currentUrl only once*/
        while (true) {
            //ken redirect w c bon 
            //w driver.title mch .getCurrentUrl psk yrajja3 fi valeur ghalta
            if (driver.getTitle().contains("White screen page")) {
                String url = driver.getCurrentUrl();

                //puisque jed om el accessToken mafamech rihtou w ena nest7a9ou donc bch ngahfou mel url
                //w puisque l url mefhch ken l tokes akahw donc bch n'split el url win yabda si l code
                if (url.contains("code=") && "".equals(code) ) {
                    String[] parts = url.split("code=");
                    String part1 = parts[0];
                    String codeToken = parts[1];
                    code = codeToken;
                    System.out.println(codeToken);

                     
                      try {
                        //khorm mtaa decryptage
                         userAccessToken =  InsViewController.call_me(appId, domain, appSecretKey, codeToken);
                       
                         FacebookClient fbclient = new DefaultFacebookClient(userAccessToken);
                         User user = fbclient.fetchObject("me",User.class);
                         System.out.print("User Name = "+ user.getName());
                         System.out.println("User birthday "+user.getBirthday());
                         System.out.println("User email "+user.getEmail());
                         System.out.println("User location "+user.getLocation());
                         System.out.println("User id "+user.getId());

                         
                         
                         Firstname.setText(user.getFirstName());
                         Last_name.setText(user.getLastName());
                         driver.quit();
                      } catch (Exception e) {
                        e.printStackTrace();
                    }   
                }
            }
        }
    }
    //decryptage taa l token eli sregtou ena 
    //w puisque l output bel json donc voila
    public static String call_me(String appId, String redirectUrl, String appSecret, String code) throws Exception {
        String url = "https://graph.facebook.com/v2.12/oauth/access_token?"+"client_id=" + appId+"&redirect_uri=" + redirectUrl+"&client_secret=" + appSecret+"&code=" + code;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print in String
        //System.out.println(response.toString());

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println(myResponse.getString("access_token"));
        
        return(myResponse.getString("access_token"));
        //System.out.println("result after Reading JSON Response");
        //System.out.println("origin- "+myResponse.getString("origin"));

    }

}