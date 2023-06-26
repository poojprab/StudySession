package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.MarkDownFile;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * From PA01:
 * test class for testing markDownFile class
 */
class MarkDownFileTest {

  /**
   * test method that tests the fields of the markDownFile class
   */
  @Test
  public void testGetCreatedAt() {
    Long knownModifiedTime = 21111L;
    FileTime knownCreationTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    MarkDownFile mdf = new MarkDownFile(knownCreationTime,
        "src/test/resources/sampleFiles/arrays.md",
        knownModifiedTime);

    assertEquals(knownCreationTime, mdf.getCreatedAt());
    assertEquals("src/test/resources/sampleFiles/arrays.md", mdf.getPathName());
  }
}