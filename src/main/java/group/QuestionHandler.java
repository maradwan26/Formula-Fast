package group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Handles the management of questions for a game, including generating new
 * questions based on the level,
 * and validating answers. This class utilizes singleton design pattern to
 * ensure only one instance is used
 * throughout the application.
 *
 * @author Que Hung Dang
 * @author Ashton Franklin
 */

public class QuestionHandler {

    private static QuestionHandler instance;
    private Question curr_question;
    private String line;
    private String cvsSplitBy = ",";
    private List<Question> questions = new ArrayList<>();

    /**
     * Gets the single instance of the QuestionHandler class.
     *
     * @return The single instance of QuestionHandler.
     */
    public static QuestionHandler get_instance() {
        if (instance == null) {
            instance = new QuestionHandler();
        }

        return instance;
    }

    /**
     * Generates a new question based on the specified level. The questions are read
     * from a CSV file corresponding
     * to the level. It randomly selects a question from the list for levels 1, 2,
     * 4, and 5. For levels 3 and 6, it
     * counts the number of questions in the file, selects a random question, and
     * then re-reads the file to find
     * the question at the random line.
     *
     * @param level The level for which a new question needs to be generated.
     * @return A string representing the question prompt, or an error message if the
     *         operation fails.
     */
    public String generate_new_question(int level) {
        questions.clear();

        // Load csv file for correct level
        InputStream csvFile;
        switch (level) {
            case 1:
                csvFile = QuestionHandler.class.getResourceAsStream("level1.csv");
                break;
            case 2:
                csvFile = QuestionHandler.class.getResourceAsStream("level2.csv");
                break;
            case 3:
                csvFile = QuestionHandler.class.getResourceAsStream("level3.csv");
                break;
            case 4:
                csvFile = QuestionHandler.class.getResourceAsStream("level4.csv");
                break;
            case 5:
                csvFile = QuestionHandler.class.getResourceAsStream("level5.csv");
                break;
            case 6:
                csvFile = QuestionHandler.class.getResourceAsStream("level6.csv");
                break;
            default:
                csvFile = QuestionHandler.class.getResourceAsStream("level1.csv");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile))) {
            questions.clear();
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {

                // Use comma as separator
                String[] data = line.split(cvsSplitBy);
                String questionText = null;
                String answerText = null;

                // Format the question based on the operands read from the CSV
                String operand = null;

                switch (level){
                    case 1:
                        questionText = data[0] + " + " + data[1]; // Setup question string for addition
                        answerText = data[2]; // Answer is taken directly from the CSV without modification
                        break;
                    case 2:
                        questionText = data[0] + " - " + data[1]; // Setup question string for subtraction
                        answerText = data[2]; // Answer is taken directly from the CSV without modification
                        break;
                    case 3:
                        if (data[0].equals("A")){
                            operand = " + ";
                        } else {
                            operand = " - ";
                        }

                        questionText = data[1] + operand + data[2]; // Setup question string for multiplication
                        answerText = data[3]; // Answer is taken directly from the CSV without modification
                        break;
                    case 4:
                        questionText = data[0] + " x " + data[1]; // Setup question string for multiplication
                        answerText = data[2]; // Answer is taken directly from the CSV without modification
                        break;
                    case 5:
                        questionText = data[0] + " ÷ " + data[1]; // Setup question string for division
                        answerText = data[2]; // Answer is taken directly from the CSV without modification
                        break;
                    case 6:
                        if (data[0].equals("A")){
                            operand = " + ";
                        } else if(data[0].equals("S")) {
                            operand = " - ";
                        } else if(data[0].equals("M")) {
                            operand = " x ";
                        } else {
                            operand = " ÷ ";
                        }

                        questionText = data[1] + operand + data[2]; // Setup question string for multiplication
                        answerText = data[3]; // Answer is taken directly from the CSV without modification
                        break;
                }

                // Add to list
                questions.add(new Question(questionText, answerText));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to load questions.";
        }

        // Check if there are any questions to select from
        if (!questions.isEmpty()) {
            // Randomly select a question
            Random rand = new Random();
            curr_question = questions.get(rand.nextInt(questions.size()));
            // Assuming you want to return the prompt for the question here
            return curr_question.get_prompt() + " = ?";
        } else {
            return "No questions available.";
        }
    }

    /**
     * Validates the given answer against the current question's answer.
     *
     * @param answer The answer provided by the user.
     * @return true if the answer is correct, false otherwise
     */
    public boolean validate_answer(String answer) {
        if (curr_question == null)
            return false;
        if (answer.equals(curr_question.get_answer())) {
            return true;
        }
        return false;
    }

}
