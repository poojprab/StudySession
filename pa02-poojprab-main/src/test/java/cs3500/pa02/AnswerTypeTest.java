package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test for enumeration AnswerType
 */
class AnswerTypeTest {

  /**
   * test for method getValue()
   */
  @Test
  public void testGetValue() {
    assertEquals(AnswerType.EASY.getValue(), "E");
    assertEquals(AnswerType.HARD.getValue(), "H");
    assertEquals(AnswerType.EXIT.getValue(), "X");
    assertEquals(AnswerType.ANSWER.getValue(), "A");
  }

}