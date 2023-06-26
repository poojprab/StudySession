package cs3500.pa02;

import java.util.ArrayList;

/**
 * Handles creating and updating flashcards
 */
public class CardHandler {

  public CardHandler() {
  }

  /**
   * Returns a list of flash cards representing the question bank
   *
   * @param sb string builder containing the flashcards
   * @return the complete arraylist of flashcards representing the question bank
   */
  public static ArrayList<FlashCard> createCards(StringBuilder sb) {

    ArrayList<FlashCard> flashCards = new ArrayList<>();

    String[] cards = sb.toString().split("\n");

    for (String s : cards) {
      String question = s.substring(0, s.indexOf(":::"));
      String answer = s.substring(s.indexOf(":::") + 3, s.indexOf("&&"));
      String difficulty = s.substring(s.indexOf("&&") + 2, s.indexOf("&&") + 6);
      flashCards.add(new FlashCard(question, answer, difficulty));
    }
    return flashCards;
  }

  /**
   * Modifies the difficulty of a single question and stores the data in the StatsCreator
   *
   * @param fc A flashcard to be modified
   * @param difficulty The difficulty to be changed
   * @param statsCreator A StatsCreator object to store the data changed
   */
  public static void modifyDifficulty(FlashCard fc,
                                      AnswerType difficulty, StatsCreator statsCreator) {
    if (!fc.getDifficulty().equals(difficulty)) {
      if (difficulty.equals(AnswerType.EASY)) {
        statsCreator.hardToEasy();
      } else {
        statsCreator.easyToHard();
      }
      fc.setDifficulty(difficulty.toString());
    }
  }
}
