package group;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Controller class to handle the leaderboard screen and functionality
 * 
 * @author Ashton Franklin
 */
public class LeaderboardController {

    /** Represents the text label displaying score 1 */
    @FXML
    private Text score_1;

    /** Represents the text label displaying score 2 */
    @FXML
    private Text score_2;

    /** Represents the text label displaying score 3 */
    @FXML
    private Text score_3;

    /** Represents the text label displaying score 4 */
    @FXML
    private Text score_4;

    /** Represents the text label displaying score 5 */
    @FXML
    private Text score_5;

    /** Represents the textl label displaying username 1 */
    @FXML
    private Text username_1;

    /** Represents the text label displaying username 2 */
    @FXML
    private Text username_2;

    /** Represents the text label displaying username 3 */
    @FXML
    private Text username_3;

    /** Represents the text label displaying username 4 */
    @FXML
    private Text username_4;

    /** Represents the text label displaying username 5 */
    @FXML
    private Text username_5;

    /**
     * Method to return the user to the main menu upon button click
     */
    public void return_to_menu() {
        try {
            SceneHandler scene_handler = SceneHandler.get_instance();
            scene_handler.set_scene("Menu");
        } catch (IOException e) {
            System.out.println("Attempt to load invalid scene");
        }
    }

    /**
     * Method to load the top 5 scoring users data onto the leaderboard screen
     */
    public void load_leaderboard_content() {
        Text username_labels[] = { username_1, username_2, username_3, username_4, username_5 };
        Text score_labels[] = { score_1, score_2, score_3, score_4, score_5 };

        DataHandler data_handler = DataHandler.get_instance();
        List<String[]> sorted_data = data_handler.get_sorted_data();

        try {
            for (int i = 0; i < (sorted_data.size() < 5 ? sorted_data.size() : 5); i++) {
                String[] curr_record = sorted_data.get(i);

                if (curr_record == null)
                    continue;

                Text curr_username_label = username_labels[i];
                Text curr_score_label = score_labels[i];

                curr_username_label.setText(curr_record[0]);
                curr_score_label.setText(curr_record[3]);
            }
        } catch (Exception e) {
            System.out.println("load_leaderboard_content() Error.");
            e.printStackTrace();
        }
    }

    /**
     * Method that runs when associated fxml scene is loaded, calls
     * load_leaderboard_content() method.
     */
    @FXML
    public void initialize() {
        load_leaderboard_content();
    }

}
