package cs3500.pa02.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.FlashCard;
import cs3500.pa02.StatsCreator;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class UserOutput
 */
class UserOutputTest {

  UserOutput userOutput;
  ByteArrayOutputStream outContent;

  PrintStream originalOut;

  FlashCard fc;

  StatsCreator statsCreator;

  Appendable sb;


  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    sb = new StringBuilder();
    userOutput = new UserOutput(sb);
    outContent = new ByteArrayOutputStream();
    originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    fc = new FlashCard("How many colors in the rainbow?",
        "6", "HARD");

    statsCreator = new StatsCreator(Path.of("Examples/Examples1"
        + "/Examples2/sampleSr.sr"));
    try {
      statsCreator.setNumOfEasy();
      statsCreator.setNumOfHard();
      statsCreator.setTotalAnswered(10);
    } catch (Exception e) {
      fail();
    }

  }

  /**
   * test for method DisplayGreeting()
   */
  @Test
  public void testDisplayGreeting() {
    try {
      userOutput.displayGreeting();
      assertEquals("Hello! Welcome to your study session! "
          + "Please input a full path to an '.sr' file"
          + " you would like to use." + "\n", sb.toString());
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * test for method howManyQuestions()
   */
  @Test
  public void testHowManyQuestions() {
    try {
      userOutput.howManyQuestions();
      assertEquals("How many questions would you like for today's session?"
          + "\n", sb.toString());
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * test for method displayQuestion()
   */
  @Test
  public void testDisplayQuestion() {
    try {
      userOutput.displayQuestion(fc);
      assertEquals("How many colors in the rainbow?"
          + "\n", sb.toString());
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * test for method displayAnswer()
   */
  @Test
  public void testDisplayAnswer() {
    try {
      userOutput.displayAnswer(fc);
      assertEquals("6" + "\n", sb.toString());
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * test for methods displayInstructions()
   */
  @Test
  public void testDisplayInstructions() {
    try {
      userOutput.displayInstructions();
      assertEquals("""
        Choose one of the following. Clicking SHOW ANSWER will move you onto the next question!
        Make sure to mark the answer as EASY or HARD before moving on.
        (E) EASY
        (H) HARD
        (A) SHOW ANSWER
        (X) EXIT""" + "\n", sb.toString());
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * test for class displayStatus()
   */
  @Test
  public void testDisplayStatus() {
    try {
      userOutput.displayStatus(statsCreator);

      assertEquals("Total number of questions answered: "
          + statsCreator.getTotalAnswered()
          + "\n" + "Total questions whose difficulty changed from easy to hard: "
          + statsCreator.getEtoh()
          + "\n" + "Total questions whose difficulty changed from hard to easy: "
          + statsCreator.getHtoE()
          + "\n" + "Total number of hard questions: "
          + statsCreator.getNumOfHard()
          + "\n" + "Total number of easy questions: "
          + statsCreator.getNumOfEasy() + "\n", sb.toString());
    } catch (Exception e) {
      fail();
    }
  }

}