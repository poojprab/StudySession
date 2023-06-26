package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.MarkDownFile;
import cs3500.pa02.SortByName;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * From PA01:
 * test class to test the sortByName class
 */
class SortByNameTest {


  SortByName sbn = new SortByName();

  FileTime knownCreationTimeOne;
  FileTime knownCreationTimeTwo;

  Long knownModifiedTimeOne;
  Long knownModifiedTimeTwo;

  MarkDownFile mdfex1;
  MarkDownFile mdfex2;

  MarkDownFile mdfex3;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    knownCreationTimeOne = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    knownCreationTimeTwo = FileTime.from(Instant.parse("2023-05-14T12:00:01Z"));
    knownModifiedTimeOne = 21111L;
    knownModifiedTimeTwo = 22222L;
    mdfex1 = new MarkDownFile(knownCreationTimeOne,
        "src/test/resources/sampleFiles/arrays.md", knownModifiedTimeOne);
    mdfex2 = new MarkDownFile(knownCreationTimeTwo,
        "src/test/resources/sampleFiles/vectors.md", knownModifiedTimeTwo);
    mdfex3 = new MarkDownFile(knownCreationTimeTwo,
        "src/test/resources/sampleFiles/vectors.md", knownModifiedTimeTwo);
  }

  /**
   * test method to test the compare method in terms of names
   */
  @Test
  public void testCompare() {
    assertEquals(-1, sbn.compare(mdfex1, mdfex2)); // Check results are as expected
    assertEquals(1, sbn.compare(mdfex2, mdfex1)); // Check results are as expected
    assertEquals(0, sbn.compare(mdfex2, mdfex3)); // Check results are as expected
  }

}