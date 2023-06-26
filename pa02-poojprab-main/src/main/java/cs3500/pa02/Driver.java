package cs3500.pa02;

import cs3500.pa02.controller.FileMaker;
import cs3500.pa02.controller.StudySession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Path;

/**
 * Everything except args == 0  is from PA01:
 * This is the main driver of this project. It calls other methods in other classes to produce
 * desired output.
 */
public class Driver {

  //the following string is for test purposes only. See @MainTest
  public static String status;

  /**
   * Project entry point
   *
   * @param args command line arguments
   * @throws Exception handles potential exceptions
   */
  public static void main(String[] args) throws Exception {
    //0 command lines runs study session
    //three command lines creates both documents
    //checks if commandline arguments are valid
    if (args.length == 0) {
      status = "no args given, start study session";

      StudySession studySession = new StudySession(new InputStreamReader(System.in),
          new PrintStream(System.out));

      studySession.run();

    } else if (args.length < 3) {
      status = "invalid args";
      throw new IOException("Invalid Command Line prompt");
    } else {
      status = "three args given";

      FileMaker fm = new FileMaker(Path.of(args[0]), Path.of(args[2]), args[1]);

      fm.run();
    }

    System.out.println("All Done!");
  }
}