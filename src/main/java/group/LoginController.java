package group;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * This class will be the controller for the FXML file that displays the login
 * screen. It will have three methods to handle the
 * different interactions, either the click of a button or a password being
 * entered, available through the login screen UI.
 * 
 * @author Matthew Marbina
 */
public class LoginController {
    @FXML
    private Text incorrect_instructor_password_label;
    @FXML
    private Text incorrect_login_password_label;
    @FXML
    private Text username_taken_label;
    @FXML
    private Text empty_field_label;

    /**
     * TextField instance variable username_field, generated from FXML file, used to
     * store the player's username as they login/create an account.
     */
    @FXML
    private TextField username_field;

    /**
     * PasswordField instance variable password_field, generated from FXML file,
     * used to store the player's password as they login/create an account.
     */
    @FXML
    private PasswordField password_field;

    /**
     * PasswordField instance variable instructor_mode_password, generated from FXML
     * file, used to store the instructor mode password if any instructor wants to
     * use
     * instructor mode.
     */
    @FXML
    private PasswordField instructor_mode_password;

    /**
     * This method binds the login button to the login method in the
     * AccountHandler.java class
     */
    public void login_clicked() {
        // Set previous messages non visible
        incorrect_instructor_password_label.setVisible(false);
        incorrect_login_password_label.setVisible(false);
        username_taken_label.setVisible(false);
        empty_field_label.setVisible(false);

        // Creating instance of AccountHandler named userAccount
        AccountHandler account_handler = AccountHandler.get_instance();
        SceneHandler scene_handler = SceneHandler.get_instance();

        // Creating String variable inputUsername that is equal to the username the
        // player inputs in the username text field
        String input_username = username_field.getText();

        // Creating String variable inputPassword that is equal to the password the
        // player inputs in the password password field
        String input_password = password_field.getText();
        boolean loggedIn = account_handler.login(input_username, input_password);

        // Checks if the input user account matches an account in the database given the
        // inputs after the login button is clicked, using the login
        // method from the AccountHandler.java class; If so, successfully logs user in
        // and sends them to main menu, otherwise, prompting them to
        // re-enter their credentials.
        try {
            if (loggedIn) {
                scene_handler.set_scene("Menu");
            } else {
                // incorrect password
                incorrect_login_password_label.setVisible(true);
                username_field.clear();
                password_field.clear();
            }
        } catch (IOException e) {
            System.out.println("Attempted to load invalid scene.");
            e.printStackTrace();
        }
    }

    /**
     * This method binds the create account button to the create_account method in
     * the AccountHandler.java class
     */
    public void create_account_clicked() {
        // Set previous messages non visible
        incorrect_instructor_password_label.setVisible(false);
        incorrect_login_password_label.setVisible(false);
        username_taken_label.setVisible(false);
        empty_field_label.setVisible(false);

        // Creating instance of AccountHandler named newAccount
        AccountHandler account_handler = AccountHandler.get_instance();
        SceneHandler scene_handler = SceneHandler.get_instance();

        // Creating String variable inputUsername that is equal to the username the
        // player inputs in the username text field
        String input_username = username_field.getText();

        // Creating String variable inputPassword that is equal to the password the
        // player inputs in the password password field
        String input_password = password_field.getText();

        // Checks if the user left either the username field or password field empty, if
        // so, tells user they cannot enter a black field and prompts them
        // to re-enter username & password.
        if (input_username.equals("") || input_password.equals("")) {
            empty_field_label.setVisible(true);
            username_field.clear();
            password_field.clear();
        } else {
            // Attempt to create new account with provided information
            boolean account_created = account_handler.create_account(input_username, input_password);

            // Check if account created properly, if so, load tutorial scene, otherwise,
            // tell user the username is already taken and prompts them to create
            // new username and/or password.
            try {
                if (account_created) {
                    scene_handler.set_scene("Tutorial");
                } else {
                    username_taken_label.setVisible(true);
                    username_field.clear();
                    password_field.clear();
                }
            } catch (IOException e) {
                System.out.println("Attempted to load invalid scene.");
                e.printStackTrace();
            }
        }
    }

    /**
     * This method verifies whether or not the password entered in the instructor
     * mode password field matches the secret code used to access instructor mode.
     */
    public void instructor_mode_entered() {
        // Set previous messages non visible
        incorrect_instructor_password_label.setVisible(false);
        incorrect_login_password_label.setVisible(false);
        username_taken_label.setVisible(false);
        empty_field_label.setVisible(false);

        // Creating String variable instructorModeCode that stores the secret password
        // instructors enter to use instructor mode
        String instructorModeCode = "password123";
        SceneHandler scene_handler = SceneHandler.get_instance();

        // Checks if the text entered in the instructor mode password field is equal to
        // the secret instructor mode password. If so, changes the scene to instructor
        // mode.
        try {
            if (instructor_mode_password.getText().equals(instructorModeCode)) {
                scene_handler.set_scene("InstructorMode");
            } else {
                instructor_mode_password.clear();
                incorrect_instructor_password_label.setVisible(true);
            }
        } catch (IOException e) {
            System.out.println("Attempted to load invalid scene.");
            e.printStackTrace();
        }
    }
}