package group;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * This core class handles all interaction with the Data.csv file including,
 * loading account data to level handler, creating new accounts and loading
 * created data, saving data for
 * current user, accessing the data of a specific user, and getting a list of
 * all user records sorted by total score.
 * 
 * @author Ashton Franklin
 */

public class DataHandler {
    /**
     * Represents the single instance of datahandler to exist
     */
    private static DataHandler instance;

    /**
     * Empty constructor for creating the instance of data handler
     */
    private DataHandler() {
    }

    /**
     * Method to access the data handler singleton, and create an instance of none
     * exists
     * 
     * @return the DataHandler instance
     */
    public static DataHandler get_instance() {
        if (instance == null) {
            instance = new DataHandler();
        }

        return instance;
    }

    /**
     * Helper method to get the data file for the project, and create it if it
     * doesn't exist
     * 
     * @return A file object for the data file
     */
    private File get_data_file() {
        // Check if data file exists, if not create it.
        File data_file = new File("Data.csv"); 

        if (!data_file.exists()) {
            try {
                data_file.createNewFile();
            } catch (IOException e) {
                System.out.println("load_account IO Error");
                e.printStackTrace();
            }
        }

        return data_file;
    }

    /**
     * Method to load the account data of a given user into the level handler
     * 
     * @param username the name of the user to load data for
     * @return An array of strings containing the data for the account loaded
     */
    public String[] load_account(String username) {
        try {
            // Find the data file
            File data_file = get_data_file();

            // Create csv reader for data file
            FileReader file_reader = new FileReader(data_file);
            CSVReader csv_reader = new CSVReader(file_reader);

            // Search records in data file for user
            String[] current_record;
            while ((current_record = csv_reader.readNext()) != null) {
                if (current_record[0].equals(username)) {
                    // Load users data into the Level Handler
                    LevelHandler level_handler = LevelHandler.get_instance();
                    level_handler.set_level(Integer.parseInt(current_record[2]));
                    for (int i = 1; i < 7; i++) {
                        level_handler.set_score(i, Integer.parseInt(current_record[i + 3]));
                    }

                    break;
                }
            }

            // Close csv reader
            csv_reader.close();

            return current_record;
        } catch (Exception e) {
            System.out.println("load_account error.");
            e.printStackTrace();

            return null;
        }
    }

    /**
     * Method to create a new account and load created data into the level handler
     * 
     * @param username Username to give to the new account
     * @param password Password to give to the new account
     * @return An array of strings containing the data of the account created
     */
    public String[] create_account(String username, String password) {
        try {
            // Fine the data file
            File data_file = get_data_file();

            // Create CSVWriter for data file
            FileWriter file_writer = new FileWriter(data_file, true);
            CSVWriter writer = new CSVWriter(file_writer);

            // Create new data record and write to data file
            String[] new_data = { username, password, "1", "0", "0", "0", "0", "0", "0", "0" };
            writer.writeNext(new_data);

            // Close csv writer
            writer.close();

            //// Load users data into the Level Handler
            LevelHandler level_handler = LevelHandler.get_instance();
            level_handler.set_level(1);
            for (int i = 1; i < 7; i++) {
                level_handler.set_score(i, 0);
            }

            return new_data;
        } catch (Exception e) {
            System.out.println("create_account error.");
            e.printStackTrace();

            return null;
        }
    }

    /**
     * Saves the data currently loaded into the level handler, to the currently
     * logged in account
     * 
     * @return True if the data was saved successfully, false otherwise
     */
    public boolean save_data() {
        // Exit method without saving if debug mode is enabled
        DebugHandler debug = DebugHandler.get_instance();
        if (debug.is_debug_mode()) {
            return false;
        }

        try {
            // Get the instance of Account Handler class
            AccountHandler account_handler = AccountHandler.get_instance();

            // Find the data file
            File data_file = get_data_file();

            // Create list to store data temporarily
            List<String[]> temp_data_container = new ArrayList<>();

            // Create csv reader for data file
            FileReader file_reader = new FileReader(data_file);
            CSVReader csv_reader = new CSVReader(file_reader);

            // Iterate over all records in data
            String[] current_record;
            while ((current_record = csv_reader.readNext()) != null) {
                if (!current_record[0].equals(account_handler.get_current_user())) {
                    // Add data for all users EXCEPT for the currently logged in user to the
                    // temp_data
                    temp_data_container.add(current_record);
                }
            }

            // Close csv reader
            csv_reader.close();

            // Access the level handler instance
            LevelHandler level_handler = LevelHandler.get_instance();

            // Calculate the total score from the level handler
            int total_score = 0;
            for (int i = 1; i < 7; i++) {
                total_score += level_handler.get_score(i);
            }

            // Generate a data record
            String[] current_user_data = { account_handler.get_current_user(), account_handler.get_user_password(),
                    Integer.toString(level_handler.get_level()), Integer.toString(total_score),
                    Integer.toString(level_handler.get_score(1)), Integer.toString(level_handler.get_score(2)),
                    Integer.toString(level_handler.get_score(3)), Integer.toString(level_handler.get_score(4)),
                    Integer.toString(level_handler.get_score(5)), Integer.toString(level_handler.get_score(6)) };
            temp_data_container.add(current_user_data);

            // Create a csv writer for data file
            FileWriter file_writer = new FileWriter(data_file);
            CSVWriter csv_writer = new CSVWriter(file_writer);

            // Write temp_data_container to the csv
            csv_writer.writeAll(temp_data_container);

            // Close the csv writer
            csv_writer.close();

            return true;
        } catch (Exception e) {
            System.out.println("Error in save_data");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the data for a given user from the data file
     * 
     * @param username Username of the user to get data of
     * @return An array containing all data for the user
     */
    public String[] get_data_from_user(String username) {
        try {
            // Get data file
            File data_file = get_data_file();

            // Create csv reader for data file
            FileReader file_reader = new FileReader(data_file);
            CSVReader csv_reader = new CSVReader(file_reader);

            // Search records in data file for user
            String[] current_record;
            while ((current_record = csv_reader.readNext()) != null) {
                if (current_record[0].equals(username)) {
                    csv_reader.close();
                    return current_record;
                }
            }

            // Close csv reader
            csv_reader.close();
        } catch (Exception e) {
            System.out.println("get_data_from_user error.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the data of all users sorted by their total score
     * 
     * @return An array list containing the records for each players data, sorted in
     *         descending order.
     */
    public List<String[]> get_sorted_data() {
        try {
            // Get data file
            File data_file = get_data_file();

            // Create csv reader for data file
            FileReader file_reader = new FileReader(data_file);
            CSVReader csv_reader = new CSVReader(file_reader);

            // Read all data from file
            List<String[]> data = csv_reader.readAll();

            // Close csv reader
            csv_reader.close();

            // Sort records based on total score
            Collections.sort(data, new Comparator<String[]>() {
                public int compare(String[] record, String[] other_record) {
                    return -(Integer.valueOf(record[3]).compareTo(Integer.valueOf(other_record[3])));
                }
            });

            return data;
        } catch (Exception e) {
            System.out.println("get_sorted_data error: " + e);
        }

        return null;
    }
}
