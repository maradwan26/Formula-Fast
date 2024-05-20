package group;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;

/**
 * A class to control the main gameplay of the game utilizing other handler
 * classes
 * 
 * @author Ashton Franklin
 * @author Mahmoud Radwan
 * @author Gabriel Azhari
 * @author Matthew Matthew Marbina
 * @author Que Hung Dang
 */
public class GameplayController {

    /** Text field to contain the players given answer */
    @FXML
    private TextField answer_field;

    /** Label displaying the currently played level */
    @FXML
    private Text level_label;

    /** Label displaying the pre game countdown */
    @FXML
    private Text countdown_timer_label;

    /** Image of a computer controlled racer */
    @FXML
    private ImageView opponent_sprite_1;

    /** Image of a computer controlled racer */
    @FXML
    private ImageView opponent_sprite_2;

    /** Image of a computer controlled racer */
    @FXML
    private ImageView opponent_sprite_3;

    /** Image of the player racer */
    @FXML
    private ImageView player_sprite;

    /** Label showing the current question to the player */
    @FXML
    private Text question_label;

    /** Label showing the current question number to the player */
    @FXML
    private Text question_number_label;

    /** Label showing how much time is remaining */
    @FXML
    private Text time_label;

    /** Label showing the username of the current player */
    @FXML
    private Text username_label;

    /**
     * An instance of racer handler being used by this level to control the racers
     */
    private RacerHandler racer_handler;

    /**
     * A reference to the question handler singleton used to generate and validate
     * questions
     */
    private QuestionHandler question_handler = QuestionHandler.get_instance();

    /** An integer representing how much time is remaining in the level */
    private int time_remaining;

    /** An integer representing the maximum amount of time to complete the level */
    private int max_time = 240;

    /** An integer representing the current level */
    public static int current_level;

    /** Represents the current number of completed questions */
    private int current_progress;

    /** Represents if the level is being played */
    private boolean playing;

    /** Create timer used to track gameplay time */
    Timer gameplay_timer = new Timer();

    /** Initialization method ran whenever the gameplay screen is loaded */
    @FXML
    public void initialize() {
        // Set username on screen
        AccountHandler account_handler = AccountHandler.get_instance();
        username_label.setText(account_handler.get_current_user());

        // Set level label on screen
        level_label.setText("Level " + current_level);

        // Set progress to 0
        current_progress = 1;

        // Set playing to true
        playing = true;

        // Create 3 computer controlled racers
        Racer[] racers = new Racer[3];
        racers[0] = new Racer(opponent_sprite_1);
        racers[1] = new Racer(opponent_sprite_2);
        racers[2] = new Racer(opponent_sprite_3);

        // Create racer handler with 3 created racers
        racer_handler = new RacerHandler(racers);

        // Start countdown timer
        Timer countdown_timer = new Timer();
        countdown_timer.scheduleAtFixedRate(new TimerTask() {
            int countdown = 5;

            @Override
            public void run() {
                if (countdown == 0) {
                    countdown_timer_label.setVisible(false);
                    countdown_timer.cancel();

                    // Start computer racers
                    racer_handler.start_racers();

                    // Get an initial question
                    String question_prompt = question_handler.generate_new_question(GameplayController.current_level);

                    // Display new question to the player
                    question_label.setText(question_prompt);
                } else {
                    countdown_timer_label.setText("START IN " + countdown);
                }

                countdown--;
            }
        }, 0, 1000);

        // Start Gameplay Timer
        time_remaining = max_time;

        gameplay_timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                --time_remaining;
                time_label.setText(time_remaining + "s");

                if (time_remaining == 0) {
                    fail_level();
                    gameplay_timer.cancel();
                } else if (current_progress >= 10) {
                    gameplay_timer.cancel();
                }
            }
        }, 5000, 1000);
    }

    /**
     * Method called when a player submits their answer to validate and increase
     * progress
     */
    public void submit_answer() {
        // Use question handler to check if answer is valid
        boolean is_correct = question_handler.validate_answer(answer_field.getText());

        // Clear text from field
        answer_field.clear();

        // Run code if correct or incorrect
        if (is_correct) {
            // Increase progress
            current_progress++;

            // Flash question number label green to show correct
            question_number_label.setFill(Color.GREEN);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    question_number_label.setFill(Color.BLACK);
                }
            }, 500);
            question_number_label.setText(Integer.toString(current_progress) + "/10");

            // Move player car
            move_player();

            if (current_progress > 10) {
                question_label.setText("");
                completed_level();
            } else {
                // Get new question
                String question_prompt = question_handler.generate_new_question(GameplayController.current_level);
                question_label.setText(question_prompt);
            }
        } else {
            question_number_label.setFill(Color.RED);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    question_number_label.setFill(Color.BLACK);
                }
            }, 500);
        }
    }

    /** Method to handle keyboard input on the gameplay screen 
     * @param event The key event being acted on
    */
    public void handle_key_press(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && playing) {
            submit_answer();
        }

        event.consume();
    }

    /**
     * A method to move the players sprite across the screen
     */
    private void move_player() {
        // Move sprite across screen
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(player_sprite);
        translate.setByX(90);
        translate.play();
    }

    /**
     * Method to be ran when the user fails the level
     */
    public void fail_level() {
        try {
            if (playing) {
                playing = false;
                racer_handler.stop_racers();
                gameplay_timer.cancel();
                SceneHandler sceneHandler = SceneHandler.get_instance();
                sceneHandler.set_scene("LevelFailed");
            }
        } catch (IOException e) {
        }
    }

    /**
     * Method to be ran when the user completes the level
     */
    public void completed_level() {
        if (playing) {
            playing = false;
            try {
                // Stop racers and cancel gameplay loop
                racer_handler.stop_racers();
                gameplay_timer.cancel();

                // Get instance of level handler
                LevelHandler level_handler = LevelHandler.get_instance();

                // Calculate score
                int score = time_remaining / 2;

                // Set variables in level passed controller
                LevelPassedController.level_score = score;
                LevelPassedController.previous_highscore = level_handler.get_score(current_level);
                LevelPassedController.completed_level = current_level;
                LevelPassedController.previous_max_level = level_handler.get_level();

                // Update and save data if not in debugging mode
                if (!(DebugHandler.get_instance().is_debug_mode())) {
                    update_data(score);
                }

                // Load level passed scene
                SceneHandler sceneHandler = SceneHandler.get_instance();
                sceneHandler.set_scene("LevelPassed");
            } catch (IOException e) {
                System.out.println("Attempt to load invalid fxml");
            }
        }
    }

    private void update_data(int score) {
        // Get access to the level handler
        LevelHandler level_handler = LevelHandler.get_instance();

        // Check if highscore, update in level handler if true
        if (score > level_handler.get_score(current_level)) {
            level_handler.set_score(current_level, score);
        }

        // If this is the current highest unlocked level, unlock next level
        if (current_level == level_handler.get_level()) {
            level_handler.set_level(current_level + 1);
        }

        // Save data
        DataHandler data_handler = DataHandler.get_instance();
        data_handler.save_data();
    }
}
