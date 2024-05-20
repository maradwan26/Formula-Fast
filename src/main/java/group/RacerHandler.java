package group;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXMLLoader;

/**
 * This class will handle anything with the racers in the game, including moving
 * the racers and adding racers to the game.
 * 
 * @author Gabriel Azhari
 * @author Ashton Franklin
 */

public class RacerHandler {
    /** Represents the racers in the game */
    private Racer[] racers = new Racer[4];

    /** Represents the chance for the player to move (move_chance * 10)% chance */
    private final int move_chance = 5;

    /** Represents how often the cars will attempt to move in milliseconds */
    private final long move_rate = 4000;

    /** Timer to control car movement */
    private Timer timer = new Timer();

    /**
     * A constructor for creating an instance of racer handler with no racers
     */
    public RacerHandler() {

    }

    /**
     * A constructor for creating an instance of racer handler with racers
     * 
     * @param racers The list of racers to be stored in the handler
     */
    public RacerHandler(Racer[] racers) {
        this.racers = racers;
    }

    /**
     * A method to start moving the racers
     */
    public void start_racers() {
        try {
            /** Creating an instance of the Random class */
            Random random_generator = new Random();

            /** Track each racers progress */
            int racer_progress[] = { 0, 0, 0 };

            /** Every 5 seconds loop through the racers to give them a chance to move */
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    for (int i = 0; i < racers.length; i++) {
                        /** Generating a random number from 1 to 10 */
                        int random_number = random_generator.nextInt((10 - 1) + 1) + 1;

                        /** The racer has a move_chance chance to move forward */
                        if (random_number <= move_chance) {
                            racers[i].move();
                            racer_progress[i]++;

                            // if racer has passed the finish line then stop racers and fail level
                            if (racer_progress[i] >= 9) {
                                timer.cancel();

                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Gameplay.fxml"));
                                    loader.load();
                                    GameplayController gameplay_controller = loader.getController();
                                    gameplay_controller.fail_level();
                                } catch (IOException e) {
                                    System.out.println("Failed to end level on racer win");
                                }
                            }
                        }
                    }
                }
            }, 1000, move_rate);
        } catch (Exception e) {
            System.out.println("Error while starting racers.");
            e.printStackTrace();
        }
    }

    /**
     * A method to add a racer to the game
     * 
     * @param new_racer the object of the racer to be added to the game
     */
    public void add_racer(Racer new_racer) {
        try {
            for (int i = 0; i < racers.length; i++) {
                if (racers[i] == null) {
                    racers[i] = new_racer;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error while adding racer.");
            e.printStackTrace();
        }
    }

    /**
     * A method to stop all racers
     */
    public void stop_racers() {
        timer.cancel();
    }

    /**
     * A getter method to get the racers in the game
     * @return A list of al racers being handled by the instance of RacerHandler
     */
    public Racer[] get_racers() {
        return this.racers;
    }
}