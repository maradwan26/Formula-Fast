package group;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

/**
 * A class representing a computer controller racer in a level
 * 
 * @author Ashton Franklin
 */
public class Racer {
    /** The sprite for this racer */
    private ImageView sprite;

    /**
     * Constructor method for a new racer object
     * 
     * @param sprite Accepts an ImageView object of the sprite for this racer
     */
    public Racer(ImageView sprite) {
        this.sprite = sprite;
    }

    /**
     * A method to move the racer a pre determined about across the screen
     */
    public void move() {
        // Move sprite across screen
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(this.sprite);
        translate.setByX(100);
        translate.play();
    }
}
