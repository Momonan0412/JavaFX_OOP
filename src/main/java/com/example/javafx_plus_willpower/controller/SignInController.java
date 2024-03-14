package com.example.javafx_plus_willpower.controller;

import com.example.javafx_plus_willpower.utilities.DatabaseUtilities;
import com.example.javafx_plus_willpower.utilities.SceneUtilities;
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
                System.out.println("Clicked button connect,  debugging.........");
                if(DatabaseUtilities.userCheckerMethod(textField_Username.getText(), passwordField_password.getText())){
                    System.out.println("Passed the `userCheckerMethod`, debugging.........");
                    SceneUtilities.changeScene(actionEvent, "/com/example/javafx_plus_willpower/Main.fxml", "Game", null);
                }
            }
        });
        btn_SignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneUtilities.changeScene(actionEvent, "/com/example/javafx_plus_willpower/Sign_Up.fxml", "Sign Up", null);
            }
        });
    }
}
