package group;

import java.io.IOException;

/**
 * A class to handle scene management within the software
 * 
 * @author Que Hung Dang
 * @author Ashton Franklin
 */
public class SceneHandler {
    /**
     * Represents the single instance of scene handler to exist
     */
    private static SceneHandler instance;

    /**
     * Represents the currently loaded scene, that initally is set to the Login
     * screen
     */
    private String current_scene = "Login";

    /**
     * Empty constructor to create new instance of scene handler
     */
    private SceneHandler() {
    }

    /**
     * Method to access the scene handler singleton, or create one if none exists
     * 
     * @return The instance of the scene handler class
     */
    public static SceneHandler get_instance() {
        if (instance == null) {
            instance = new SceneHandler();
        }

        return instance;
    }

    /**
     * Getter method to access the current scene
     * @return A string representing the currently loaded scene
     */
    public String get_current_scene() {
        return current_scene;
    }

    /**
     * Setter method to set the new scene, and load that scene to the screen
     * 
     * @param new_scene the name of the fxml to be set as the new scene
     * @throws IOException when the fxml file does not exist
     */
    public void set_scene(String new_scene) throws IOException {
        current_scene = new_scene;
        App.setRoot(new_scene);
    }

}
