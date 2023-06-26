package cs3500.pa02.controller;

import cs3500.pa02.FileWalker;
import cs3500.pa02.OrderingType;
import cs3500.pa02.QuestionBank;
import cs3500.pa02.StudyGuide;
import cs3500.pa02.writer.WriteFiles;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that handles creating files based on command line arguments
 * (both .sr and .md)
 */
public class FileMaker implements Controller {
  /**
   * Path to store where the file walker should start walking from
   */
  private final Path startPath;
  /**
   * Path to store where the ending study guide should be written
   */
  private final Path endPathMd;
  /**
   * Path to store where the ending question bank should be written
   */
  private final Path endPathSr;

  /**
   * OrderingType enumeration to store the type of sort
   */
  private final OrderingType sortType;

  /**
   * Path to store where a temporary file should be written and used in other methods. Is to be
   * deleted at the completion of the program.
   */
  private final Path tempPath = Path.of("Examples/Examples1/Examples2/temp.md");

  /**
   * Constructor for FileMaker
   *
   * @param startPath Path to start walking files
   * @param endFile File to be written to
   * @param sort type of sort
   */
  public FileMaker(Path startPath, Path endFile, String sort) {
    this.startPath = startPath;
    this.endPathMd = endFile;
    this.endPathSr = Path.of(endFile.toString()
        .substring(0, endFile
            .toString()
            .lastIndexOf("."))
        + ".sr");
    if (!sort.equals("filename")
        && !sort.equals("created")
        && !sort.equals("modified")) {
      throw new IllegalArgumentException("invalid sort type");
    } else {
      this.sortType = OrderingType.valueOf(sort.toUpperCase());
    }
  }

  /**
   * Method that runs program to write the Question Bank and Study Guide
   */
  @Override
  public void run() throws IOException {
    //new FileWriter
    WriteFiles fileWriter = new WriteFiles();

    //        -------- FILE WALKER -------
    //creates file-walker object
    FileWalker fw = new FileWalker();

    //walks the files
    Files.walkFileTree(startPath, fw);

    StudyGuide studyGuideFile = new StudyGuide(this.startPath, this.sortType, this.endPathMd);
    QuestionBank flashCardFile = new QuestionBank(startPath, this.endPathSr);

    //creates string builder object and extracts headers and bullets
    //ignores :::
    StringBuilder fileContentMd = studyGuideFile.formatFile(fw);
    StringBuilder fileContentSr = flashCardFile.formatFile(fw);

    //calls method to write to a temporary file so that it can be read again
    fileWriter.writeToFile(tempPath, fileContentMd);
    fileWriter.writeToFile(tempPath, fileContentSr);

    //writes the two files (both .sr and .md)
    fileWriter.writeToFile(endPathMd, studyGuideFile.fixFile(tempPath));
    fileWriter.writeToFile(endPathSr, flashCardFile.fixFile(tempPath));
    Files.delete(tempPath);
  }
}
