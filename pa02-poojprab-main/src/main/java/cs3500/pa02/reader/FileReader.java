package cs3500.pa02.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * FROM PA01:
 * Reads an individual file and formats it
 */
public class FileReader implements Reader {

  private final Path pathName;

  public FileReader(Path pathName) {
    this.pathName = pathName;
  }


  /**
   * Uses scanner to read a file
   *
   * @return StringBuilder the string that will be appended to the study guide
   * @throws IOException handles any potential exceptions
   */
  public StringBuilder read() throws IOException {

    //creates scanner
    Scanner input = new Scanner(pathName);

    //string builder
    StringBuilder content = new StringBuilder();

    return this.formatFile(input, content);
  }

  /**
   * Formats the file according to the rules of the assignment
   *
   * @param input   a scanner object to read the file
   * @param content a StringBuilder to concatenate the strings to
   * @return StringBuilder the string that will be appended to the study guide
   */
  private StringBuilder formatFile(Scanner input, StringBuilder content) {

    while (input.hasNextLine()) {
      content.append(input.nextLine()).append("\n");
    }

    String[] adf = content.toString().split("\n");

    StringBuilder correct = new StringBuilder();

    StringBuilder notcorrect = new StringBuilder();

    for (String s : adf) {
      if (s.contains("#")) {
        notcorrect.append("[[").append(s).append("]]");
      } else {
        notcorrect.append(s);
      }
    }

    String[] notC = notcorrect.toString().split("\\[\\[");

    for (int i = 0; i < notC.length; i++) {
      if (notC[i].contains("]]")) {
        if (notC[i].contains("#")) {
          if (i == 1) {
            correct.append(notC[i].substring(0, notC[i].indexOf("]]"))).append("\n");
          } else {
            correct.append("\n").append(notC[i].substring(0, notC[i].indexOf("]]"))).append("\n");
          }
        } else {
          correct.append("- ").append(notC[i].substring(0, notC[i].indexOf("]]"))).append("\n");
        }
      }
    }

    return correct;
  }
}
