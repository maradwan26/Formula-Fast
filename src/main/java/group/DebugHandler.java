package group;

/**
 * The {@code DebugHandler} class manages a debug mode in an application. It
 * follows the singleton design pattern
 * to ensure that only one instance of the debug handler is created and used
 * throughout the application.
 *
 * @author Que Hung Dang
 */
public class DebugHandler {
    /**
     * Represents if debug mode is enabled or not
     */
    private boolean mode;

    /**
     * Single instancce of the DebugHandler
     */
    private static DebugHandler instance;

    /**
     * Private constructor to prevent instantiation outside of the
     * {@code getInstance()} method.
     * Initializes the debug mode to {@code false}.
     */
    private DebugHandler() {
        mode = false;
    }

    /**
     * Retrieves the single instance of the DebugHandler class. If the instance does
     * not exist,
     * it creates one; otherwise, it returns the existing instance.
     *
     * @return The single instance of {@code DebugHandler}.
     */
    public static DebugHandler get_instance() {
        if (instance == null) {
            instance = new DebugHandler();
        }

        return instance;
    }

    /**
     * Sets the debug mode of the application.
     *
     * @param mode The new debug mode status to set. {@code true} to enable debug
     *             mode, {@code false} to disable it.
     */
    public void set_debug_mode(boolean mode) {
        this.mode = mode;
    }

    /**
     * Checks whether the debug mode is enabled.
     *
     * @return {@code true} if debug mode is enabled, {@code false} otherwise.
     */
    public boolean is_debug_mode() {
        return mode;
    }

}
