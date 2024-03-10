package com.example.javafx_plus_willpower;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main  extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the existing FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign_In.fxml"));
            Parent root = loader.load();

            // Create the main scene
            Scene mainScene = new Scene(root);

            // Set the main scene for the primary stage
            primaryStage.setScene(mainScene);

            // Set the title for the primary stage
            primaryStage.setTitle("Sign In");

            // Show the primary stage
            primaryStage.show();

            // Open a new stage with a new scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
