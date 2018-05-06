/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Conversation;
import models.Member;
import services.ConversationService;
import services.MemberService;
import services.MessageService;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class ConversationViewController implements Initializable {

    @FXML
    private ScrollPane convs;
    @FXML
    private VBox cons;
    List<Button> conversation = new ArrayList<>();

    int i;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goConversations();

    }

    private Parent goConversations() {

        MessageService cs = MessageService.getInstance();
        MemberService ms = MemberService.getInstance();

        try {
            Member c = new Member();
            c.setId(MySoulMate.MEMBER_ID);
            List<Conversation> convers = cs.getAllConversations(c);

            convers.forEach(e -> {

                try {
                    Member m = new Member();
                    m.setId(e.getPerson1Id());
                    m = ms.get(m);
                    Button bc = new Button();
                    bc.setMinWidth(407);
                    bc.setText(" " + m.getPseudo() + " \n Seen :" + e.getLabel());
                    bc.getStyleClass().add("recu");
                    String isConnected = m.isConnected() ? "Online" : "Offline";
                    bc.setAlignment(Pos.CENTER);
                    bc.setId("" + m.getId());
                    bc.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Button b = (Button) event.getTarget();
                            int receiver = Integer.parseInt(b.getId());
                            FXMLLoader loader = GlobalViewController.getInstance().setMainContent("/view/InstantMessagingView.fxml");
                            ((InstantMessagingViewController) loader.getController()).setReceiverId(receiver);

                        }
                    });
                    bc.setPrefWidth(268);
                    bc.getStyleClass().add("recu");

                    cons.getChildren().add(bc);

                    i = i + 1;
                } catch (SQLException ex) {
                    Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (Exception e) {
            Logger.getLogger(InstantMessagingViewController.class.getName()).log(Level.SEVERE, null, e);
        }

        return cons;
    }

}
