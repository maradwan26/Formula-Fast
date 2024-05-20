import static org.junit.jupiter.api.Assertions.*;

import group.Racer;
import group.RacerHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import org.junit.jupiter.api.*;

/**
 * Testing class to run JUnit tests on RacerHandler methods.
 * 
 * @author Gabriel Azhari
 */
public class TestRacerHandler {
    /** Sample sprite for testing */
    @FXML
    private ImageView test_sprite;

    /**
     * Method to test the constructor method of the RacerHandler class and make sure it does not return null.
     */
    @Test
    void test_get_instance() {
        RacerHandler instance = new RacerHandler();
        assertNotEquals(null, instance);
    }

    /**
     * Method to test the start_racers method of the RacerHandler class and make sure it starts the racers correctly.
     */
    @Test 
    void test_start_racers() {
        // Creating an instance of RacerHandler and creating racers using the test sprite
        RacerHandler instance = new RacerHandler();
        Racer test_racer_1 = new Racer(test_sprite);
        Racer test_racer_2 = new Racer(test_sprite);
        Racer test_racer_3 = new Racer(test_sprite);

        // Adding racers to the racer list and starting the racers, and testing if an exception/error was thrown
        instance.add_racer(test_racer_1);
        instance.add_racer(test_racer_2);
        instance.add_racer(test_racer_3);

        // Starting the racers and testing if an exception/error was thrown
        instance.start_racers();
    }

    /**
     * Method to test the add_racer method of the RacerHandler class and make sure it adds new racers correctly.
     */
    @Test
    void test_add_racer() {
        // Creating a racer using the test sprite
        RacerHandler instance = new RacerHandler();
        Racer test_racer = new Racer(test_sprite);

        // Adding a racer to the racer list and checking if the racer was properly added
        instance.add_racer(test_racer);
        assertEquals(test_racer,instance.get_racers()[0]);
    }

    /**
     * Method to test the stop_racer method of the RacerHandler class and make sure it stops the racers correctly.
     */
    @Test
    void test_stop_racer() {
        // Creating an instance of RacerHandler and creating racers using the test sprite
        RacerHandler instance = new RacerHandler();
        Racer test_racer_1 = new Racer(test_sprite);
        Racer test_racer_2 = new Racer(test_sprite);
        Racer test_racer_3 = new Racer(test_sprite);

        // Adding the racers to the racer list
        instance.add_racer(test_racer_1);
        instance.add_racer(test_racer_2);
        instance.add_racer(test_racer_3);

        // Starting the racers and stopping the racers, and testing if an exception/error was thrown
        instance.start_racers();
        instance.stop_racers();
    }

    /**
     * Method to test the get_racers method of the RacerHandler class and make sure it gets the racers correctly.
     */
    @Test
    void test_get_racers() {
        // Creating a racer using the test sprite
        RacerHandler instance = new RacerHandler();
        Racer test_racer = new Racer(test_sprite);

        // Adding a racer to the racer list and checking if the getter method can  get the racer from the list
        instance.add_racer(test_racer);
        assertEquals(test_racer,instance.get_racers()[0]);
    }
}
