package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for class statsCreator
 */
class StatsCreatorTest {

  StatsCreator statsCreator;

  Path path1;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    path1 = Path.of("Examples/Examples3/sampleQB.sr");
    statsCreator = new StatsCreator(path1);
  }

  /**
   * test for method hardToEasy()
   */
  @Test
  public void testHardToEasy() {
    statsCreator.hardToEasy();
    assertEquals(statsCreator.getHtoE(), 1);
  }

  /**
   * test for method easyToHard()
   */
  @Test
  public void testEasyToHard() {
    statsCreator.easyToHard();
    assertEquals(statsCreator.getEtoh(), 1);
  }

  /**
   * test for method setTotalAnswered()
   */
  @Test
  public void testSetTotalAnswered() {
    statsCreator.setTotalAnswered(5);
    assertEquals(statsCreator.getTotalAnswered(), 5);
  }

  /**
   * test for method setNumOfHard() and getNumOfHard()
   */
  @Test
  public void testSetNumOfHard() {
    try {
      statsCreator.setNumOfHard();
      assertEquals(statsCreator.getNumOfHard(), 1);
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * test for method setNumOfEasy() and getNumOfEasy()
   */
  @Test
  public void testSetNumOfEasy() {
    try {
      statsCreator.setNumOfEasy();
      assertEquals(statsCreator.getNumOfEasy(), 1);
    } catch (Exception e) {
      fail();
    }

  }

}