package cs3500.pa02.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Class that handles reading an SR file to be converted into a flashcard object
 */
public class FlashCardMaker implements Reader {

  /**
   * Path that represents the path to a file to be read
   */
  private final Path pathName;

  public FlashCardMaker(Path pathName) {
    this.pathName = pathName;
  }

  /**
   * Reads a file and returns a string builder of what was in it
   *
   * @return A string builder representing what is in the file that was read
   * @throws IOException handles potential exceptions
   */
  @Override
  public StringBuilder read() throws IOException {

    //creates scanner
    Scanner input = new Scanner(this.pathName);

    StringBuilder content = new StringBuilder();

    while (input.hasNextLine()) {
      String currentLine = input.nextLine();

      content.append(currentLine).append("\n");
    }

    return content;
  }
}
