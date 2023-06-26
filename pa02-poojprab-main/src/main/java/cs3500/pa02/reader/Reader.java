package cs3500.pa02.reader;

import java.io.IOException;

/**
 * Interface that represents classes that implement the function read()
 */
public interface Reader {

  /**
   * Reads a files contents
   *
   * @return A String builder representing everything that was read in the file
   * @throws IOException handles potential exceptions
   */
  StringBuilder read() throws IOException;

}
