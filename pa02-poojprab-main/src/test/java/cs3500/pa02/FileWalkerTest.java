package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * From PA01:
 * test class for the fileWalker class
 */
class FileWalkerTest {

  FileTime knownCreationTimeOne;
  FileTime knownCreationTimeTwo;
  Long knownModifiedTimeOne;
  Long knownModifiedTimeTwo;
  MarkDownFile mdfex1;
  MarkDownFile mdfex2;
  ArrayList<MarkDownFile> expectedFiles;
  private String sampleInputsDirectory;

  @BeforeEach
  public void setup() {
    knownCreationTimeOne = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
    knownCreationTimeTwo = FileTime.from(Instant.parse("2023-05-14T12:00:01Z"));
    knownModifiedTimeOne = 21111L;
    knownModifiedTimeTwo = 22222L;
    mdfex1 = new MarkDownFile(knownCreationTimeOne,
        "src/test/resources/sampleFiles2/new.md", knownModifiedTimeOne);
    mdfex2 = new MarkDownFile(knownCreationTimeTwo,
        "src/test/resources/sampleFiles2/fileWriterTest.md", knownModifiedTimeTwo);

    expectedFiles = new ArrayList<>();
    expectedFiles.add(mdfex1);
    expectedFiles.add(mdfex2);
  }

  /**
   * test for getHashOfFilesAttr()
   */
  @Test
  public void testGetList() {
    sampleInputsDirectory = "src/test/resources/sampleFiles2";
    FileWalker mfv = new FileWalker();
    try {
      Files.walkFileTree(Path.of(sampleInputsDirectory), mfv);
    } catch (Exception e) {
      fail();
    }

    // get list of traversed Markdown file paths
    ArrayList<MarkDownFile> actualFiles = mfv.getListOfMdFiles();

    // compare both hashmaps
    assertFalse(expectedFiles.isEmpty());
    assertEquals(actualFiles.size(), expectedFiles.size());
    assertEquals(expectedFiles.get(0).getPathName(), actualFiles.get(0).getPathName());
    assertEquals(expectedFiles.get(1).getPathName(), actualFiles.get(1).getPathName());
  }

  /**
   * test method for failing cases in getHashOfFilesAttr()
   */
  @Test
  public void testGetListFail() {
    FileWalker mfv = new FileWalker();

    try {
      IllegalStateException exception = assertThrows(IllegalStateException.class,
          mfv::getListOfMdFiles);
      String expectedMessage = "getList called before fileWalker";
      String actualMessage = exception.getMessage();

      assertTrue(actualMessage.contains(expectedMessage));
    } catch (IllegalStateException e) {
      fail();
    }
  }

  /**
   * test method for method visitFileFailed()
   */
  @Test
  public void testVisitFileFailed() {
    sampleInputsDirectory = "src/test/resources/sampleFiles/edges.md";
    FileWalker mfv = new FileWalker();
    try {
      Files.walkFileTree(Path.of(sampleInputsDirectory), mfv);
      assertEquals("file not found", mfv.file1);
    } catch (Exception e) {
      fail();
    }
  }
}
