/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXColorPicker;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.Paint;
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
    Message msg;
    boolean isSeen;

    private int receiverId;
    javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    @FXML
    private ScrollPane convs = new ScrollPane();
    @FXML
    private Label nom;
    @FXML
    private Label age;
    @FXML
    private ComboBox<String> box;
    @FXML
    private JFXColorPicker colorpicker;

    public void setReceiverId(int id) {
        this.receiverId = id;
    }

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
    Button conversation[] = new Button[150];
    @FXML
    private AnchorPane msgAnchore = new AnchorPane();
    @FXML
    private Label typing;
    @FXML
    private VBox envoye;
    private VBox cons;
    @FXML
    private VBox recu;
    @FXML
    private HBox wrap;

    private void clearContent(Pane container) {
        container.getChildren().clear();
    }

    public void init() throws Exception {

        connection.startConnection();

    }

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

                if(data == null) return;
                if ((data.toString()).equals(t)) {
                    typing.setText("Is typing");
                    typing.getStyleClass().add("typing");

                } else {
                    typing.setText("");
                    textField[i] = new Label();
                    textField[i].setMaxWidth(350);
                    textField[i].setWrapText(true);
                    textField[i].setText(" " + data.toString() + "\n");
                    textField[i].setTranslateX(20);
                    textField[i].getStyleClass().add("typing");
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

                if(data == null) return;
                if ((data.toString()).equals(t)) {
                    typing.setText("Is typing");
                    typing.getStyleClass().add("typing");

                } else {
                    typing.setText("");
                    textField[i] = new Label();
                    textField[i].setMaxWidth(350);
                    textField[i].setWrapText(true);
                    textField[i].setText(" " + data.toString() + "\n");
                    textField[i].setTranslateX(20);
                    textField[i].getStyleClass().add("typing");
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
        msg.setReceiverId(3);

        List<Message> messages = MessageService.getInstance().getAll(msg);
        messages.forEach(e -> {
            if (e.getSenderId() == 2 && e.getReceiverId() == 3) {

                textField[i] = new Label();
                textField[i].nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                textField[i].setText(" " + e.getContent() + " \n");
                envoye = new VBox();
                wrap = new HBox();
                textField[i].setTranslateX(350);
                textField[i].getStyleClass().add("recu");
                textField[i].setAlignment(Pos.CENTER);

                envoye.getChildren().add(textField[i]);

                wrap.getChildren().add(envoye);
                content2.getChildren().add(wrap);
                content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());

                i = i + 1;
                x.setContent(content2);
                x.setVvalue(1.0d);

            } else if (e.getSenderId() == 3 && e.getReceiverId() == 2) {
                textField[i] = new Label();

                textField[i].setText(" " + e.getContent() + " \n");
                recu = new VBox();
                wrap = new HBox();
                textField[i].setTranslateX(20);
                textField[i].getStyleClass().add("typing");
                textField[i].setAlignment(Pos.CENTER);
                textField[i].setNodeOrientation(NodeOrientation.INHERIT);

                recu.getChildren().add(textField[i]);
                wrap.getChildren().add(recu);
                content2.getChildren().add(wrap);
                content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());

                i = i + 1;
                x.setContent(content2);
                x.setVvalue(1.0d);

            }
            try {
                e.setSeen(true);
                e.setSeenDate(new Timestamp(new Date().getTime()));
                MessageService.getInstance().update(e);
            } catch (SQLException ex) {
                Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        x.setVvalue(1.0d);
        return x;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            init();
            getHisotrique();
            goConversations();
            box.getSelectionModel().select(1);
            box.getItems().add("Emojis");
            box.getItems().add("☺");
            box.getItems().add("☹");
            box.getItems().add("💋");
            box.getItems().add("❤");
            box.getItems().add("♫");
            box.getItems().add("☺");
            box.setStyle("-fx-font-size : 15 px ; -fx-text-fill : #fff ;");
            inputmsg.setStyle("-fx-font-size : 20 px ;");
            envoye.setAlignment(Pos.CENTER);
            recu.setAlignment(Pos.CENTER);
            envoye.setNodeOrientation(NodeOrientation.INHERIT);
//            recu.setMaxWidth(wrap.getMaxWidth() / 2);
//            envoye.setMaxWidth(wrap.getMaxWidth() / 2);

            /**
             *
             * @throws Exception
             */
        } catch (Exception ex) {
            Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Parent goConversations() throws SQLException {
        ConversationService cs = ConversationService.getInstance();
        MemberService ms = MemberService.getInstance();

        try {
            Conversation c = new Conversation();
            cons = new VBox();
            c.setPerson1Id(2);
            c.setPerson2Id(3);
            Member m = new Member();
            m.setId(c.getPerson1Id());
           m= ms.get(m);
            List<Conversation> convers = cs.getAll(c);
            convers.forEach(e -> {
                Member m2 = new Member();
                m2.setId(c.getPerson2Id());
                try {
                   m2= ms.get(m2);
                } catch (SQLException ex) {
                    Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
                }

                conversation[i] = new Button();
                isSeen = c.isSeen();
                Timestamp timestamp = e.getSeenDate();

                String x = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(e.getSeenDate());
                String seen = isSeen ? e.getSeenDate().toString() : "no";
                conversation[i].setText(" " + e.getLabel() + "\n " + m2.getPseudo() + " \n Seen :" + e.getSeenDate().toString());
                conversation[i].getStyleClass().add("recu");
//                conversation[i].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("InstantMessagingView.fxml"));
//                        Parent root2;
//                        try {
//                            root2 = (Parent) fxmlLoader2.load();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(root2));
//                            stage.show();
//                        } catch (IOException ex) {
//                            Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                    }
//                });
                conversation[i].setAlignment(Pos.CENTER);
                cons.setMinWidth(conversation[i].getWidth());
                cons.getChildren().add(conversation[i]);
                convs.setContent(cons);

                i = i + 1;

            });

        } catch (Exception e) {
              Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, e);
        }

        cons.setMinWidth(300);
        convs.setMinWidth(cons.getMinWidth());
        return convs;
    }

    @FXML
    private Parent goSend(ActionEvent event6) throws SQLException, IOException {

        String message ="";

        message += inputmsg.getText();
        inputmsg.clear();

        textField[i] = new Label();

        textField[i].setMaxWidth((primaryScreenBounds.getWidth() / 2) - 30);

        textField[i].setWrapText(true);
        textField[i].setText(" " + message + " \n");
        envoye = new VBox();

        wrap = new HBox();
        textField[i].setTranslateX(350);
        textField[i].getStyleClass().add("recu");
        textField[i].setAlignment(Pos.CENTER_LEFT);

        envoye.getChildren().add(textField[i]);
        wrap.getChildren().add(envoye);
        content2.getChildren().add(wrap);
        content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());

        i = i + 1;
        x.setContent(content2);
        ConversationService cs = ConversationService.getInstance();
        MessageService ms = MessageService.getInstance();
        msg = new Message();
        msg.setSenderId(MySoulMate.MEMBER_ID);

        msg.setReceiverId(3);
        msg.setContent(message);
        try {
            connection.send((message.toString()));
        } catch (Exception e) {
            System.out.println("instant msg exception" + e);
        }

        try {

            msg = ms.create(msg);

        } catch (Exception e) {

            System.out.println(e + " send msg exception");
        }

        Conversation c = new Conversation();
        System.out.println("hnee conv");
        c.setPerson1Id(msg.getSenderId());
        System.out.println("p1 id " + msg.getSenderId());
        c.setPerson1Id(msg.getReceiverId());
        System.out.println("p2 id" + msg.getReceiverId());

        try {

            if ( (cs.get(c)) != null) {

                c.setSeen(false);
                c.setSeenDate(null);
              cs.update(c);
                System.out.println("update" + c);

            } else {

                c.setPerson1Id(msg.getSenderId());
                c.setPerson2Id(msg.getReceiverId());
                c.setSeen(false);
                c.setLabel("New conversation");
                c.setSeenDate(null);
                c = cs.create(c);
                System.out.println("create" + c);

            }
        } catch (Exception e) {
            System.out.println("conv exception" + e);
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

            System.out.println("failed to send");
        }

    }

    @FXML
    private void isWriting(InputMethodEvent event) {
    }

    @FXML
    private void Emoji(ActionEvent event) {
        box.show();
        if (box.getSelectionModel().getSelectedItem().toString() != "Emojis") {
            String emojy = box.getSelectionModel().getSelectedItem().toString();
            inputmsg.setText(inputmsg.getText() + " " + emojy);
            box.setValue("Emojis");
            box.setStyle("-fx-font-size : 15 px ; -fx-text-fill : #fff ;");
        }
    }

    @FXML
    private void goColor(ActionEvent event) {
//        Color selectedColor = colorpicker.getValue();
//       
//          msgAnchore.setBackground(new  Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()),CornerRadii.EMPTY,Insets.EMPTY)));
//          button.setBackground(new Background(new BackgroundFill(Color.ORANGE,
//                new CornerRadii(2), new Insets(4))));}
//        msgAnchore.getChildren().addAll();
//        return msgAnchore;
    }

}
