package com.example.javafx_plus_willpower.controller;

import com.example.javafx_plus_willpower.sockets.client.ChatClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    public static String userName = "Anon";
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

    }
    // TODO: Implement methods that would update the @FXMLs nodes.

    public void setListView_ViewOnlinePlayers(ListView<String> listView_ViewOnlinePlayers) {
        this.listView_ViewOnlinePlayers = listView_ViewOnlinePlayers;
    }
    private void defaultSetter(){
        textArea_ChatMsgBox.setEditable(false);
    }
    // CONNECT METHOD SHOULD BE CALLED AFTER USER SUCCESSFULLY LOGGED IN
    public void connect() { // TODO: (?) SHOULD I MAKE ANOTHER CLASS FOR "CONNECT"?
        // TODO: AFTER LOGGING IN, GET THE USERNAME, UPDATE THE @userName, INSERT THE USERNAME AND PASSWORD IN DATABASE;
        // TODO:THE JAVAFX UI BASE IN THE USER,NAMELY: @text_NameOfThePlayer @listView_ViewOnlinePlayers and textArea_ChatMsgBox(?)
        try {
            final int port = 1337;
            final String host = "localhost";
            Socket socket = new Socket(host, port);
            System.out.println("You connected to: " + host);

            ChatClient chatClient = new ChatClient(socket, this);

            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.println(userName);
            output.flush();

            Thread t = new Thread(chatClient);
            t.start();

        } catch(Exception e) {
            System.out.println(e); // Debugging?
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Server not responding.");
                alert.show();
            });
//            System.exit(0);
        }
    }
}
