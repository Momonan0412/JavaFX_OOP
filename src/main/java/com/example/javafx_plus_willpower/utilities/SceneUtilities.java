package com.example.javafx_plus_willpower.utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtilities {
    private SceneUtilities() {
    }

    /**
     * Changes the current scene by loading an FXML file, setting it as the root
     * of a new scene, and updating the stage with the new scene and title.
     *
     * @param event      The ActionEvent triggering the scene change.
     * @param fxmlFile   The path to the FXML file defining the new scene's structure.
     * @param title      The title to be set for the new stage.
     * @param controller An optional controller instance to be associated with the loaded FXML file.
     *                   If provided, the controller is set using FXMLLoader and can be used
     *                   to interact with the loaded UI elements or perform other actions.
     * @throws RuntimeException If an IOException occurs during the FXML file loading process.
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, Object controller){ // To be change if this method propagates exception
        Parent root = null; // Assign root node for the new scene
        try{
            FXMLLoader loader = new FXMLLoader(SceneUtilities.class.getResource(fxmlFile));
            root = loader.load();
            if(root == null) System.out.println("The root is null!");
            if (controller != null) {
                // Set the provided controller instance using FXMLLoader
                controller = loader.getController();
                // TODO: Implement any logic that involves the controller, if needed
                // Example: Setting or changing the controller UI
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading FXML file: " + fxmlFile, e);
        } finally {
            ((Node)event.getSource()).getScene().setFill(null);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        }
    }
}
