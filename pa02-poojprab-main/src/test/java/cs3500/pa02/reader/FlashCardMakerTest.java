package cs3500.pa02.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class FlashCardMaker
 */
class FlashCardMakerTest {

  FlashCardMaker fcm;

  Path pathex1;

  StringBuilder expected;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setup() {
    pathex1 = Path.of("Examples/Examples1/Examples2/sampleSr.sr");
    fcm = new FlashCardMaker(pathex1);
    expected = new StringBuilder();
    expected
        .append("- What is the longest river in Europe?:::"
          + "The longest river is the Volga River. &&HARD&&")
        .append("\n");
  }

  /**
   * Test for method read()
   */
  @Test
  public void readTest() {
    StringBuilder sb = new StringBuilder();
    try {
      sb = fcm.read();
    } catch (Exception e) {
      fail();
    }

    assertEquals(sb.toString(), expected.toString());
  }

}