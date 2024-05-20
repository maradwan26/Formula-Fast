import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.opencsv.CSVWriter;

import group.DataHandler;

/**
 * Testing class to run JUnit tests on DataHandler methods
 * @author Ashton Franklin
 */
public class TestDataHandler {
    /**
     * Sample records for use in testing
     */
    private static String[] sample_record_one;
    private static String[] sample_record_two;
    private static String[] sample_record_three;

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

        sample_record_one = new String[]{"testuserone", "password", "4", "99", "33", "33", "33", "0", "0", "0"};
        sample_record_two = new String[]{"testusertwo", "password", "6", "120", "20", "20", "20", "20", "20", "0"};
        sample_record_three = new String[]{"testuserthree", "password", "1", "25", "0", "0", "0", "0", "0", "0"};

        sample_data.add(sample_record_one);
        sample_data.add(sample_record_two);
        sample_data.add(sample_record_three);

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
     * Method to test the get_instance method of data handler and make sure it does not return null
     */
    @Test
    void test_get_instance(){
        DataHandler instance = DataHandler.get_instance();
        assertNotEquals(null, instance);
    }

    @Test
   void test_load_account(){
        DataHandler instance = DataHandler.get_instance();
        String[] loaded_data = instance.load_account("testuserone");
        assertArrayEquals(sample_record_one, loaded_data);
    }

    @Test
    void test_create_account(){
        DataHandler instance = DataHandler.get_instance();
        String[] loaded_data = instance.create_account("test_username", "test_password");
        String[] new_record = {"test_username", "test_password", "1", "0", "0", "0", "0", "0", "0", "0"};
        assertArrayEquals(new_record, loaded_data);
    }

    @Test
    void test_save_data(){
        DataHandler instance = DataHandler.get_instance();
        boolean save_result = instance.save_data();
        assertEquals(true, save_result);
    }

    @Test
    void test_get_data_from_user(){
        DataHandler instance = DataHandler.get_instance();
        String[] retrieved_data = instance.get_data_from_user(sample_record_two[0]);
        assertArrayEquals(sample_record_two, retrieved_data);
    }

    @Test
    void test_get_sorted_data(){
        DataHandler instance = DataHandler.get_instance();
        List<String[]> sorted_data = instance.get_sorted_data();
        
        List<String[]> sorted_samples = new ArrayList<>();
        sorted_samples.add(sample_record_two);
        sorted_samples.add(sample_record_one);
        sorted_samples.add(sample_record_three);

        for (String[] record : sorted_data){
            for (String element : record){
                System.out.print(element + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < (sorted_data.size()); i++){
            assertArrayEquals(sorted_samples.get(i), sorted_data.get(i));
        }
    }
}
