package com.example.javafx_plus_willpower;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
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
                SceneUtilities.changeScene(actionEvent, "Main.fxml", "Game", null);
            }
        });
        btn_SignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneUtilities.changeScene(actionEvent, "Sign_Up.fxml", "Sign Up", null);
            }
        });
    }
}
