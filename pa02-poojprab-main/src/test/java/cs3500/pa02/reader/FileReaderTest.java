
package cs3500.pa02.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for class FileReader
 */
class FileReaderTest {

  FileReader fr1;
  FileReader fr2;
  FileReader fr3;

  Path file1;

  Path file2;

  Path file3;

  Scanner input1;
  Scanner input2;
  Scanner input3;

  String ex1;

  String ex2;


  /**
   * Setup for testing
   */
  @BeforeEach
  public void setup() {

    //creates the file
    file1 = Path.of("src/test/resources/sampleFiles/fileReaderEx1.md");

    //creates the file
    file2 = Path.of("src/test/resources/sampleFiles/fileReaderEx2.md");

    //creates the file
    file3 = Path.of("src/test/resources/sampleFiles/fileReaderEx3.md");

    fr1 = new FileReader(file1);
    fr2 = new FileReader(file2);
    fr3 = new FileReader(file3);

    try {
      //creates scanner
      input1 = new Scanner(file1);
      //creates scanner
      input2 = new Scanner(file2);
      //creates scanner
      input3 = new Scanner(file3);
    } catch (Exception e) {
      System.err.println(e);
    }

    //creates String
    ex1 = "- General form:  arrayName = new type[numberOfElements];" + "\n";

    //creates String
    ex2 = "- only creates a reference" + "\n" + "- no array has  actually been created yet"
        + "\n";
  }


  /**
   * test for method readFile()
   */
  @Test
  public void testReadFile() {
    try {
      assertEquals(fr1.read()
              .toString(),
          "");
      assertEquals(ex1, fr2.read()
          .toString());
      assertEquals(ex2, fr3.read()
          .toString());
    } catch (Exception e) {
      fail();
    }
  }
}

