package com.example.javafx_plus_willpower.controller;

import com.example.javafx_plus_willpower.utilities.DatabaseUtilities;
import com.example.javafx_plus_willpower.utilities.SceneUtilities;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField textField_UserName;
    @FXML
    private PasswordField passwordField_Password;
    @FXML
    private PasswordField passwordField_PasswordRepeat;
    @FXML
    private Button btn_RegisterButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = textField_UserName.getText().trim();
                String password = passwordField_Password.getText().trim();
                String repeatedPassword = passwordField_PasswordRepeat.getText().trim();
                // TODO: if @username @password and @repeatedPassword are null make a alert
                // TODO: Make a separate class for a more beautiful alert UI! `na same niya ka pretty!`
                if(username.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()){
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please make sure all fields are not empty!");
                        alert.show();
                    });
                }
                if(!password.equals(repeatedPassword)){
                    System.out.println(password + " " + repeatedPassword); // Debugger
                    // TODO: Make a separate class for a more beautiful alert UI! `na same niya ka pretty!`
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password and repeated password do not match. Please make sure both fields contain the same password.");
                        alert.show();
                    });
                } else {
                    // Attempt to sign up

                    // TODO: Make a separate class for a more beautiful alert UI! `na same niya ka pretty!`
                    if (!DatabaseUtilities.userCheckerMethod(username, password)) {
                        String signupResult = DatabaseUtilities.signUpMethod(username, password);
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText(signupResult);
                            alert.show();

                            // If signup was successful, change the scene
                            if (signupResult.equals("Data inserted successfully!")) {
                                SceneUtilities.changeScene(actionEvent, "Sign_In.fxml", "Sign In", null);
                            }
                        });
                    }
                }
            }
        });
    }
}
