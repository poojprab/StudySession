package cs3500.pa02.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.StatsCreator;
import cs3500.pa02.reader.FlashCardMaker;
import cs3500.pa02.writer.WriteFiles;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class StudySession
 */
class StudySessionTest {

  StatsCreator statsCreator;

  String userInput1;
  String userInput2;

  StudySession session1;

  Readable input1;

  StringBuilder output1;

  StudySession session2;

  Readable input2;

  StringBuilder output2;


  StringBuilder originalContent;

  Scanner inputScan1;

  Scanner inputScan2;

  WriteFiles fileWriter;

  Path samplePath;

  FlashCardMaker flashCardMaker;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setup() {
    samplePath = Path.of("Examples/Examples3/sampleQB.sr");
    statsCreator = new StatsCreator(samplePath);
    userInput1 = "Examples/Examples3/sampleQB.sr\n"
        + "3\n" + "A\n" + "X\n";
    userInput2 = "Examples/Examples3/sampleQB.sr\n"
        + "3\n" + "E\n" + "A\n" + "H\n" + "A\n" + "X\n";

    input1 = new StringReader(userInput1);
    output1 = new StringBuilder();
    session1 = new StudySession(input1, output1);
    inputScan1 = new Scanner(input1);


    input2 = new StringReader(userInput2);
    output2 = new StringBuilder();
    session2 = new StudySession(input2, output2);
    inputScan2 = new Scanner(input2);

    fileWriter = new WriteFiles();
    flashCardMaker = new FlashCardMaker(samplePath);

    try {
      originalContent = flashCardMaker.read();
      session1.run();
      session2.run();
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * Fixes the files so that they aren't randomized each time I test
   */
  @AfterEach
  public void cleanup() {
    try {
      Files.delete(samplePath);
      fileWriter.writeToFile(samplePath, originalContent);
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * Test to test that the study session mode is printing the correct content
   */
  @Test
  public void testRun() {
    assertEquals(output1.toString(), "Hello! Welcome to your study session! "
        + "Please input a full path to an '.sr' file you would like to use."
        + "\n" + "How many questions would you like for today's session?"
        + "\n" + "- What is the longest river in Europe?"
        + "\n" + """
        Choose one of the following. Clicking SHOW ANSWER will move you onto the next question!
        Make sure to mark the answer as EASY or HARD before moving on.
        (E) EASY
        (H) HARD
        (A) SHOW ANSWER
        (X) EXIT"""
        + "\n" + "The longest river is the Volga River. "
        + "\n" + "- Which country is famous for its tulips and windmills?"
        + "\n" + """
        Choose one of the following. Clicking SHOW ANSWER will move you onto the next question!
        Make sure to mark the answer as EASY or HARD before moving on.
        (E) EASY
        (H) HARD
        (A) SHOW ANSWER
        (X) EXIT""" + "\n" + "Total number of questions answered: 1"
        + "\n" + "Total questions whose difficulty changed from easy to hard: 0"
        + "\n" + "Total questions whose difficulty changed from hard to easy: 0"
        + "\n" + "Total number of hard questions: 1"
        + "\n" + "Total number of easy questions: 1" + "\n"
    );
  }

  /**
   * Test to test that the study session mode is printing the correct content given that the user
   * also changes the difficulty
   */
  @Test
  public void testRunModifyDifficulty() {
    assertEquals(output2.toString(), "Hello! Welcome to your study session! "
        + "Please input a full path to an '.sr' file you would like to use."
        + "\n" + "How many questions would you like for today's session?"
        + "\n" + "- What is the longest river in Europe?"
        + "\n" + """
        Choose one of the following. Clicking SHOW ANSWER will move you onto the next question!
        Make sure to mark the answer as EASY or HARD before moving on.
        (E) EASY
        (H) HARD
        (A) SHOW ANSWER
        (X) EXIT"""
        + "\n" + "The longest river is the Volga River. "
        + "\n" + "- Which country is famous for its tulips and windmills?"
        + "\n" + """
        Choose one of the following. Clicking SHOW ANSWER will move you onto the next question!
        Make sure to mark the answer as EASY or HARD before moving on.
        (E) EASY
        (H) HARD
        (A) SHOW ANSWER
        (X) EXIT""" + "\n" + "- The Netherlands. " + "\n"
        + "Total number of questions answered: 2"
        + "\n" + "Total questions whose difficulty changed from easy to hard: 1"
        + "\n" + "Total questions whose difficulty changed from hard to easy: 1"
        + "\n" + "Total number of hard questions: 1"
        + "\n" + "Total number of easy questions: 1" + "\n"
    );
  }

}