package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for class StudyGuide
 */
class StudyGuideTest {

  StudyGuide studyGuide;

  Path startPath;

  Path endPath;

  Path tempFile;

  Path tempFile2;

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
    tempFile2 = Path.of("Examples/Examples1/Examples2/sampleSr4.sr");
    studyGuide = new StudyGuide(startPath, OrderingType.FILENAME, endPath);
  }

  /**
   * test for method formatFile()
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
      sb = studyGuide.formatFile(fileWalker);
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
    StringBuilder sb2 = new StringBuilder();

    try {
      sb = studyGuide.fixFile(tempFile);
      sb2 = studyGuide.fixFile(tempFile2);
    } catch (Exception e) {
      fail();
    }

    assertEquals(sb.toString(),
        "");

    assertEquals(sb2.toString(),
        "- This is important! This line should be kept!" + "\n");

  }

}