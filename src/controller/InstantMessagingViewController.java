/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.NetworkConnection;
import models.*;
import services.*;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class InstantMessagingViewController implements Initializable {

    MemberService m = MemberService.getInstance();
    Member member = new Member();
    Message msg ; 
   
    
    private int receiverId;
    javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    public void setReceiverId(int id) {
        this.receiverId = id;
    }

    @FXML
    private Label nom;
    @FXML
    private Label age;
    @FXML
    private Label status;
    @FXML
    private Button send;

    /**
     * Initializes the controller class.
     */
    int i = 0;

    boolean isServer = true;
    Label ntapi = new Label();

    NetworkConnection connection = isServer ? createServer() : createClient();

    private TextField inputmsg2 = new TextField();

    @FXML
    private ScrollPane x = new ScrollPane();
    @FXML
    private TextArea inputmsg = new TextArea();
    Label textField[] = new Label[150000];
    @FXML
    private AnchorPane msgAnchore;
    @FXML
    private Label typing;
    private VBox envoye;
    private VBox recu;
    private HBox wrap;

//    private Parent createContent() {
//        TextArea inputmsg = new TextArea();
//        inputmsg.setFont(Font.font(20));
//        inputmsg.setPrefHeight(46);
//        inputmsg.setEditable(false);
//
////        TextArea input = new TextArea();
//        inputmsg.setPromptText("type");
//
//        inputmsg.setOnKeyReleased(((event3) -> {
//            try {
//                String typing = "Stopped";
//                if (inputmsg.getText() != null) {
//                    connection.send((typing.toString()));
//
//                }
//
//            } catch (Exception e) {
//                textField[i].setText("Failed to send\n");
//            }
//
//        }));
//        inputmsg.setOnKeyTyped(((event2) -> {
//            try {
//                String typing = "Is typing";
//                if (inputmsg.getText() != null) {
//                    connection.send((typing.toString()));
//
//                }
//
//            } catch (Exception e) {
//                textField[i].setText("Failed to send\n");
//            }
//
//        }));
//
////        inputmsg.on((ActionEvent event) -> {
////});
//        return x;
//    }

//    private void setContent(String path, Pane container) {
//        try {
//            Pane newLoadedPane = FXMLLoader.load(getClass().getResource(path));
//            VBox.setVgrow(x, Priority.ALWAYS);
//            container.getChildren().clear();
//            container.getChildren().add(newLoadedPane);
//            newLoadedPane.prefWidthProperty().bind(container.widthProperty());
//        } catch (IOException ex) {
//            util.Logger.writeLog(ex, GlobalViewController.class.getCanonicalName(), null);
//        }
//    }
    private void clearContent(Pane container) {
        container.getChildren().clear();
    }

    public void init() throws Exception {

        connection.startConnection();

    }

//    public void start(Stage primaryStage) throws Exception {
//        setContent("Authentification.fxml", msgAnchore);
//
//        primaryStage.setTitle(isServer ? "Server" : "Badis");
//        primaryStage.show();
//    }
    public void stop() throws Exception {
        connection.closeConnection();
    }
    VBox content2 = new VBox();

    private Server createServer() {
        return new Server("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
                String t = "Is typing";
                String c = "Stopped";
                recu = new VBox();
                wrap = new HBox();
                if (data.equals(t)) {
                    typing.setText("Is typing");
                    typing.setStyle("       -fx-text-fill:  #e74c3c ;\n"
                            + "     -fx-background-color :  #fff ; \n"
                            + "     -fx-border-color : #e74c3c; \n"
                            + "     -fx-border-width : 0.5 ;"
                            + "-fx-font-size : 15 px ; "
                            + "-fx-border-radius : 30 30 30 30 ; "
                            + "-fx-background-radius : 30 30 30 30 ;");

                } else {
                    typing.setText("");
                    textField[i].setMaxWidth(350);
                    textField[i].setWrapText(true);
                    textField[i] = new Label();
                    textField[i].setText(" " + data.toString() + "\n");
                    textField[i].setTranslateX(20);
                    textField[i].setStyle("       -fx-text-fill:  #e74c3c ;\n"
                            + "     -fx-background-color :  #fff ; \n"
                            + "     -fx-border-color : #e74c3c; \n"
                            + "     -fx-border-width : 0.5 ;"
                            + "-fx-font-size : 15 px ; "
                            + "-fx-border-radius : 30 30 30 30 ; "
                            + "-fx-background-radius : 30 30 30 30 ;");
                    textField[i].setAlignment(Pos.CENTER);

                    recu.getChildren().add(textField[i]);
                    wrap.getChildren().add(recu);
                    content2.getChildren().add(wrap);
                    i = i + 1;
                    x.setContent(content2);
                }
            });
        });
    }

    private Client createClient() {
        return new Client("127.0.0.2", 55555, data -> {
            Platform.runLater(() -> {
                String t = "Is typing";
                String c = "Stopped";
                recu = new VBox();
                wrap = new HBox();

//
                if (data.equals(t)) {
                    typing.setText("Is typing");
                    typing.setStyle("       -fx-text-fill:  #e74c3c ;\n"
                            + "     -fx-background-color :  #fff ; \n"
                            + "     -fx-border-color : #e74c3c; \n"
                            + "     -fx-border-width : 0.5 ;"
                            + "-fx-font-size : 15 px ; "
                            + "-fx-border-radius : 30 30 30 30 ; "
                            + "-fx-background-radius : 30 30 30 30 ;");

                } else {
                    typing.setText("");
                    textField[i] = new Label();
                    textField[i].setMaxWidth(350);
                    textField[i].setWrapText(true);
                    textField[i].setText(" " + data.toString() + "\n");
                    textField[i].setTranslateX(20);
                    textField[i].setStyle("       -fx-text-fill:  #e74c3c ;\n"
                            + "     -fx-background-color :  #fff ; \n"
                            + "     -fx-border-color : #e74c3c; \n"
                            + "     -fx-border-width : 0.5 ;"
                            + "-fx-font-size : 15 px ; "
                            + "-fx-border-radius : 30 30 30 30 ; "
                            + "-fx-background-radius : 30 30 30 30 ;");
                    textField[i].setAlignment(Pos.CENTER);
                    recu.getChildren().add(textField[i]);
                    wrap.getChildren().add(recu);
                    content2.getChildren().add(wrap);
                    i = i + 1;
                    x.setContent(content2);
                }
            });
        });
    }
    

    public Parent getHisotrique() throws SQLException {
        
       

     msg = new Message();
    msg.setSenderId(2);
    msg.setReceiverId(1);
      List<Message> messages = MessageService.getInstance().getAll(msg);
            messages.forEach(e -> {
                if (msg.getSenderId() == 1 && msg.getReceiverId() ==2 ) {
                    textField[i] = new Label();

                    textField[i].setText(" " + msg.getContent() + " \n");
                    envoye = new VBox();
                    wrap = new HBox();
                    textField[i].setTranslateX(1200);
                    textField[i].setStyle("   -fx-background-color :   #e74c3c ; \n"
                            + "    -fx-border-color : white ; \n"
                            + "    -fx-border-width : 0.5 ;\n"
                            + "   -fx-text-fill : #fff ; \n"
                            + "   -fx-border-radius : 30 30 30 30 ;\n"
                            + "    -fx-background-radius : 30 30 30 30 ;"
                            + "-fx-font-size : 20px ; "
                            + "");
                    textField[i].setAlignment(Pos.CENTER_LEFT);
                    textField[i].setNodeOrientation(NodeOrientation.INHERIT);

                    textField[i].setTranslateX(primaryScreenBounds.getWidth() / 2);

                    envoye.getChildren().add(textField[i]);
                    wrap.getChildren().add(envoye);
                    content2.getChildren().add(wrap);
                    content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());

                    i = i + 1;
                    x.setContent(content2);
                    x.setVvalue(1.0d);

                } else if (msg.getSenderId() == 2 && msg.getReceiverId() == 1) {
                    textField[i] = new Label();

                    textField[i].setText(" " + msg.getContent() + " \n");
                    envoye = new VBox();
                    wrap = new HBox();
                    textField[i].setTranslateX(1200);
                    textField[i].setStyle("       -fx-text-fill:  #e74c3c ;\n"
                            + "     -fx-background-color :  #fff ; \n"
                            + "     -fx-border-color : #e74c3c; \n"
                            + "     -fx-border-width : 0.5 ;"
                            + "-fx-font-size : 15 px ; "
                            + "-fx-border-radius : 30 30 30 30 ; "
                            + "-fx-background-radius : 30 30 30 30 ;");
                    textField[i].setAlignment(Pos.CENTER_LEFT);
                    textField[i].setNodeOrientation(NodeOrientation.INHERIT);

                    textField[i].setTranslateX(primaryScreenBounds.getWidth() / 2);

                    envoye.getChildren().add(textField[i]);
                    wrap.getChildren().add(envoye);
                    content2.getChildren().add(wrap);
                    content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());

                    i = i + 1;
                    x.setContent(content2);
                    x.setVvalue(1.0d);

                }
            });
            return x ;
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            init();
            getHisotrique();

           

            /**
             *
             * @throws Exception
             */
        } catch (Exception ex) {
            Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private Parent goSend(ActionEvent event6) throws Exception {

        String message = isServer ? "Server: " : "Badis: ";

        message += inputmsg.getText();
        inputmsg.clear();

        textField[i] = new Label();
        textField[i].setTranslateX((primaryScreenBounds.getWidth() / 2));
        textField[i].setMaxWidth((primaryScreenBounds.getWidth() / 2) - 30);

        textField[i].setWrapText(true);
        textField[i].setText(" " + message + " \n");
        envoye = new VBox();
        wrap = new HBox();
        textField[i].setTranslateX(750);
        textField[i].setStyle("   -fx-background-color :   #e74c3c ; \n"
                + "    -fx-border-color : white ; \n"
                + "    -fx-border-width : 0.5 ;\n"
                + "   -fx-text-fill : #fff ; \n"
                + "   -fx-border-radius : 30 30 30 30 ;\n"
                + "    -fx-background-radius : 30 30 30 30 ;"
                + "-fx-font-size : 20px ; "
                + "");
        textField[i].setAlignment(Pos.CENTER_LEFT);
        textField[i].setNodeOrientation(NodeOrientation.INHERIT);

        textField[i].setTranslateX(primaryScreenBounds.getWidth() / 2);

        envoye.getChildren().add(textField[i]);
        wrap.getChildren().add(envoye);
        content2.getChildren().add(wrap);
        content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());

        i = i + 1;
        x.setContent(content2);

        try {
            msg = new Message();
            msg.setSenderId(2);
         
            msg.setReceiverId(1);
            msg.setContent(message);
            msg = MessageService.getInstance().create(msg);
            connection.send((message.toString()));

        } catch (Exception e) {
            textField[i].setText("Failed to send\n");
            System.out.println("failed to send");
        }
        x.setVvalue(1.0d);
        return x;

    }

    @FXML
    private void goReceive(MouseEvent event) {
    }

    private void isWriting(KeyEvent event) {

        String message = "Is typing";
        try {

            connection.send((message.toString()));

        } catch (Exception e) {
            textField[i].setText("Failed to send\n");
            System.out.println("failed to send");
        }

    }

    @FXML
    private void isWriting(InputMethodEvent event) {
    }

}
