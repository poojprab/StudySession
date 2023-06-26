package cs3500.pa02;

import cs3500.pa02.reader.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles the creation of the sr file
 */
public class QuestionBank extends FileFormatter {

  public QuestionBank(Path startPath, Path endPath) {
    //add the file name to studyFile path
    super(startPath, endPath);
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

    //gets the paths of all the md files (no sort needed)
    ArrayList<MarkDownFile> mdFiles = fw.getListOfMdFiles();
    ArrayList<Path> pathFiles = new ArrayList<>();
    for (MarkDownFile mdf : mdFiles) {
      pathFiles.add(Path.of(mdf.getPathName()));
    }

    //string builder to append every file strings onto
    StringBuilder flashCardStrings = new StringBuilder();

    //appends each file together
    for (Path f : pathFiles) {
      flashCardStrings.append(new FileReader(f).read());
    }

    return flashCardStrings;
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
      if (currentLine.contains(":::")) {
        content.append(currentLine).append(" &&HARD&&").append("\n");
      }
    }
    return content;
  }
}
