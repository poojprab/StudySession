package cs3500.pa02;

/**
 * class that represents a single flash card
 */
public class FlashCard {

  /**
   * Question of the flashcard
   */
  private final String question;

  /**
   * Answer of the flashcard
   */
  private final String answer;

  /**
   * Difficulty of the flashcard
   */
  private AnswerType difficulty;

  /**
   * Constructor for FlashCard
   *
   * @param question String representing the question
   * @param answer String representing the answer
   * @param difficulty String representing the difficulty
   */
  public FlashCard(String question, String answer, String difficulty) {
    this.question = question;
    this.answer = answer;
    if (difficulty.equalsIgnoreCase("hard")
        || difficulty.equalsIgnoreCase("easy")) {
      this.difficulty = AnswerType.valueOf(difficulty.toUpperCase());
    } else {
      throw new IllegalArgumentException("invalid sort type");
    }
  }

  /**
   * Modifies the difficulty of a single card
   *
   * @param difficulty difficulty to be modified
   */
  public void setDifficulty(String difficulty) {
    if (difficulty.equalsIgnoreCase("hard")
        || difficulty.equalsIgnoreCase("easy")) {
      this.difficulty = AnswerType.valueOf(difficulty.toUpperCase());
    } else {
      throw new IllegalArgumentException("invalid sort type");
    }
  }

  /**
   * Getter method for a question
   *
   * @return A string representing a question
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * Getter method for an aanswer
   *
   * @return A string representing an answer
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Getter method for a difficulty
   *
   * @return A difficulty enum representing a difficulty
   */
  public AnswerType getDifficulty() {
    return this.difficulty;
  }
}
