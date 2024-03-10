package com.example.javafx_plus_willpower;

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
                String username = textField_UserName.getText();
                String password = passwordField_Password.getText();
                String repeatedPassword = passwordField_PasswordRepeat.getText();
                // TODO: if @username @password and @repeatedPassword are null make a alert
                if(!password.equals(repeatedPassword)){
                    System.out.println(password + " " + repeatedPassword); // Debugger
                    // TODO: Make a separate class for a more beautiful alert UI! `na same niya ka pretty!`
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password and repeated password do not match. Please make sure both fields contain the same password.");
                        alert.show();
                    });
                } else {

                }
            }
        });
    }
}
