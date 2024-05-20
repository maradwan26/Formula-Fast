package group;

import java.io.IOException;

/**
 * This class handles anything to do with the user's account, including logging
 * into an account, creating a new account, or checking
 * which user is currently logged into the system.
 * 
 * @author Gabriel Azhari
 */

public class AccountHandler {
    /** Represents the single instance of AccountHandler to exist */
    private static AccountHandler instance;
    /** Represents who the current user is */
    private String current_user;
    /** Represents the password of the current user */
    private String user_password;

    /**
     * An empty constructor for creating the instance of AccountHandler
     */
    private AccountHandler() {
    }

    /**
     * A method to access the AccountHandler singleton and create an instance if
     * none exists
     * 
     * @return the AccountHandler instance
     */
    public static AccountHandler get_instance() {
        if (instance == null) {
            instance = new AccountHandler();
        }

        return instance;
    }

    /**
     * A method to let the user login to their account
     * 
     * @param username the username of the account trying to login
     * @param password the password of the account trying to login
     * @return a boolean value representing whether or not the login was successful
     */
    public boolean login(String username, String password) {
        DataHandler data_handler = DataHandler.get_instance();
        String[] user_data = data_handler.get_data_from_user(username);
        try {
            // Checking if the username and password match an account in the database
            if ((user_data != null) && (user_data[1].equals(password))) {
                // Setting the current user and their password, and creating their account
                set_current_user(username);
                set_user_password(password);
                data_handler.load_account(username);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error while logging in.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * A method to let the user create an account
     * 
     * @param username the username to give to the new account
     * @param password the password to give to the new account
     * @return a boolean value representing whether or not the account creation was
     *         successful
     */
    public boolean create_account(String username, String password) {
        DataHandler data_handler = DataHandler.get_instance();
        try {
            // Checking if the username has already been taken
            if (data_handler.get_data_from_user(username) == null) {
                // Setting the current user and their password, and creating their account
                current_user = username;
                user_password = password;
                data_handler.create_account(username, password);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error while creating account.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * A getter method to get the current user
     * 
     * @return a string representing who the current user is
     */
    public String get_current_user() {
        return current_user;
    }

    /**
     * A getter method to get the current user's password
     * 
     * @return a string representing the password of the current user
     */
    public String get_user_password() {
        return user_password;
    }

    /**
     * A setter method to get the current user
     * @param username The username to be set as current user
     */
    public void set_current_user(String username) {
        current_user = username;
    }

    /**
     * A setter method to get the current user's password
     * @param password The password to be set as current password
     */
    public void set_user_password(String password) {
        user_password = password;
    }

    /**
     * A method to log the current user out of the system
     */
    public void logout() {
        try {
            // Clear data from account handler
            current_user = null;
            user_password = null;

            // Clear data from level handler
            LevelHandler level_handler = LevelHandler.get_instance();
            level_handler.clear();

            // Send user back to login screen
            SceneHandler scene_handler = SceneHandler.get_instance();
            scene_handler.set_scene("Login");
        } catch (IOException e) {
            System.out.println("Error logging out of system.");
        }
    }
}