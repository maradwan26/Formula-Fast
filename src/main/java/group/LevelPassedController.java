package group;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * @author Mahmoud Radwan
 *         This class handles the ui interactions and loading elements for the
 *         level passed page
 */

public class LevelPassedController {

    /** Label to display the earned score */
    @FXML
    private Text score;

    /**
     * Text label that displays whether the earned score is a new high score or not
     */
    @FXML
    private Text new_highscore_text;

    /** Represents the score earned in the level played */
    public static int level_score;

    /** Represents the highscore in the completed level before this completion */
    public static int previous_highscore;

    /** Represents the level that was completed */
    public static int completed_level;

    /** Represents the players best unlocked level before completing this one */
    public static int previous_max_level;

    /** Method to run when level passed screen is loaded */
    @FXML
    public void initialize() {
        score.setText(Integer.toString(level_score));
        if (level_score <= previous_highscore) {
            new_highscore_text.setVisible(false);
        }
    }

    /**
     * This method sends the user back to menu after clicking the continue
     * button. It uses the sceneHandler to set the next scene which is the
     * Main Menu.
     */
    public void continue_clicked() {
        try {
            SceneHandler scene_handler = SceneHandler.get_instance();

            if (completed_level == 6 && (previous_max_level == 6 || DebugHandler.get_instance().is_debug_mode())) {
                scene_handler.set_scene("Ending");
            } else {
                scene_handler.set_scene("Menu");
            }
        } catch (IOException e) {
            System.out.println("Attempt to load invalid fxml");
        }
    }

}
