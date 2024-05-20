package group;

import java.io.IOException;

/**
 * @author Mahmoud Radwan
 *         This class handles the ui interactions and loading elements for the
 *         level failed page
 */

public class LevelFailedController {

    /**
     * This method sends the user back to menu after clicking the continue
     * button. It uses the sceneHandler to set the next scene which is the
     * Main Menu.
     */
    public void continue_clicked() {
        try {
            SceneHandler sceneHandler = SceneHandler.get_instance();
            sceneHandler.set_scene("Menu");
        } catch (IOException e) {
            System.out.println("Attempt to load invalid fxml");
        }
    }

}
