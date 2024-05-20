
import static org.junit.jupiter.api.Assertions.*;
import group.QuestionHandler;

import org.junit.jupiter.api.*;
/**
 * Testing class to run JUnit tests on QuestionHandler methods.
 * 
 * @author Que Hung Dang
 */
public class TestQuestionHandler {

    /**
     * Verifies the singleton instance creation of {@link QuestionHandler} by ensuring the instance
     * is not null. This test guarantees that the {@link QuestionHandler} can be successfully instantiated
     * and accessed.
     */
    @Test
    void test_get_instance(){
        QuestionHandler instance = QuestionHandler.get_instance();
        assertNotEquals(null, instance);
    }
    
    /**
     * Tests the generation of a new question for level 1, asserting that the generated question
     * is not null. This ensures the question generation mechanism is operational for level 1.
     */
    @Test 
    void test_get_level_1(){
        QuestionHandler instance = QuestionHandler.get_instance();
        assertNotEquals(null,instance.generate_new_question(1));
        
    }
    /**
     * Tests the generation of a new question for level 2, asserting that the generated question
     * is not null. This ensures the question generation mechanism is operational for level 1.
     */
    @Test 
    void test_get_level_2(){
        QuestionHandler instance = QuestionHandler.get_instance();
        assertNotEquals(null,instance.generate_new_question(2));
        
    }

    /**
     * Tests the generation of a new question for level 3, asserting that the generated question
     * is not null. This ensures the question generation mechanism is operational for level 1.
     */
   
    @Test 
    void test_get_level_3(){
        QuestionHandler instance = QuestionHandler.get_instance();
        assertNotEquals(null,instance.generate_new_question(3));
        
    }
    /**
     * Tests the generation of a new question for level 4, asserting that the generated question
     * is not null. This ensures the question generation mechanism is operational for level 1.
     */
    @Test
    void test_get_level_4(){
        QuestionHandler instance = QuestionHandler.get_instance();
        assertNotEquals(null,instance.generate_new_question(4));
        
    }
    /**
     * Tests the generation of a new question for level 5, asserting that the generated question
     * is not null. This ensures the question generation mechanism is operational for level 1.
     */
    @Test
    void test_get_level_5(){
        QuestionHandler instance = QuestionHandler.get_instance();
        assertNotEquals(null,instance.generate_new_question(5));
        
    }

    /**
     * Tests the generation of a new question for level 6, asserting that the generated question
     * is not null. This ensures the question generation mechanism is operational for level 1.
     */
   
    @Test
    void test_get_level_6(){
        QuestionHandler instance = QuestionHandler.get_instance();
        assertNotEquals(null,instance.generate_new_question(6));
        
    }
    
}
