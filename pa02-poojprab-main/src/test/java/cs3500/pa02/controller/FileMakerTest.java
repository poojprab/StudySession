package cs3500.pa02.controller;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class FileMaker
 */
class FileMakerTest {

  FileMaker fm1;
  FileMaker fm2;
  FileMaker fm3;

  Path startPath;

  Path endFileMd;
  Path endFileSr;

  String sort1;
  String sort2;
  String sort3;

  /**
   * Setup for before testing
   */
  @BeforeEach
  public void setup() {
    startPath = Path.of("Examples/");
    endFileMd = Path.of("Examples/Examples1/Examples2/examplefile.md");
    endFileSr = Path.of("Examples/Examples1/Examples2/examplefile.sr");
    sort1 = "filename";
    sort2 = "modified";
    sort3 = "created";
    fm1 = new FileMaker(startPath, endFileMd, sort1);
    fm2 = new FileMaker(startPath, endFileMd, sort2);
    fm3 = new FileMaker(startPath, endFileMd, sort3);
  }

  /**
   * Test to make the two .md and .sr files
   */
  @Test
  public void runTest() {
    try {
      fm1.run();
    } catch (Exception e) {
      fail();
    }

    assertTrue(endFileMd.toFile().exists());
    assertTrue(endFileSr.toFile().exists());

    try {
      fm2.run();
    } catch (Exception e) {
      fail();
    }

    assertTrue(endFileMd.toFile().exists());
    assertTrue(endFileSr.toFile().exists());

    try {
      fm3.run();
    } catch (Exception e) {
      fail();
    }

    assertTrue(endFileMd.toFile().exists());
    assertTrue(endFileSr.toFile().exists());

    assertThrows(IllegalArgumentException.class, () ->
        new FileMaker(startPath, endFileMd, "Cookies"));

  }

}