package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Class that handles formatting files (both .sr and .md)
 */
public abstract class FileFormatter {
  /**
   * Path to store where the file walker should start walking from
   */
  protected Path startPath;
  /**
   * Path to store where the ending file should be written
   */
  protected Path endPath;

  public FileFormatter(Path startPath, Path endFile) {
    this.startPath = startPath;
    this.endPath = endFile;
  }

  /**
   * Formats the file by the rules of the assignment
   *
   * @param fw A FileWalker needed to access a list of .md files
   * @return A string builder that is formatted and will be written to a temporary file.
   * @throws IOException handles potential exceptions
   */
  public abstract StringBuilder formatFile(FileWalker fw) throws IOException;


  /**
   * Fixes the file into either an .md or .sr file representing the study guide and question bank
   * respectively.
   *
   * @param tempPath Represents a path to a file that is temporarily written then deleted. Its
   *                 contents will be used before deletion.
   * @return A String builder representing the fixed file.
   * @throws IOException handles potential exceptions
   */
  public abstract StringBuilder fixFile(Path tempPath) throws IOException;
}

