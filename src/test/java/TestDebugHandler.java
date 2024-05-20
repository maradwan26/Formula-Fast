import static org.junit.jupiter.api.Assertions.*;

import group.DebugHandler;

import org.junit.jupiter.api.*;


/**
 * Test class for DebugHandler. It ensures that the DebugHandler singleton works as expected,
 * including instance creation, default debug mode state, and the ability to change the debug mode.
 * 
 * @author Que Hung Dang
 */

public class TestDebugHandler {


    /**
     * Sets up the testing environment before each test case. Specifically, it resets the debug mode
     * to false to ensure a consistent starting point for all tests.
     */
    
    @BeforeEach
    void set_up() {
        // Reset Debug mode to false before each test
        DebugHandler.get_instance().set_debug_mode(false);
    }

    
    /**
     * Tests that the {@link DebugHandler} instance is not null, ensuring that the singleton instance
     * is successfully created.
     */
    @Test
    void test_get_instance(){
        DebugHandler instance = DebugHandler.get_instance();
        assertNotEquals(null, instance);
    }
    /**
     * Validates that the default state of the debug mode is false. This test ensures that upon
     * creation or reset, the debug mode is not enabled unless explicitly set.
     */
    
    @Test
    void test_begin_mode(){
        DebugHandler instance = DebugHandler.get_instance();
    
        assertEquals(false, instance.is_debug_mode());
    }
    /**
     * Confirms that the debug mode can be set to false explicitly and verifies the state afterward.
     * This test ensures the {@link DebugHandler} accurately reflects changes to the debug mode.
     */
    @Test
    void test_set_false(){
        DebugHandler instance = DebugHandler.get_instance();
        instance.set_debug_mode(false);
        assertEquals(false, instance.is_debug_mode());
    }
    
    /**
     * Tests the ability to enable the debug mode and checks its state afterward. This test case
     * is crucial for validating that the {@link DebugHandler} can toggle the debug mode on and reflect
     * this change accurately.
     */

    @Test
    void test_set_true(){
        DebugHandler instance = DebugHandler.get_instance();
        instance.set_debug_mode(true);
        assertEquals(true, instance.is_debug_mode());
    }


    
    
}
