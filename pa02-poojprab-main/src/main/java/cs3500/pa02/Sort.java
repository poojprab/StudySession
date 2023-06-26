package cs3500.pa02;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * FROM PAO1:
 * Sorts the given files by given sort type, then returns
 * the re-formatted files in a single String.
 */
public class Sort {
  /**
   * an ordering type to store the way the files should be sorted
   */
  private final OrderingType sortType;

  /**
   * a constructor to store values of sort type and starting path for the file walker
   *
   * @param type an ordering type to store the way the files should be sorted
   */
  public Sort(OrderingType type) {
    this.sortType = type;
  }

  /**
   * Sorts a list of files by the specified string in the command line
   *
   * @param mdf contains a hashmap that associates files to their times created
   * @return an arraylist that contains a list of paths with files to be read
   */
  public ArrayList<Path> sortBy(ArrayList<MarkDownFile> mdf) {

    ArrayList<Path> temp = new ArrayList<>();
    //if statements for different sorts
    if (sortType.equals(OrderingType.FILENAME)) {
      mdf.sort(new SortByName());
    } else if (sortType.equals(OrderingType.CREATED)) {
      mdf.sort(new SortByName());
    } else if (sortType.equals(OrderingType.MODIFIED)) {
      mdf.sort(new SortByName());
    }
    for (MarkDownFile m : mdf) {
      temp.add(Path.of(m.getPathName()));
    }
    return temp;
  }

}
