package com.example.javafx_plus_willpower.utilities;

import com.example.javafx_plus_willpower.callbacks.UIUpdateCallback;
import com.example.javafx_plus_willpower.controller.ChatController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
// TODO: Check if this utility class should be "static" since all client would need to access this
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
    public static void changeScene(ActionEvent event, String fxmlFile, String title,
                                   Object controller, String playerName,
                                   UIUpdateCallback callback){ // To be change if this method propagates exception
        Parent root = null; // Assign root node for the new scene
        FXMLLoader loader = null;
        try{
            loader = new FXMLLoader(SceneUtilities.class.getResource(fxmlFile));
            root = loader.load();
            if(root == null) System.out.println("The root is null!");
            if (controller != null) {
                // Set the provided controller instance using FXMLLoader
                controller = loader.getController();
                // TODO: Implement any logic that involves the previous controller, if needed
                // Example: Setting or changing the controller UI
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading FXML file: " + fxmlFile, e);
        } finally {
            ((Node)event.getSource()).getScene().setFill(Color.TRANSPARENT);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));

            assert loader != null;
            // TODO: FIX! IMPORTANT! CHECK THE COMMENT BELOW THIS!
            // The List of Players is not updated!
            if(loader.getController() instanceof ChatController){
                FXMLLoader finalLoader = loader;
                Platform.runLater(()->{
                    ((ChatController) finalLoader.getController()).getText_NameOfThePlayer().setText(playerName);
                    System.out.println("In the `changeScene` method! Debugging! " + playerName);
//                    ((ChatController) finalLoader.getController()).connectAction();
                    callback.onUIUpdated(finalLoader);
                });
            }
            stage.centerOnScreen();
            stage.show();
        }
    }
}
// TODO: EYES HERE
//Certainly! Here's a summary:
//
// 1. **Current Approach**:
// - The `connectAction` method is directly called inside the `Platform.runLater` block after setting the player name in the `changeScene` method.
// - This approach assumes that `Platform.runLater` will immediately execute the provided `Runnable`, ensuring that `connectAction` is called after setting the player name.
//
// 2. **Vulnerability**:
// - There's no guarantee of immediate execution of code inside `Platform.runLater`. It merely queues the provided `Runnable` to be executed later on the JavaFX Application Thread.
// - If multiple UI updates are queued using `Platform.runLater`, the order of execution may not match the order in which they were added, leading to potential concurrency issues or unexpected behavior.
// - This can result in `connectAction` being called before the player name is set or in an unpredictable order with other queued UI updates.
//
// 3. **Recommended Approach**:
// - Implement a callback mechanism using interfaces to ensure that `connectAction` is invoked after the UI update (setting the player name) is complete.
// - Use the callback to explicitly define the action to be performed after the UI update, providing better control over the sequence of execution and avoiding potential concurrency issues.
//
//By following the recommended approach, you can ensure a predictable sequence of execution and mitigate the risk of concurrency-related issues in your application.
