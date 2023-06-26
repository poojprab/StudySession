package cs3500.pa02.writer;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Interface that handles writing to files
 */
public interface Writer {

  /**
   * Writes the content to a specified file
   *
   * @param endPath Path to a file where output should be written
   * @param content the content to be written
   */
  void writeToFile(Path endPath, StringBuilder content) throws IOException;
}
