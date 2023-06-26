package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.MarkDownFile;
import cs3500.pa02.OrderingType;
import cs3500.pa02.Sort;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test class for the class sort
 */
class SortTest {

  Sort s1;

  Sort s2;

  Sort s3;

  FileTime knownCreationTimeOne;
  FileTime knownCreationTimeTwo;
  FileTime knownCreationTimeThree;

  Long knownModifiedTimeOne;
  Long knownModifiedTimeTwo;
  Long knownModifiedTimeThree;

  MarkDownFile mdfex1;
  MarkDownFile mdfex2;

  MarkDownFile mdfex3;

  ArrayList<Path> arr1;
  ArrayList<Path> arr2;

  ArrayList<Path> arr3;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    knownCreationTimeOne = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    knownCreationTimeTwo = FileTime.from(Instant.parse("2023-05-14T12:00:01Z"));
    knownCreationTimeThree = FileTime.from(Instant.parse("2023-05-14T12:00:02Z"));
    knownModifiedTimeOne = 21111L;
    knownModifiedTimeTwo = 22222L;
    knownModifiedTimeThree = 22223L;
    mdfex1 = new MarkDownFile(knownCreationTimeOne,
        "src/test/resources/sampleFiles2/fileWriterTest.md", knownModifiedTimeOne);
    mdfex2 = new MarkDownFile(knownCreationTimeTwo,
        "src/test/resources/sampleFiles2/new.md", knownModifiedTimeTwo);
    mdfex3 = new MarkDownFile(knownCreationTimeTwo,
        "src/test/resources/sampleFiles2/blah.md", knownModifiedTimeThree);

    s1 = new Sort(OrderingType.FILENAME);
    s2 = new Sort(OrderingType.MODIFIED);
    s3 = new Sort(OrderingType.CREATED);

    arr1 = new ArrayList<>();

    arr2 = new ArrayList<>();

    arr3 = new ArrayList<>();
  }

  /**
   * test method to test sortBy()
   */
  @Test
  public void testSortBy() {
    arr1.add(Path.of("src/test/resources/sampleFiles2/fileWriterTest.md"));
    arr1.add(Path.of("src/test/resources/sampleFiles2/new.md"));
    arr1.add(Path.of("src/test/resources/sampleFiles2/blah.md"));

    arr2.add(Path.of("src/test/resources/sampleFiles2/fileWriterTest.md"));
    arr2.add(Path.of("src/test/resources/sampleFiles2/new.md"));
    arr2.add(Path.of("src/test/resources/sampleFiles2/blah.md"));

    arr3.add(Path.of("src/test/resources/sampleFiles2/fileWriterTest.md"));
    arr3.add(Path.of("src/test/resources/sampleFiles2/new.md"));
    arr3.add(Path.of("src/test/resources/sampleFiles2/blah.md"));
    // get list of traversed Markdown file paths
    ArrayList<MarkDownFile> actualFiles = new ArrayList<>();
    actualFiles.add(mdfex1);
    actualFiles.add(mdfex2);
    actualFiles.add(mdfex3);

    // compare both hashmaps
    assertEquals(arr1, s1.sortBy(actualFiles));
    assertEquals(s2.sortBy(actualFiles), arr2);
    assertEquals(s3.sortBy(actualFiles), arr3);
  }

}
