package cs3500.pa02.controller;

import cs3500.pa02.AnswerType;
import cs3500.pa02.CardHandler;
import cs3500.pa02.FlashCard;
import cs3500.pa02.QuestionBankHandler;
import cs3500.pa02.StatsCreator;
import cs3500.pa02.reader.FlashCardMaker;
import cs3500.pa02.view.UserOutput;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for handling user input via the console
 */
public class StudySession implements Controller {

  /**
   * UserOutput object to print to the console
   */
  private final UserOutput userOutput;

  /**
   * ArrayList object to store all the FlashCards
   */
  private  ArrayList<FlashCard> listOfFlashCards;

  /**
   * Integer to represent the total count of answered questions
   */
  private int count;

  /**
   * StatsCreator object to handle documenting stats to be printed out
   */
  private  StatsCreator statsCreator;

  /**
   * Readable used to test input
   */
  private Readable in;

  /**
   * Scanner to take in input
   */
  private Scanner input;

  /**
   * Appendable used to test output
   */
  private Appendable out;

  /**
   * Path to the sr file containing the question bank
   */
  private Path endingFile;

  /**
   * Number of questions the user wants to study
   */
  private int questionLimit;


  /**
   * constructor for StudySession
   *
   * @param in Readable to test input
   * @param out Appendable to test output
   */
  public StudySession(Readable in, Appendable out) {

    this.in = in;
    this.out = out;

    input = new Scanner(this.in);

    userOutput = new UserOutput(this.out);

    count = 0;
  }

  /**
   * Runs the study session mode and handles user input accordingly
   *
   * @throws Exception handles potential exceptions
   */
  @Override
  public void run() throws Exception {

    endingFile = this.initializePath();

    statsCreator = new StatsCreator(endingFile);

    questionLimit = this.initalizeList();

    //Examples/Examples1/Examples2/studyGuide.sr
    FlashCard currentCard;

    String answer = "G";

    while ((count < questionLimit) && !answer.equals(AnswerType.EXIT.getValue())) {
      currentCard = listOfFlashCards.get(count);
      userOutput.displayQuestion(currentCard);
      userOutput.displayInstructions();

      answer = input.nextLine();

      while (!answer.equals(AnswerType.ANSWER.getValue())) {
        if (answer.equals(AnswerType.EASY.getValue())) {
          CardHandler.modifyDifficulty(currentCard, AnswerType.EASY, this.statsCreator);
          answer = input.nextLine();
          break;
        } else if (answer.equals(AnswerType.HARD.getValue())) {
          CardHandler.modifyDifficulty(currentCard, AnswerType.HARD, this.statsCreator);
          answer = input.nextLine();
          break;
        } else if (answer.equals(AnswerType.EXIT.getValue())) {
          break;
        } else {
          System.out.println("Invalid letter entered. Enter valid letter:");
          answer = input.nextLine();
        }
      }
      if (answer.equals("A")) {
        userOutput.displayAnswer(currentCard);
        count++;
      }
    }
    this.endScreen(count, listOfFlashCards);
  }

  /**
   * Initializes the path to be read from
   *
   * @return A path containing the file containing the question bank
   * @throws IOException handles potential exceptions
   */
  public Path initializePath() throws IOException {
    String temp;
    userOutput.displayGreeting();
    temp = input.nextLine();
    if (!Path.of(temp).toFile().exists()) {
      userOutput.displayGreeting();
      temp = input.nextLine();
    }
    return Path.of(temp);
  }

  /**
   * Initializes the list containing the total number of questions the user asked for
   *
   * @return an integer representing the total number of questions to be read
   * @throws IOException handles potential exceptions
   */
  public int initalizeList() throws IOException {
    FlashCardMaker flashCardMaker = new FlashCardMaker(endingFile);
    listOfFlashCards = CardHandler.createCards(flashCardMaker.read());

    userOutput.howManyQuestions();
    count = 0;

    this.statsCreator = new StatsCreator(endingFile);

    String temp;
    temp = input.nextLine();
    return
        Math.min(listOfFlashCards.size(), Integer.parseInt(temp));
  }

  /**
   * Displays the end screen with the stats
   *
   * @param count Integer representing total number of questions answered
   * @param listOfFlashCards List of flashcards that are in the sr file
   * @throws Exception handles potential exceptions
   */
  public void endScreen(int count, ArrayList<FlashCard> listOfFlashCards)
      throws Exception {
    QuestionBankHandler.resetSr(endingFile, listOfFlashCards);
    QuestionBankHandler.shuffleCards(listOfFlashCards, endingFile);
    statsCreator.setNumOfHard();
    statsCreator.setNumOfEasy();
    statsCreator.setTotalAnswered(count);
    userOutput.displayStatus(this.statsCreator);
    input.close();
  }
}
