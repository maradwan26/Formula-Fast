import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import group.AccountHandler;
import org.junit.jupiter.api.*;

import com.opencsv.CSVWriter;


/**
 * Testing class to run JUnit tests on AccountHandler methods.
 * 
 * @author Gabriel Azhari
 */
public class TestAccountHandler {
    /**
     * Sample record for use in testing
     */
    private static String[] sample_record_one;

    /**
     * A method ran before any tests to create a testing data file
     */
    @BeforeEach
    void setup_test_data(){
        //Access data file
        File data_file = new File("Data.csv");

        //Create data file
        if (!data_file.exists()){
            try{
                data_file.createNewFile();
            } catch (IOException e){
                System.out.println("Error creating test data");
            }
        }

        //Create sample data
        List<String[]> sample_data = new ArrayList<>();

        sample_record_one = new String[]{"testuserone", "testpasswordone", "3", "3", "12", "45", "50", "0", "0", "0"};

        sample_data.add(sample_record_one);

        //Write sample data to data file
        try{
            FileWriter file_writer = new FileWriter(data_file);
            CSVWriter csv_writer = new CSVWriter(file_writer);
            csv_writer.writeAll(sample_data);
            csv_writer.close();
        } catch (IOException e){
            System.out.println("IOException creating test data");
        }
        
    }

    @AfterEach
    void remove_test_data(){
        File data_file = new File("Data.csv");
        if (data_file.exists()){
            data_file.delete();
        }
    }

    /**
     * Method to test the getInstance method of the AccountHandler class and make sure it does not return null.
     */
    @Test
    void test_get_instance() {
        AccountHandler instance = AccountHandler.get_instance();
        assertNotEquals(null, instance);
    }

    /**
     * Method to test the login method of the AccountHandler class and make sure it returns true.
     */
    @Test
    void test_login() {
        AccountHandler instance = AccountHandler.get_instance();
        assertTrue(instance.login("testuserone","testpasswordone"));
    }

    /**
     * Method to test the create_account method of the AccountHandler class and make sure it returns true.
     */
    @Test
    void test_create_account() {
        AccountHandler instance = AccountHandler.get_instance();
        assertTrue(instance.create_account("testusernew","testpasswordnew"));
    }

    /**
     * Method to test the get_current_user method of the AccountHandler class and make sure it returns the appropriate username.
     */
    @Test
    void test_get_current_user() {
        AccountHandler instance = AccountHandler.get_instance();
        instance.set_current_user("testuserone");
        assertEquals("testuserone", instance.get_current_user());
    }

    /**
     * Method to test the get_user_password method of the AccountHandler class and make sure it returns the appropriate password.
     */
    @Test
    void test_get_user_password() {
        AccountHandler instance = AccountHandler.get_instance();
        instance.set_user_password("testpasswordone");
        assertEquals("testpasswordone", instance.get_user_password());
    }

     /**
     * Method to test the set_current_user method of the AccountHandler class and make sure it sets the username correctly.
     */
    @Test
    void test_set_current_user() {
        AccountHandler instance = AccountHandler.get_instance();
        instance.set_current_user("randomusername");
        assertEquals("randomusername", instance.get_current_user());
    }

    /**
     * Method to test the set_user_password method of the AccountHandler class and make sure it sets the password correctly.
     */
    @Test
    void test_set_user_password() {
        AccountHandler instance = AccountHandler.get_instance();
        instance.set_user_password("randompassword");
        assertEquals("randompassword", instance.get_user_password());
    }
}