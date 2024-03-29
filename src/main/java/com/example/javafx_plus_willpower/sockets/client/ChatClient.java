package com.example.javafx_plus_willpower.sockets.client;
import com.example.javafx_plus_willpower.controller.ChatController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import org.w3c.dom.Text;

import java.net.Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

// Refactored Java Swing to JavaFX

/**
 * Manages all messages sent among clients, printing them to the GUI as well as
 * the console.
 */
public class ChatClient implements Runnable {

    public static String userName = "Anon"; // TODO: HANDLE THE NAME, WHERE TO PUT!
    Socket socket;
    Scanner input;
    PrintWriter output;
    private ChatController chatController;

    /**
     * Simple constructor which takes a client socket and assigns it to a local
     * variable socket.
     *
     * @param s Socket taken from the client.
     */
    public ChatClient(Socket s, ChatController chatController) {
        this.socket = s;
        this.chatController = chatController;
    }

    /**
     * Infinite loop which runs as long as the socket is not closed.
     */
    @Override
    public void run() {
        try {
            try {
                input = new Scanner(socket.getInputStream());
                output = new PrintWriter(socket.getOutputStream());
                output.flush();

                checkStream();
            }
            finally {
                socket.close();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * A method which calls the receive method as long as the client is
     * connected.
     */
    public void checkStream() {
        while (true) {
            receive();
        }
    }

    /**
     * A method which waits for client messages and appends them to the
     * conversation.
     */
    public void receive() {
        if (input.hasNext()) {
            String message = input.nextLine();

            if(message.contains("#?!"))
            {
                String temp = message.substring(3);
                temp = temp.replace("[", "");
                temp = temp.replace("]", "");

                String[] currentUsers = temp.split(", ");
//                System.out.println(Arrays.toString(currentUsers));
                // TODO: Use `ListView ListView_ViewOnlinePlayers;`
                // HOW TO HANDLE? THE GUI JAVA FX! SHOULD BE THREAD SAFE!

                // TODO: THE PROBLEM LIES IN `ChatServer` Class!
                //  IT DOES NOT UPDATE THE LIST OF USER WHEN LOGGED IN! SORE WA MONDAI DESU NE!
                Platform.runLater(() -> {
                    ObservableList<String> listOfUsers = FXCollections.observableArrayList(currentUsers);
                    chatController.getListView_ViewOnlinePlayers().getItems().setAll(listOfUsers);
//                    System.out.println(listOfUsers);
                });
                // TODO: MAKE ANOTHER CLASS! (?)
            }
            else {
                // TODO: Use `TextArea textArea_ChatMsgBox;`
//                ChatClientGUI.conversationTA.append(message + "\n");
                Platform.runLater(() -> {
                    chatController.getTextArea_ChatMsgBox().appendText(message + "\n");
                });
            }
        }
    }

    /**
     * A method which sends the user's message to the chat.
     * @param s
     */
    public void send(String s) {
        // TODO: USE `Text text_NameOfThePlayer;` USE GETTER METHOD
        /* For setting the username of the Client. Add `String userName`
         * output.println(ChatClientGUI.userName + ": " + s);
         * output.flush();
         * */
        Platform.runLater(()->{
            output.println(chatController.getText_NameOfThePlayer().getText() + ": " + s);
            output.flush();
            // TODO: USE `textArea_SendMsgBox` USE GETTER METHOD
            // ChatClientGUI.messageTF.setText("");
            chatController.getTextArea_SendMsgBox().setText("");
        });
    }

    /**
     * A method which disconnects the user from the chat room.
     * @throws IOException
     */
    public void disconnect() throws IOException { // Check if this should be propagating exceptions.
        // TODO: Use `Text text_NameOfThePlayer;`
//        output.println(ChatClientGUI.userName + " has disconnected.");
        Platform.runLater(()->{
            try {
                output.println(chatController.getText_NameOfThePlayer().getText() + " has disconnected.");
                output.flush();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // TODO: Use JavaFX Alert (There should be a class that handles alerts.)
//        JOptionPane.showMessageDialog(null, "You disconnected!");
//        Platform.runLater(() -> {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setContentText("You disconnected!");
//            alert.show();
//        });
        System.exit(0);
    }
}