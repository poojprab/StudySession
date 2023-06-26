package cs3500.pa02;

import cs3500.pa02.reader.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that writes a given string into a file.
 */
public class StudyGuide extends FileFormatter {
  /**
   * Ordering type to store the way the files should be sorted
   */
  private final OrderingType sortType;


  /**
   * A constructor for StudyGuide
   *
   * @param startPath path to start walking from
   * @param sortType a type of sort to sort the files
   * @param endPath a path to write to
   */
  public StudyGuide(Path startPath, OrderingType sortType, Path endPath) {
    super(startPath, endPath);
    this.sortType = sortType;
  }

  /**
   * Formats the file by the rules of the assignment
   *
   * @param fw A FileWalker needed to access a list of .md files
   * @return A string builder that is formatted and will be written to a temporary file.
   * @throws IOException handles potential exceptions
   */
  @Override
  public StringBuilder formatFile(FileWalker fw) throws IOException {
    //returns the completely sorted lists
    ArrayList<Path> sortedFiles = new Sort(sortType).sortBy(fw.getListOfMdFiles());
    //string builder to append every file strings onto
    StringBuilder studyGuideStrings = new StringBuilder();

    //appends each file together
    for (Path f : sortedFiles) {
      studyGuideStrings.append(new FileReader(f).read());
    }

    return studyGuideStrings;
  }

  /**
   * Fixes the file into either an .md or .sr file representing the study guide and question bank
   * respectively.
   *
   * @param tempPath Represents a path to a file that is temporarily written then deleted. Its
   *                 contents will be used before deletion.
   * @return A String builder representing the fixed file.
   * @throws IOException handles potential exceptions
   */
  @Override
  public StringBuilder fixFile(Path tempPath) throws IOException {
    //creates scanner
    Scanner input = new Scanner(tempPath);

    //string builder
    StringBuilder content = new StringBuilder();

    while (input.hasNextLine()) {
      String currentLine = input.nextLine();

      //if contains a header (has a #)
      if (!currentLine.contains(":::") && currentLine.contains("#")) {
        content.append("\n").append(currentLine).append("\n");
      } else if (!currentLine.contains(":::") && currentLine.contains("-")) {
        content.append(currentLine).append("\n");
      }
    }
    return content;
  }
}
