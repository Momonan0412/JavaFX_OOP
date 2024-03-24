package com.example.javafx_plus_willpower.controller;

import com.example.javafx_plus_willpower.sockets.client.ChatClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    ChatClient chatClient;
    private String userName;
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
//  NAME AND DISCONNECT ------------------------------------------------------------------------------------------------
    @FXML
    public AnchorPane anchorPane_NameAndDisconnect;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultSetter();
        btn_ButtonDisconnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                disconnectAction();
            }
        });
        btn_ChatSendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sendAction();
            }
        });
    }
    // TODO: Implement methods that would update the @FXMLs nodes.


    public ListView<String> getListView_ViewOnlinePlayers() {
        return listView_ViewOnlinePlayers;
    }

    public TextArea getTextArea_ChatMsgBox() {
        return textArea_ChatMsgBox;
    }

    private void defaultSetter(){
        textArea_ChatMsgBox.setEditable(false);
    }
    public void sendAction() {
        // TODO: "btn_ChatSendButton" HANDLES THIS, THE "send()" METHOD IS LOCATED IN THE CHAT CLIENT;
        // TODO: USE `textArea_SendMsgBox` IT IS THE THIS PROGRAM'S `messageTF`
//        if(!messageTF.getText().equals("")) {
//            chatClient.send(messageTF.getText());
//            messageTF.requestFocus();
//        }
        if(!textArea_SendMsgBox.getText().isEmpty()) {
            chatClient.send(textArea_SendMsgBox.getText());
            System.out.println(textArea_SendMsgBox.getText());
            textArea_SendMsgBox.requestFocus();
        }
    }
    public void disconnectAction() {
        // TODO: "btn_ButtonDisconnect" HANDLES THIS, THE "disconnect()" METHOD IS LOCATED IN THE CHAT CLIENT;
//        try {
//            chatClient.disconnect();
//        }
//        catch (Exception e) {
//            System.out.println(userName + "has disconnected.");
//        }
        try {
            chatClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // CONNECT METHOD SHOULD BE CALLED AFTER USER SUCCESSFULLY LOGGED IN
    public void connectAction() { // TODO: (?) SHOULD I MAKE ANOTHER CLASS FOR "CONNECT"?
        // TODO: AFTER LOGGING IN, GET THE USERNAME, UPDATE THE @userName, INSERT THE USERNAME AND PASSWORD IN DATABASE;
        // TODO:THE JAVAFX UI BASE IN THE USER,NAMELY: @text_NameOfThePlayer @listView_ViewOnlinePlayers and textArea_ChatMsgBox(?)
        try {
            final int port = 1337;
            final String host = "localhost";
            Socket socket = new Socket(host, port);
            System.out.println("You connected to: " + host);

            // TODO: FIX ME! READ!
            // DISPLAY WRONG USERNAME!
            userName = getText_NameOfThePlayer().getText();
            System.out.println("Inside the connectAction method, username: " + userName);

            chatClient = new ChatClient(socket, this);

            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.println(userName); // Print as the "Online player" therefore username is null
//            output.println(userName); // original, passed to the server
            output.flush();

            Thread t = new Thread(chatClient);
            t.start();

        } catch(Exception e) {
            System.err.println(e); // Debugging?
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Server not responding.");
                alert.show();
            });
            System.exit(0);
        }
    }
    public Text getText_NameOfThePlayer() {
        return text_NameOfThePlayer;
    }
    public TextArea getTextArea_SendMsgBox() { return textArea_SendMsgBox; }
}
