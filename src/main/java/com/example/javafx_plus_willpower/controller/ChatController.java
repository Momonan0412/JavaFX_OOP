package com.example.javafx_plus_willpower.controller;

import com.example.javafx_plus_willpower.sockets.client.ChatClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    private static ChatClient chatClient;
    // CHAT PANE------------------------------------------------------------------------------------------------------------
    @FXML
    private TextArea textArea_ChatMsgBox; // TODO: `setEditable(false);`
    @FXML
    private TextArea textArea_SendMsgBox;
    @FXML
    private Button btn_ChatSendButton;
// PLAYER NAME AND DISCONNECT PANE -------------------------------------------------------------------------------------
    @FXML
    private Text text_NameOfThePlayer;
    @FXML
    private Button btn_ButtonDisconnect;
//  USERNAME AND LIST OF PLAYER ----------------------------------------------------------------------------------------
    @FXML
    private ListView<String> listView_ViewOnlinePlayers; // TODO: implement `ObservableList<String>` and append.
//  THE PARENT AND CHILD ---------------------------------------------------------------------------------------------------------
    @FXML
    private AnchorPane anchorPane_ParentOfChat;
    @FXML
    private AnchorPane anchorPane_ChatContainer;
    @FXML
    private AnchorPane anchorPane_OnlinePlayerContainer;
    @FXML
    public AnchorPane anchorPane_NameAndDisconnect;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultSetter();
    }
    // TODO: Implement methods that would update the @FXMLs nodes.

    public void setListView_ViewOnlinePlayers(ListView<String> listView_ViewOnlinePlayers) {
        this.listView_ViewOnlinePlayers = listView_ViewOnlinePlayers;
    }
    private void defaultSetter(){
        textArea_ChatMsgBox.setEditable(false);
//        anchorPane_ParentOfChat.setOpacity(0.0);
//        anchorPane_ChatContainer.setOpacity(1.0);
//        anchorPane_OnlinePlayerContainer.setOpacity(1.0);
//        anchorPane_NameAndDisconnect.setOpacity(1.0);
    }
}
