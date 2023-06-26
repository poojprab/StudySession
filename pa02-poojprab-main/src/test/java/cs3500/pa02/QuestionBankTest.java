package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for class QuestionBank
 */
class QuestionBankTest {

  QuestionBank questionBank;

  Path startPath;

  Path endPath;

  Path tempFile;

  FileWalker fileWalker;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    fileWalker = new FileWalker();
    startPath = Path.of("Examples/Examples3/");
    endPath = Path.of("test/resources/tempFiles/temp.sr");
    tempFile = Path.of("Examples/Examples1/Examples2/sampleSr3.sr");
    questionBank = new QuestionBank(startPath, endPath);
  }

  /**
   * tests for method formatFile()
   */
  @Test
  public void testFormatFile() {

    StringBuilder sb = new StringBuilder();

    try {
      Files.walkFileTree(startPath, fileWalker);
    } catch (Exception e) {
      fail();
    }

    try {
      sb = questionBank.formatFile(fileWalker);
    } catch (Exception e) {
      fail();
    }

    assertEquals(sb.toString(),
        "- This is important! QuestionBank Test!" + "\n");

  }

  /**
   * test for method fixFile()
   */
  @Test
  public void testFixFile() {

    StringBuilder sb = new StringBuilder();

    try {
      sb = questionBank.fixFile(tempFile);
    } catch (Exception e) {
      fail();
    }

    assertEquals(sb.toString(),
        "- What is the longest river in Europe?:::"
            + "The longest river is the Volga River. &&HARD&&" + "\n");

  }

}