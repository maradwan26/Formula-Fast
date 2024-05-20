package group;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * A class to handle all UI interaction with the Menu.fxml
 * 
 * @author Ashton Franklin
 */
public class MenuController {

    /** Text label for the debug display */
    @FXML
    private Label text;

    /** Button to load level five */
    @FXML
    private Button level_five_button;

    /** Button to load level four */
    @FXML
    private Button level_four_button;

    /** Button to load level one */
    @FXML
    private Button level_one_button;

    /** Button to load level six */
    @FXML
    private Button level_six_button;

    /** Button to load level three */
    @FXML
    private Button level_three_button;

    /** Button to load level two */
    @FXML
    private Button level_two_button;

    /** A reference to the debug handler object */
    DebugHandler debug = DebugHandler.get_instance();

    /** Method to setup menu when loading ui */
    @FXML
    public void initialize() {
        if (debug.is_debug_mode()) {
            text.setVisible(true);
        } else {
            set_level_unlock_status();
        }
    }

    /**
     * A method to mark level buttons as locked if not yet unlocked
     */
    private void set_level_unlock_status() {
        LevelHandler level_handler = LevelHandler.get_instance();
        int current_level = level_handler.get_level();

        if (current_level < 2) {
            level_two_button.setText("LOCKED");
        }
        if (current_level < 3) {
            level_three_button.setText("LOCKED");
        }
        if (current_level < 4) {
            level_four_button.setText("LOCKED");
        }
        if (current_level < 5) {
            level_five_button.setText("LOCKED");
        }
        if (current_level < 6) {
            level_six_button.setText("LOCKED");
        }
    }

    /**
     * Method to load the leaderboard scene upon button click
     */
    public void load_leaderboard() {
        try {
            SceneHandler scene_handler = SceneHandler.get_instance();
            scene_handler.set_scene("Leaderboard");
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to load level one in gameplay
     */
    public void load_level_one() {
        try {
            // Set the level on gameplay controller
            GameplayController.current_level = 1;

            // Switch scene
            SceneHandler scene_handler = SceneHandler.get_instance();
            scene_handler.set_scene("Gameplay");
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to load level two in gameplay
     */
    public void load_level_two() {
        try {
            if (debug.is_debug_mode() || LevelHandler.get_instance().get_level() >= 2) {
                // Set the level on gameplay controller
                GameplayController.current_level = 2;

                // Switch scene
                SceneHandler scene_handler = SceneHandler.get_instance();
                scene_handler.set_scene("Gameplay");
            }
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to load level three in gameplay
     */
    public void load_level_three() {
        try {
            if (debug.is_debug_mode() || LevelHandler.get_instance().get_level() >= 3) {
                // Set the level on gameplay controller
                GameplayController.current_level = 3;

                // Switch scene
                SceneHandler scene_handler = SceneHandler.get_instance();
                scene_handler.set_scene("Gameplay");
            }
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to load level four in gameplay
     */
    public void load_level_four() {
        try {
            if (debug.is_debug_mode() || LevelHandler.get_instance().get_level() >= 4) {
                // Set the level on gameplay controller
                GameplayController.current_level = 4;

                // Switch scene
                SceneHandler scene_handler = SceneHandler.get_instance();
                scene_handler.set_scene("Gameplay");
            }
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to load level five in gameplay
     */
    public void load_level_five() {
        try {
            if (debug.is_debug_mode() || LevelHandler.get_instance().get_level() >= 5) {
                // Set the level on gameplay controller
                GameplayController.current_level = 5;

                // Switch scene
                SceneHandler scene_handler = SceneHandler.get_instance();
                scene_handler.set_scene("Gameplay");
            }
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to load level six in gameplay
     */
    public void load_level_six() {
        try {
            if (debug.is_debug_mode() || LevelHandler.get_instance().get_level() >= 6) {
                // Set the level on gameplay controller
                GameplayController.current_level = 6;

                // Switch scene
                SceneHandler scene_handler = SceneHandler.get_instance();
                scene_handler.set_scene("Gameplay");
            }
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to run when logout button is clicked
     */
    @FXML
    public void logout() {
        AccountHandler account_handler = AccountHandler.get_instance();
        account_handler.logout();
    }

    /**
     * Method to handle keyboard interaction, toggle debug mode
     * 
     * @param event The key event that contains the keycode of the key pressed
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        // Check if the pressed key is 'P'
        if (event.getCode() == KeyCode.P) {
            if (debug.is_debug_mode()) {
                debug.set_debug_mode(false);
                text.setVisible(false);
                set_level_unlock_status();
            } else {
                debug.set_debug_mode(true);
                text.setVisible(true);

                // Display all levels as unlocked on buttons
                level_two_button.setText("Level 2 (-)");
                level_three_button.setText("Level 3 (+/-)");
                level_four_button.setText("Level 4 (x)");
                level_five_button.setText("Level 5 (÷)");
                level_six_button.setText("Level 6 (+/-/x/÷)");
            }

            event.consume();
        }
    }

}
