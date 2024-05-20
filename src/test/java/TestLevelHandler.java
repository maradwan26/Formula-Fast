import static org.junit.jupiter.api.Assertions.*;

import group.LevelHandler;
import org.junit.jupiter.api.*;

/**
 * Testing class to run JUnit tests on LevelHandler methods.
 * @author Mahmoud Radwan
 */

class TestLevelHandler {

    /**
     * Tests that the singleton instance of LevelHandler is not null.
     */
    @Test
    void get_instance() {
        LevelHandler instance = LevelHandler.get_instance();
        assertNotEquals(null, instance);
    }

    /**
     * Tests the ability to set and retrieve the current level in LevelHandler.
     */
    @Test
    void get_level() {
        LevelHandler instance = LevelHandler.get_instance();
        instance.set_level(1);
        assertEquals(1, instance.get_level());
    }

    /**
     * Tests the ability to set and retrieve a score for a specific level.
     */
    @Test
    void get_score() {
        LevelHandler instance = LevelHandler.get_instance();
        instance.set_score(4, 500);
        assertEquals(500, instance.get_score(4));
    }

    /**
     * Tests the ability to set a specific level and then retrieve it to ensure the value is correctly updated.
     */
    @Test
    void set_level() {
        LevelHandler instance = LevelHandler.get_instance();
        instance.set_level(6);
        assertEquals(6, instance.get_level());
    }

    /**
     * Tests setting a score for a specific level and retrieving it to ensure the score is stored correctly.
     */
    @Test
    void set_score() {
        // would this not be the same as get_score or am i tweeaking??? same for
        LevelHandler instance = LevelHandler.get_instance();
        instance.set_score(2, 250);
        assertEquals(250, instance.get_score(2));
    }
}