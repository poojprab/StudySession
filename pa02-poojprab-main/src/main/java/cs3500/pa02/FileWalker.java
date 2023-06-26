package cs3500.pa02;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


/**
 * FROM PAO1:
 * Walks through a given file directory/path and stores .md files
 */
public class FileWalker implements FileVisitor<Path> {
  /**
   * arraylist to store all markdown files and their attributes
   *
   */
  private ArrayList<MarkDownFile> listOfMdFiles = new ArrayList<>();

  // the following string is for testing (see @FileWalkerTest)
  public String file1 = null;
  /**
   * boolean to test whether the walk file methods are called before the get list method
   *
   */
  private boolean beforeFileWalker = true;

  /**
   * Adds file to the hashmap if the file is a .md file
   *
   * @param file a reference to the file
   * @param attr the file's basic attributes
   * @return FileVisitResult tells FileWalker what to do with given file
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    if (attr.isRegularFile()) {
      if (file.getFileName().toString().contains(".md")) {
        listOfMdFiles.add(new MarkDownFile(attr.creationTime(),
            file.toString(),
            file.toFile().lastModified()));
      }
    }
    beforeFileWalker = false;

    return CONTINUE;
  }

  /**
   * Continues to the next directory
   *
   * @param dir  a reference to the directory
   * @param exec handles potential exceptions
   * @return FileVisitResult tells FileWalker what to do with given file
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    beforeFileWalker = false;
    return CONTINUE;
  }

  /**
   * Continues parsing through the directory's files
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return FileVisitResult
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) {
    beforeFileWalker = false;
    return CONTINUE;
  }

  /**
   * Sends a message error if the file given does not exist
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return FileVisitResult tells FileWalker what to do with given file
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    beforeFileWalker = false;
    file1 = (this.fileFailedMessage());
    return CONTINUE;
  }

  /**
   * This class is used for testing only see @FileWalkerTest
   * signals to the test that the visitFileFailed method was called
   *
   * @return String the file failed message
   */
  public String fileFailedMessage() {
    return "file not found";
  }

  /**
   * gets the hashmap created in fileWalker class
   *
   * @return HashMap A hashmap to be used when sorting the files
   */
  public ArrayList<MarkDownFile> getListOfMdFiles() {
    if (beforeFileWalker) {
      throw new IllegalStateException("getList called before fileWalker");
    }
    return listOfMdFiles;
  }
}
