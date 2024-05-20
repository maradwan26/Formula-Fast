package group;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

/**
 * The {@code TutorialController} class is responsible for handling user
 * interactions
 * within the tutorial section of the application. It manages the transition
 * from the tutorial
 * scene to the main menu scene when the continue button is clicked.
 * 
 * @author Que Hung Dang
 * @author Mahmoud Radwan
 */

public class TutorialController {

    /**
     * Text box for the first part of the tutorial,
     * once this button is clicked, the next part of the
     * tutorial will show up.
     */
    @FXML
    private Button part1;

    /**
     * Text box for the second part of the tutorial,
     * once this button is clicked, the next part of the
     * tutorial will show up.
     */
    @FXML
    private Button part2;

    /**
     * Text box for the third part of the tutorial,
     * once this button is clicked, the next part of the
     * tutorial will show up.
     */
    @FXML
    private Button part3;

    /**
     * Text box for the fourth part of the tutorial,
     * once this button is clicked, the next part of the
     * tutorial will show up.
     */
    @FXML
    private Button part4;

    /**
     * Text box for the fifth part of the tutorial,
     * once this button is clicked, the next part of the
     * tutorial will show up.
     */
    @FXML
    private Button part5;

    /**
     * "Finish" button goes to the main menu when clicked
     * as the tutorial is finished.
     */
    @FXML
    private Button finish;

    /**
     * Handles the click action on each tutorial text box within the tutorial scene.
     * This method initializes each part as invisible except the first.
     * The next part of the tutorial is shown when the current part is clicked.
     */
    public void initialize() {
        // Initialize buttons to be invisible except the first one
        part1.setVisible(true);
        part2.setVisible(false);
        part3.setVisible(false);
        part4.setVisible(false);
        part5.setVisible(false);
        finish.setVisible(false);

        // Add click event listeners to each part button
        part1.setOnAction(event -> part2.setVisible(true));
        part2.setOnAction(event -> part3.setVisible(true));
        part3.setOnAction(event -> part4.setVisible(true));
        part4.setOnAction(event -> part5.setVisible(true));
        part5.setOnAction(event -> finish.setVisible(true));
    }

    /**
     * Handles the click action on the "Finish" button within the tutorial scene.
     * This method attempts to change the current scene to the main menu. If the
     * specified
     * FXML file for the menu scene is not found, it catches the {@code IOException}
     * and
     * prints an error message indicating an attempt to load an invalid FXML file.
     */
    @FXML
    public void finish_clicked() {
        try {
            SceneHandler sceneHandler = SceneHandler.get_instance();
            sceneHandler.set_scene("Menu");
        } catch (IOException e) {
            System.out.println("Attempt to load invalid fxml");
        }
    }
}