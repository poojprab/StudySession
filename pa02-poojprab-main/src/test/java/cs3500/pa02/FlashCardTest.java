package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for class FlashCard
 */
class FlashCardTest {

  FlashCard fc1;
  FlashCard fc2;

  /**
   * setup for testing
   */
  @BeforeEach
  public void setup() {
    fc1 = new FlashCard("What is the capital of Idaho?",
        "Boise", "HARD");
    fc2 = new FlashCard("What is the capital of Illinois?",
        "Springfield", "EASY");
  }

  /**
   * test for method setDifficulty()
   */
  @Test
  public void testSetDifficulty() {
    fc1.setDifficulty("EASY");
    fc2.setDifficulty("HARD");
    assertEquals(fc1.getDifficulty(), AnswerType.EASY);
    assertEquals(fc2.getDifficulty(), AnswerType.HARD);
  }

  /**
   * test for method getQuestion()
   */
  @Test
  public void testGetQuestion() {
    assertEquals(fc1.getQuestion(), "What is the capital of Idaho?");
    assertEquals(fc2.getQuestion(), "What is the capital of Illinois?");
  }

  /**
   * test for method getAnswer()
   */
  @Test
  public void testGetAnswer() {
    assertEquals(fc1.getAnswer(), "Boise");
    assertEquals(fc2.getAnswer(), "Springfield");
  }

  /**
   * test for seeing if the constructor fails given invalid input
   */
  @Test
  public void testInvalidDifficulty() {
    assertThrows(IllegalArgumentException.class, () -> {
      new FlashCard("What is the capital of Illinois?",
          "Springfield", "COOKIE");
    }, "invalid sort type");
    assertThrows(IllegalArgumentException.class, () -> {
      fc2.setDifficulty("Cookie");
    }, "invalid sort type");
  }

}