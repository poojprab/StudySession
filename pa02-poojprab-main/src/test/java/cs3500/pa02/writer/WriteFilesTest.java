package cs3500.pa02.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.reader.FlashCardMaker;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for WriteFiles class
 */
class WriteFilesTest {

  WriteFiles wf;

  Path path1;

  StringBuilder content;

  FlashCardMaker fcm;

  /**
   * setup for testing
   */
  @BeforeEach
  public void setup() {
    wf = new WriteFiles();
    path1 = Path.of("Examples/Examples1/Examples2/sampleSr2.sr");
    content = new StringBuilder();
    content.append("Hello :) ! ").append("The capital of India is New Delhi.");
  }

  /**
   * test for method writeToFile()
   */
  @Test
  public void testWriteToFile() {

    StringBuilder sb = new StringBuilder();
    try {
      wf.writeToFile(path1, content);
    } catch (Exception e) {
      fail();
    }

    assertTrue(path1.toFile().exists());

    fcm = new FlashCardMaker(path1);

    try {
      sb = fcm.read();
    } catch (Exception e) {
      fail();
    }

    content.append("\n");

    try {
      assertEquals(sb.toString(), content.toString());
    } catch (Exception e) {
      fail();
    }
  }

}