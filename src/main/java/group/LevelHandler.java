package group;

/**
 * A class to store the players currently unlocked level, scores for each level,
 * as well as the debug mode state
 * 
 * @author Que Hung Dang
 */
public class LevelHandler {
    private int current_level;
    private int scores[];

    private static LevelHandler instance;

    /**
     * Constructor to initialize the level variable and the scores array the store
     * the 6 level of the game. also set the debug mode as false
     */
    private LevelHandler() {
        current_level = 1;
        scores = new int[6];
    }

    /**
     * Method to access the data handler singleton, and create an instance of none
     * exists
     * 
     * @return the DataHandler instance
     */
    public static LevelHandler get_instance() {
        /**
         * Represents the single instance of datahandler to exist
         */
        if (instance == null) {
            instance = new LevelHandler();
        }
        return instance;
    }

    /**
     * Constructor to return the current level of the game
     * 
     * @return the current level
     */
    public int get_level() {
        return current_level;
    }

    /**
     * Constructor to return the score of the current level
     * 
     * @param level int variable to get the current level from 1 - 6 to know which
     *              one to return the correct scores for the corressponding level.
     * @return the scores of the given level (minus one due to array initialize at
     *         0)
     */
    public int get_score(int level) {
        return scores[level - 1];
    }

    /**
     * Constructor to set the current level of the game
     * 
     * @param level int variable to take in the level that want to be setted for the
     *              current level.
     */
    public void set_level(int level) {
        current_level = level;
    }

    /**
     * Constructor to set the score of the game at the current level
     * 
     * @param level to take in int variable the level that want to the score to be
     *              setted to
     * @param score to take in int variable the scores is recorded for that current
     *              level
     */
    public void set_score(int level, int score) {
        scores[level - 1] = score;
    }

    /**
     * A method to clear data from the instance of level handler
     */
    public void clear() {
        current_level = 1;
        scores = new int[6];
    }

}