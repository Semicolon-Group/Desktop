/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXColorPicker;
import static controller.GlobalViewController.online;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.shape.Circle;
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
    private ScrollPane convs = new ScrollPane();
    @FXML
    private Label nom;
    private TextField age;
    @FXML
    private ComboBox<String> box;
    @FXML
    private ImageView profileImage;
    @FXML
    private HBox profileImgPane;

    private HBox conversationbox[] = new HBox[1500];

    @FXML
    private Label status;
    @FXML
    private Button send;

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
    List<Button> conversation = new ArrayList<>();
    @FXML
    private AnchorPane msgAnchore = new AnchorPane();
    @FXML
    private Label typing;

    private VBox cons;

    private HBox wrap;
    private ImageView yekteb;
    @FXML
    private VBox msget;

    public void setReceiverId(int id) {
        Member receiver = new Member();
        this.receiverId = id;
        

        try {
            try {
                online = MemberService.getInstance().get(new Member(MySoulMate.MEMBER_ID));
            } catch (SQLException ex) {
                Logger.getLogger(GlobalViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Member m = MemberService.getInstance().get(new Member(receiverId));
            nom.setText(m.getFirstname());
            nom.getStyleClass().add("typing");
            String conn = m.isConnected() ? "Online" : "Offline";
            status.setText(conn);
            status.getStyleClass().add("typing");
            receiver = MemberService.getInstance().get(new Member(receiverId));
            profileImage.setImage(new Image(MySoulMate.UPLOAD_URL + PhotoService.getInstance().get(new Photo(receiver.getId(), Enumerations.PhotoType.PROFILE)).getUrl()));
            typing.getStyleClass().add("typing");
            typing.setVisible(false);

            init();
            getHisotrique();
//            goConversations();
            box.getSelectionModel().select(0);
            box.getItems().add("Emojis");
            box.getItems().add("☺");
            box.getItems().add("☹");
            box.getItems().add("💋");
            box.getItems().add("❤");
            box.getItems().add("♫");
            box.getItems().add("☺");
            box.setStyle("-fx-font-size : 15 px ; -fx-text-fill : #fff ;");
            inputmsg.setStyle("-fx-font-size : 20 px ;");
        } catch (Exception ex) {
            Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ConversationService cs = ConversationService.getInstance();
        Conversation c = new Conversation();
        c.setPerson1Id(MySoulMate.MEMBER_ID);
        c.setPerson2Id(receiverId);
        try {
            if ((cs.get(c)) != null) {
                
                
                c.setSeen(true);
                c.setSeenDate(new Timestamp(System.currentTimeMillis()));
                cs.update(c);
                System.out.println("update 3al da5la");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      

    }


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
                wrap = new HBox();
                if (data == null) {
                    return;
                }
                if ((data.toString()).equals(t)) {

                    typing.setText("Is typing");
                    typing.setVisible(true);

                } else {

                    typing.setVisible(false);
                    textField[i] = new Label();
                    textField[i].setMaxWidth(350);
                    textField[i].setWrapText(true);
                    textField[i].setText(" " + data.toString() + "\n");
                    textField[i].setTranslateX(20);
                    textField[i].getStyleClass().add("typing");
                    textField[i].setAlignment(Pos.CENTER);

                    wrap.getChildren().add(textField[i]);
                    content2.getChildren().add(wrap);
                    i = i + 1;
                    x.setContent(content2);
                    x.setVvalue(1.0d);
                }
            });
        });
    }

    private Client createClient() {
        return new Client("127.0.0.2", 55555, data -> {
            Platform.runLater(() -> {
                String t = "Is typing";
                String c = "Stopped";

                wrap = new HBox();
                if (data == null) {
                    return;
                }
                if ((data.toString()).equals(t)) {

                    typing.setText("Is typing");

                    typing.setVisible(true);

                } else {

                    typing.setVisible(false);

                    textField[i] = new Label();
                    textField[i].setMaxWidth(350);
                    textField[i].setWrapText(true);
                    textField[i].setText(" " + data.toString() + "\n");
                    textField[i].setTranslateX(20);
                    textField[i].getStyleClass().add("typing");
                    textField[i].setAlignment(Pos.CENTER);

                    wrap.getChildren().add(textField[i]);
                    content2.getChildren().add(wrap);
                    i = i + 1;
                    x.setContent(content2);
                    x.setVvalue(1.0d);
                }
            });
        });
    }

    public Parent getHisotrique() throws SQLException {

        msg = new Message();
        msg.setSenderId(online.getId());
        msg.setReceiverId(this.receiverId);

        List<Message> messages = MessageService.getInstance().getAll(msg);
        messages.forEach(e -> {
            if (e.getSenderId() == msg.getSenderId() && e.getReceiverId() == msg.getReceiverId()) {

                textField[i] = new Label();
                textField[i].setText(" " + e.getContent() + " \n");
                wrap = new HBox();
                wrap.setPrefWidth(msget.getPrefWidth()-80);
                wrap.setAlignment(Pos.TOP_LEFT);
//                textField[i].setTranslateX(-350);
                textField[i].getStyleClass().add("recumsg");
                textField[i].setAlignment(Pos.CENTER);
                wrap.getChildren().add(textField[i]);
                wrap.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                content2.getChildren().add(wrap);
                content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());
                i = i + 1;
                x.setContent(content2);
                x.setVvalue(1.0d);

            } else if (e.getSenderId() == msg.getReceiverId() && e.getReceiverId() == msg.getSenderId()) {
                textField[i] = new Label();
                textField[i].setText(" " + e.getContent() + " \n");
                wrap = new HBox();
                                wrap.setPrefWidth(msget.getPrefWidth()-80);

                wrap.setAlignment(Pos.TOP_LEFT);
                textField[i].setTranslateX(20);
                textField[i].getStyleClass().add("typing");
                textField[i].setAlignment(Pos.CENTER);
                wrap.getChildren().add(textField[i]);
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

    private Parent goConversations() throws SQLException {

        ConversationService cs = ConversationService.getInstance();
        MemberService ms = MemberService.getInstance();

        try {
            Conversation c = new Conversation();
            cons = new VBox();
            c.setPerson1Id(MySoulMate.MEMBER_ID);

            List<Conversation> convers = cs.getAll(c);
            convers.forEach(e -> {

                try {
                    Member m = new Member();
                    m.setId(e.getPerson1Id() == MySoulMate.MEMBER_ID ? e.getPerson2Id() : e.getPerson1Id());
                    m = ms.get(m);

                    Timestamp timestamp = e.getSeenDate();

                    String x = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(e.getSeenDate());
                    String seen = e.isSeen() ? e.getSeenDate().toString() : "no";
                    Button bc = new Button();
                    bc.setText(" " + e.getLabel() + "\n " + m.getPseudo() + " \n Seen :" + e.getSeenDate().toString());
                    bc.getStyleClass().add("recumsg");
                    String isConnected = m.isConnected() ? "Online" : "Offline";
                    bc.setAlignment(Pos.CENTER);
                    bc.setPrefWidth(cons.getMinWidth());
                    bc.setId(e.getId() + "");
                    bc.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                Button b = (Button) event.getTarget();
                                int conversationId = Integer.parseInt(b.getId());
                                Conversation conversation = ConversationService.getInstance().get(new Conversation(conversationId));

                                FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/InstantMessagingView.fxml");
                                ((InstantMessagingViewController) loader.getController()).setReceiverId(
                                        conversation.getPerson1Id() == MySoulMate.MEMBER_ID
                                        ? conversation.getPerson2Id()
                                        : conversation.getPerson1Id()
                                );
                            } catch (SQLException ex) {
                                Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });

                    cons.getChildren().add(bc);

                    i = i + 1;
                } catch (SQLException ex) {
                    Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (Exception e) {
            Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, e);
        }

        convs.setContent(cons);
        return convs;
    }

    @FXML
    private Parent goSend(ActionEvent event6) throws SQLException, IOException {

        String message = "";

        message += inputmsg.getText();
        inputmsg.clear();

        textField[i] = new Label();

        textField[i].setMaxWidth(500);

        textField[i].setWrapText(true);
        textField[i].setText(" " + message + " \n");

        wrap = new HBox();
        wrap.setSpacing(5);
         wrap.setPrefWidth(msget.getPrefWidth()-80);
         
        wrap.setAlignment(Pos.TOP_LEFT);
        textField[i].getStyleClass().add("recumsg");
        textField[i].setAlignment(Pos.CENTER);
//        textField[i].setTranslateX(-350);

        wrap.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        wrap.getChildren().add(textField[i]);
        content2.getChildren().add(wrap);
        content2.setPrefHeight(content2.getPrefHeight() + textField[i].getPrefHeight());

        i = i + 1;
        x.setContent(content2);
        ConversationService cs = ConversationService.getInstance();
        MessageService ms = MessageService.getInstance();
        msg = new Message();
        msg.setSenderId(MySoulMate.MEMBER_ID);

        msg.setReceiverId(this.receiverId);
        msg.setContent(message);
        try {
            connection.send((message.toString()));
            x.setVvalue(1.0d);
        } catch (Exception e) {

        }

        try {

            msg = ms.create(msg);

        } catch (Exception e) {

        }

        Conversation c = new Conversation();

        c.setPerson1Id(msg.getSenderId());

        c.setPerson2Id(msg.getReceiverId());

        try {

            if ((cs.get(c)) != null) {

                c.setSeen(false);
                c.setSeenDate(null);
                cs.update(c);
                System.out.println("update");

            } else {

                c.setPerson1Id(msg.getSenderId());
                c.setPerson2Id(msg.getReceiverId());
                c.setSeen(false);
                c.setLabel("New conversation");
                c.setSeenDate(null);
                c = cs.create(c);

            }
        } catch (Exception e) {

        }

        x.setVvalue(1.0d);
        return x;

    }

    @FXML
    private void goReceive(MouseEvent event) {
    }

    @FXML
    private void isWriting(KeyEvent event) {

        String message = "Is typing";
        try {

            connection.send((message.toString()));

        } catch (Exception e) {

        }

    }

    private void isWriting(InputMethodEvent event) {
        String message = "Is typing";
        try {

            connection.send((message.toString()));

        } catch (Exception e) {

        }
    }

    @FXML
    private void Emoji(ActionEvent event) {
        try {
//            box.show();
            if (box.getSelectionModel().getSelectedItem().toString() != "Emojis") {
                try {

                    String emojy = box.getSelectionModel().getSelectedItem().toString();
                    inputmsg.setText(inputmsg.getText() + " " + emojy);
                    box.setValue("Emojis");
                    box.setStyle("-fx-font-size : 15 px ; -fx-text-fill : #fff ;");
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void goprofile(MouseEvent event) {
        FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/OthersProfileView.fxml");
        ((OthersProfileViewController)loader.getController()).setUserId(receiverId);
    }
}
