package group;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * This class is the controller for InstructorMode.fxml, which displays the
 * instructor mode UI. This class contains two methods to display the progress
 * for a searched user, and to exit the instructor mode and return back to the
 * menu.
 * 
 * @author Matthew Marbina
 */
public class InstructorModeController {

    /**
     * TextField instance variable username, generated from FXML file, used to store
     * the player's username after the instructor submits the search, if its in the
     * database.
     */
    @FXML
    private TextField username;

    /**
     * Text instance variable username_display, generated from FXML file, displays
     * the player's username after confirming it is in the database
     */
    @FXML
    private Text username_display;

    /**
     * Text instance variable current_level, generated from FXML file, displays the
     * current level the selected player is on
     */
    @FXML
    private Text current_level;

    /**
     * Text instance variable level_one_score, generated from FXML file, displays
     * the score from level one from the selected player, 0 if they haven't played
     * it yet
     */
    @FXML
    private Text level_one_score;

    /**
     * Text instance variable level_two_score, generated from FXML file, displays
     * the score from level two from the selected player, 0 if they haven't played
     * it yet
     */
    @FXML
    private Text level_two_score;

    /**
     * Text instance variable level_three_score, generated from FXML file, displays
     * the score from level three from the selected player, 0 if they haven't played
     * it yet
     */
    @FXML
    private Text level_three_score;

    /**
     * Text instance variable level_four_score, generated from FXML file, displays
     * the score from level four from the selected player, 0 if they haven't played
     * it yet
     */
    @FXML
    private Text level_four_score;

    /**
     * Text instance variable level_five_score, generated from FXML file, displays
     * the score from level five from the selected player, 0 if they haven't played
     * it yet
     */
    @FXML
    private Text level_five_score;

    /**
     * Text instance variable level_six_score, generated from FXML file, displays
     * the score from level six from the selected player, 0 if they haven't played
     * it yet
     */
    @FXML
    private Text level_six_score;

    /**
     * Text instance variable total_score, generated from FXML file, displays the
     * players total score summed from all 6 levels, 0 if they haven't completed any
     * levels
     */
    @FXML
    private Text total_score;

    /**
     * This method displays the progress the searched user has throughout the game.
     */
    @FXML
    public void display_progress() {
        String input_username = username.getText();
        // Checks if the instructor leaves the search bar blank, if so, prompts them to
        // enter a username.
        if (input_username.equals("")) {
            username.setPromptText("Please Input A Username!");
            username.clear();
        } else {
            // If the search bar is not empty and the instructor searches a name, checks if
            // the input name exists in the software database.
            // If username does not exist, resets all fields and tells instructor the
            // username does not exist.
            DataHandler usernameExists = DataHandler.get_instance();
            if (usernameExists.get_data_from_user(input_username) == null) {
                username.setPromptText("This Username Does Not Exist!");
                username.clear();
                username_display.setText("Player Username");
                current_level.setText("###");
                level_one_score.setText("###");
                level_two_score.setText("###");
                level_three_score.setText("###");
                level_four_score.setText("###");
                level_five_score.setText("###");
                level_six_score.setText("###");
                total_score.setText("###");
            } else {
                // If the input username exists in the database, displays that player's
                // username, current level they are on, scores on each level, and total score.
                username_display.setText(input_username);
                String[] user_data = usernameExists.get_data_from_user(input_username);
                if (user_data[2].equals("7")) {
                    current_level.setText("Game Completed");
                } else {
                    current_level.setText(user_data[2]);
                }
                level_one_score.setText(user_data[4]);
                level_two_score.setText(user_data[5]);
                level_three_score.setText(user_data[6]);
                level_four_score.setText(user_data[7]);
                level_five_score.setText(user_data[8]);
                level_six_score.setText(user_data[9]);
                total_score.setText(user_data[3]);
            }
        }
    }

    /**
     * This method returns the instructor back to the login page when the Exit
     * button is clicked.
     */
    @FXML
    public void exit_instructor_mode() {
        SceneHandler current_scene = SceneHandler.get_instance();
        try {
            current_scene.set_scene("Login");
        } catch (IOException e) {
            System.out.println("Attempted to load invalid scene.");
            e.printStackTrace();
        }
    }
}