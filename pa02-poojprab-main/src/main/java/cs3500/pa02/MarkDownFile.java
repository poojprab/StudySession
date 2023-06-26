package cs3500.pa02;

import java.nio.file.attribute.FileTime;

/**
 * FROM PA01:
 * TESTER CLASS representing a MarkDown File
 */
public class MarkDownFile {
  /**
   * A file time to represent the time a markdown file is created at
   *
   */
  private final FileTime createdAt;
  /**
   * a string name to represent the path name of the markdown file
   *
   */
  private final String pathName;

  /**
   * a long to represent the time a markdown file was modified
   *
   */
  private final Long modifiedTime;

  /**
   * a constructor to store the markdown files time created at, modified, and path name
   *
   * @param createdAt A file time to represent the time a markdown file is created at
   * @param pathName A string name to represent the path name of the markdown file
   * @param modifiedTime A long to represent the time a markdown file was modified
   */
  public MarkDownFile(FileTime createdAt, String pathName, Long modifiedTime) {
    this.createdAt = createdAt;
    this.pathName = pathName;
    this.modifiedTime = modifiedTime;
  }

  public FileTime getCreatedAt() {
    return this.createdAt;
  }

  public String getPathName() {
    return this.pathName;
  }

  public Long getModifiedTime() {
    return this.modifiedTime;
  }
}
