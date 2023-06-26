package cs3500.pa02.view;

import cs3500.pa02.FlashCard;
import cs3500.pa02.StatsCreator;
import java.io.IOException;

/**
 * Class that handles output to the user (the View in MVC)
 */
public class UserOutput implements View {

  /**
   * Appendable to append each output
   */
  private Appendable out;

  /**
   * Constructor for UserOutput
   *
   * @param out An appendable that each output is appended to
   */
  public UserOutput(Appendable out) {
    this.out = out;
  }


  /**
   * Greets the user
   *
   * @throws IOException handles potential exceptions
   */
  public void displayGreeting() throws IOException {
    out.append("Hello! Welcome to your study session! "
        + "Please input a full path to an '.sr' file"
        + " you would like to use." + "\n");
  }

  /**
   * Asks the user how many questions they want to study
   *
   * @throws IOException handles potential exceptions
   */
  public void howManyQuestions() throws IOException {
    out.append("How many questions would you like for today's session?" + "\n");
  }


  /**
   * Displays the question to the user
   *
   * @param fc A flashcard representing the question to be asked
   * @throws IOException handles potential exceptions
   */
  public void displayQuestion(FlashCard fc) throws IOException {
    out.append(fc.getQuestion()).append("\n");
  }

  /**
   * Tells the user how to respond to the given output
   *
   * @throws IOException handles potential exceptions
   */
  public void displayInstructions() throws IOException {
    out.append("""
        Choose one of the following. Clicking SHOW ANSWER will move you onto the next question!
        Make sure to mark the answer as EASY or HARD before moving on.
        (E) EASY
        (H) HARD
        (A) SHOW ANSWER
        (X) EXIT""" + "\n");
  }


  /**
   * Displays the answer to the user
   *
   * @param fc A flashcard representing the answer
   * @throws IOException handles potential exceptions
   */
  public void displayAnswer(FlashCard fc) throws IOException {
    out.append(fc.getAnswer()).append("\n");
  }

  /**
   * Displays the fields of the stats creator at the ending screen
   *
   * @param statsCreator A StatsCreator object that will calculate the stats
   * @throws IOException handles potential exceptions
   */
  public void displayStatus(StatsCreator statsCreator) throws IOException {

    //1. the total number of questions answered for that session,
    out.append("Total number of questions answered: ")
        .append(String.valueOf(statsCreator.getTotalAnswered())).append("\n");

    //2. the total number of questions that changed from easy to hard,
    out.append("Total questions whose difficulty changed from easy to hard: ")
        .append(String.valueOf(statsCreator.getEtoh())).append("\n");

    //3. the total number of questions that changed from hard to easy,
    out.append("Total questions whose difficulty changed from hard to easy: ")
        .append(String.valueOf(statsCreator.getHtoE())).append("\n");

    //4. the updated total number of hard questions in the question bank, and
    out.append("Total number of hard questions: ")
        .append(String.valueOf(statsCreator.getNumOfHard())).append("\n");

    //5. the updated total number of easy questions in the question bank.
    out.append("Total number of easy questions: ")
        .append(String.valueOf(statsCreator.getNumOfEasy())).append("\n");
  }
}
