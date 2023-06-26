package cs3500.pa02.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Class that handles writing strings to a file
 */
public class WriteFiles implements Writer {

  public WriteFiles() {
  }

  /**
   * Writes the content to a specified file
   *
   * @param endPath Path to a file where output should be written
   * @param content the content to be written
   */
  public void writeToFile(Path endPath, StringBuilder content) throws IOException {

    //create file writer
    FileWriter output = new FileWriter(endPath.toFile());

    //write to file
    output.write(content.toString());

    //close the output
    output.close();
  }
}
