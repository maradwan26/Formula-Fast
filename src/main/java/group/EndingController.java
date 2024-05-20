package group;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * This class will be the controller for the FXML file that displays the ending
 * screen, after the player completes all 6 levels. It has
 * three methods to control whether the player selects to check the leaderboard,
 * return the the main menu, or save exit the game
 * 
 * @author Matthew Marbina
 */
public class EndingController {

    /**
     * This method sends the player to the leaderboard when the respective button is
     * clicked by changing the scene to the leaderboard UI.
     */
    @FXML
    void leaderboard_clicked() {
        SceneHandler scene = SceneHandler.get_instance();
        try {
            scene.set_scene("Leaderboard");
        } catch (IOException e) {
            System.out.println("Attempt to load invalid fxml");
            e.printStackTrace();
        }
    }

    /**
     * This method sends the player to the main menu when the respective button is
     * clicked by changing the scene to the menu UI.
     */
    @FXML
    void menu_clicked() {
        SceneHandler scene = SceneHandler.get_instance();
        try {
            scene.set_scene("Menu");
        } catch (IOException e) {
            System.out.println("Attempt to load invalid fxml");
            e.printStackTrace();
        }
    }

    /**
     * This method saves the player's current data and then exits them from the
     * game.
     */
    @FXML
    void exit_clicked() {
        DataHandler data_handler = DataHandler.get_instance();
        data_handler.save_data();
        System.exit(0);
    }
}