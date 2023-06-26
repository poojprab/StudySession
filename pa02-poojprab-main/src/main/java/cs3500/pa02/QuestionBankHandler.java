package cs3500.pa02;

import cs3500.pa02.reader.FlashCardMaker;
import cs3500.pa02.writer.WriteFiles;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that handles the question bank
 */
public class QuestionBankHandler {

  QuestionBankHandler() {}

  /**
   * Resets teh SR to match the modified Arraylist of flash cards at the end of a session
   *
   * @param srFile .sr file to be reset
   * @param flashCards List of flashcards to match to the .sr file
   * @throws Exception handles potential exceptions
   */
  public static void resetSr(Path srFile, ArrayList<FlashCard> flashCards) throws Exception {

    //new FileWriter
    WriteFiles fileWriter = new WriteFiles();

    FlashCardMaker flashCardMaker = new FlashCardMaker(srFile);

    String content = flashCardMaker.read().toString();

    StringBuilder correctedContent = new StringBuilder();

    //parse through every card in the deck
    for (FlashCard fc : flashCards) {

      //if entire string builder contains the card, go through the code
      if (content.contains(fc.getQuestion())) {

        //get the substring of the entire card
        String card = content.substring(content.indexOf(fc.getQuestion()),
            content.indexOf("\n", content.indexOf(fc.getQuestion())));

        //get just the substring of the difficulty
        String difficulty = card.substring(card.indexOf("&&") + 2, card.lastIndexOf("&&"));

        //if the difficulty on the sr file doesn't equal the one that is documented, change it
        if (!difficulty.equals(fc.getDifficulty().toString())) {
          correctedContent
              .append(card, 0, card.indexOf("&&"))
              .append("&&").append(fc.getDifficulty().toString()).append("&&").append("\n");

          //else, just append the same card
        } else {
          correctedContent.append(card).append("\n");
        }
        //if it doesn't contain the card, the card does not exist
      }
    }

    Files.delete(srFile);

    fileWriter.writeToFile(srFile, correctedContent);
  }

  /**
   * Handles randomizing and sorting from hardest to easiest
   *
   * @param flashCards A list of flash cards to be shuffled
   * @param srFile A sr file to write to after the cards are shuffled
   * @throws IOException handles potential exceptions
   */
  public static void shuffleCards(ArrayList<FlashCard> flashCards, Path srFile) throws IOException {

    Collections.shuffle(flashCards);

    ArrayList<FlashCard> sortedCards = new ArrayList<>();

    for (int i = 0; i < flashCards.size(); i++) {
      if (flashCards.get(i).getDifficulty().equals(AnswerType.EASY.HARD)) {
        sortedCards.add(flashCards.get(i));
        flashCards.remove(flashCards.get(i));
        i--;
      }
    }

    sortedCards.addAll(flashCards);

    StringBuilder sortedCardsString = new StringBuilder();

    for (FlashCard fc : sortedCards) {
      sortedCardsString.append(fc.getQuestion()).append(":::").append(fc.getAnswer())
          .append("&&").append(fc.getDifficulty()).append("&&").append("\n");
    }
    //new FileWriter
    WriteFiles fileWriter = new WriteFiles();

    Files.delete(srFile);

    fileWriter.writeToFile(srFile, sortedCardsString);
  }
}
