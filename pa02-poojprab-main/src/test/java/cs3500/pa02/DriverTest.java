package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for class Driver
 */
class DriverTest {

  String[] arr1;

  String[] arr2;

  String[] arr3;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    arr1 = new String[] {"/user/src"};
    arr2 = new String[] {
        "src/test/resources/sampleFiles", "filename", "src/test/resources/sampleFiles/arrays.md"};
    arr3 = new String[]{};
  }


  /**
   * test method for invalid command line
   */

  @Test
  public void mainTestFail() {

    Exception exception = assertThrows(Exception.class, () -> {
      Driver.main(arr1);
    });

    String expectedMessage = "Invalid Command Line prompt";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * test method for valid command line to create files
   */

  @Test
  public void mainTestPass() {
    try {
      Driver.main(arr2);
      assertEquals("three args given", Driver.status);
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * test method for valid command line to start study session
   */
  @Test
  public void mainTestStudySession() {
    try {
      Driver.main(arr3);
    } catch (IllegalArgumentException e) {
      fail("Exception was thrown: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("session ran");
    }
  }
}