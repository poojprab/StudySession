package cs3500.pa02;

import cs3500.pa02.reader.FlashCardMaker;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Class that handles creating statistics to be shown at the end of the game
 */
public class StatsCreator {

  /**
   * Total number of answered questions
   */
  private int totalAnswered;
  /**
   * Total number of questions changed from easy to hard
   */
  private int etoh;
  /**
   * Total number of questions changed from hard to easy
   */
  private int htoE;
  /**
   * Total number of hard questions
   */
  private int numOfHard;
  /**
   * Total number of easy questions
   */
  private int numOfEasy;

  /**
   * Path to an srFile to be reading information from
   */
  private final Path srFile;

  /**
   * Constructor for StatsCreator
   *
   * @param srFile Path to an srFile to be reading information from
   */
  public StatsCreator(Path srFile) {
    this.totalAnswered = 0;
    this.etoh = 0;
    this.htoE = 0;
    this.numOfHard = 0;
    this.numOfEasy = 0;
    this.srFile = srFile;
  }

  /**
   * Increments hard to easy
   */
  public void hardToEasy() {
    htoE++;
  }

  /**
   * Increments easy to hard
   */
  public void easyToHard() {
    etoh++;
  }

  /**
   * Sets the total answered to the count of answered questions
   *
   * @param totalAnswered the number of questions the user answered before exiting
   */
  public void setTotalAnswered(int totalAnswered) {
    this.totalAnswered = totalAnswered;
  }

  /**
   * Sets the total number of hard questions after each session
   *
   * @throws IOException handles potential exceptions
   */
  public void setNumOfHard() throws IOException {
    int count = 0;
    FlashCardMaker flashCardMaker = new FlashCardMaker(this.srFile);
    StringBuilder content = flashCardMaker.read();

    String[] cards = content.toString().split("\n");

    for (String s : cards) {
      if (s.contains("HARD")) {
        count++;
      }
    }

    numOfHard = count;
  }

  /**
   * Sets the total number of hard questions after each session
   *
   * @throws IOException handles potential exceptions
   */
  public void setNumOfEasy() throws IOException {
    int count = 0;
    FlashCardMaker flashCardMaker = new FlashCardMaker(this.srFile);
    StringBuilder content = flashCardMaker.read();

    String[] cards = content.toString().split("\n");

    for (String s : cards) {
      if (s.contains("EASY")) {
        count++;
      }
    }

    numOfEasy = count;
  }

  /**
   * Getter method for total number of answered questions
   *
   * @return total number of answered questions
   */
  public int getTotalAnswered() {
    return this.totalAnswered;
  }

  /**
   * Getter method for total number of easy to hard questions
   *
   * @return total number of easy to hard questions
   */
  public int getEtoh() {
    return this.etoh;
  }

  /**
   * Getter method for total number of hard to easy questions
   *
   * @return total number of hard to easy questions
   */
  public int getHtoE() {
    return this.htoE;
  }

  /**
   * Getter method for total number of hard questions
   *
   * @return total number of hard questions
   */
  public int getNumOfHard() {
    return this.numOfHard;
  }

  /**
   * Getter method for total number of easy questions
   *
   * @return total number of easy questions
   */
  public int getNumOfEasy() {
    return this.numOfEasy;
  }
}
