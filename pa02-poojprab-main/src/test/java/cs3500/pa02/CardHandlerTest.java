package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardHandlerTest {

  StringBuilder sb1;

  StatsCreator statsCreator;

  /**
   * setup for testing
   */
  @BeforeEach
  public void setup() {
    sb1 = new StringBuilder();
    sb1.append("What is the capital of India?:::New Delhi &&HARD&&").append("\n");
    sb1.append("What is the capital of America?:::Washington DC &&EASY&&").append("\n");

    statsCreator = new StatsCreator(Path.of("Examples/Examples3/sampleQB.sr"));
  }

  /**
   * test for method createCards()
   */
  @Test
  public void testCreateCards() {
    ArrayList<FlashCard> listOfCards = CardHandler.createCards(sb1);
    assertFalse(listOfCards.isEmpty());
    assertEquals(listOfCards.size(), 2);
    assertEquals(listOfCards.get(0).getQuestion(), "What is the capital of India?");
    assertEquals(listOfCards.get(0).getAnswer(), "New Delhi ");
    assertEquals(listOfCards.get(0).getDifficulty(), AnswerType.HARD);
    assertEquals(listOfCards.get(1).getQuestion(), "What is the capital of America?");
    assertEquals(listOfCards.get(1).getAnswer(), "Washington DC ");
    assertEquals(listOfCards.get(1).getDifficulty(), AnswerType.EASY);
  }

  /**
   * test for method modifyDifficulty()
   */
  @Test
  public void testModifyDifficulty() {
    ArrayList<FlashCard> listOfCards = CardHandler.createCards(sb1);
    CardHandler.modifyDifficulty(listOfCards.get(0), AnswerType.EASY, statsCreator);
    assertEquals(listOfCards.get(0).getDifficulty(), AnswerType.EASY);
    CardHandler.modifyDifficulty(listOfCards.get(1), AnswerType.HARD, statsCreator);
    assertEquals(listOfCards.get(1).getDifficulty(), AnswerType.HARD);
  }

}