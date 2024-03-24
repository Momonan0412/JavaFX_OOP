package com.example.javafx_plus_willpower.controller;

import com.example.javafx_plus_willpower.callbacks.UIUpdateCallback;
import com.example.javafx_plus_willpower.utilities.DatabaseUtilities;
import com.example.javafx_plus_willpower.utilities.SceneUtilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable, UIUpdateCallback {
    @FXML
    private TextField textField_Username;
    @FXML
    private PasswordField passwordField_password;
    @FXML
    private Button btn_ButtonConnect;
    @FXML
    private Button btn_ButtonExit;
    @FXML
    private Button btn_SignUp;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_ButtonConnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Query in database and find the textField_Username and passwordField_password.
                // If success, go to main.fxml
                System.out.println("Clicked button connect,  debugging.........");
                if(DatabaseUtilities.userCheckerMethod(textField_Username.getText(), passwordField_password.getText())){
                    System.out.println("Debugging username! -> \"" + textField_Username.getText() + "\"");
                    System.out.println("Passed the `userCheckerMethod`, debugging.........");
                    // Switching to the Chat.fxml scene will automatically instantiate the ChatController class.
                    SceneUtilities.changeScene(actionEvent, "/com/example/javafx_plus_willpower/Chat.fxml", "Game", null,textField_Username.getText(), (loader)->{
                        onUIUpdated(loader);
                    });
                }
            }
        });
        btn_SignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneUtilities.changeScene(actionEvent, "/com/example/javafx_plus_willpower/Sign_Up.fxml", "Sign Up", null, null, null);
            }
        });
    }

    @Override
    public void onUIUpdated(FXMLLoader loader) {
        ChatController controller = loader.getController();
        controller.connectAction();
    }
}
