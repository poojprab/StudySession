package cs3500.pa02.view;

import cs3500.pa02.FlashCard;
import cs3500.pa02.StatsCreator;
import java.io.IOException;

/**
 * Interface that represents teh View in MVC, controls computer output
 */
public interface View {
  /**
   * Greets the user
   */
  void displayGreeting() throws IOException;

  /**
   * Asks the user how many questions they want to study
   */
  void howManyQuestions() throws IOException;

  /**
   * Displays the question to the user
   *
   * @param fc A flashcard representing the question to be asked
   */
  void displayQuestion(FlashCard fc) throws IOException;

  /**
   * Tells the user how to respond to the given output
   */
  void displayInstructions() throws IOException;

  /**
   * Displays the answer to the user
   *
   * @param fc A flashcard representing the answer
   */
  void displayAnswer(FlashCard fc) throws IOException;

  /**
   * Displays the fields of the stats creator at the ending screen
   *
   * @param statsCreator A StatsCreator object that will calculate the stats
   */
  void displayStatus(StatsCreator statsCreator) throws IOException;
}
