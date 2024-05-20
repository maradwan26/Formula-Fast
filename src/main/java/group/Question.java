package group;

/**
 * Represents a question with a prompt and an answer. This class provides a
 * structure for storing
 * the information related to a single question including its text (prompt) and
 * the correct answer.
 * 
 * @author Que Hung Dang
 */
public class Question {
    private String prompt;
    private String answer;

    /**
     * Constructs a new Question object with specified prompt and answer.
     *
     * @param prompt The text of the question.
     * @param answer The answer to the question.
     */
    public Question(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
    }

    /**
     * Retrieves the prompt of the question.
     *
     * @return The prompt of the question.
     */
    public String get_prompt() {
        return prompt;
    }

    /**
     * Retrieves the answer to the question.
     *
     * @return The answer to the question.
     */
    public String get_answer() {
        return answer;
    }

}
