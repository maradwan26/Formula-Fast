import org.junit.jupiter.api.Test;
import group.SceneHandler;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class to run JUnit tests on SceneHandler methods
 * @author Matthew Marbina
 */
public class TestSceneHandler {

    /**
     * Test of get_instance() method, of class SceneHandler
     */
    @Test
    public void test_get_instance() {
        SceneHandler instance = SceneHandler.get_instance();
        assertNotEquals(null, instance);
    }

    /**
     * Test of get_current_scene() method, of class SceneHandler
     */
    @Test
    public void test_get_current_scene() {
        SceneHandler instance = SceneHandler.get_instance();
        String exp_result = "Login";
        String result = instance.get_current_scene();
        assertEquals(exp_result, result);
    }
}
