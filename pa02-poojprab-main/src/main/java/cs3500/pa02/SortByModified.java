package cs3500.pa02;

import java.util.Comparator;

/**
 * FROM PAO1:
 * A class that implements a comparator that can be used to sort by modified time.
 */
public class SortByModified implements Comparator<MarkDownFile> {
  /**
   * Compares two times (last modified times of two paths)
   * and returns a numeric value representing which comes first
   *
   * @param mdf1 the first Long to be compared.
   * @param mdf2 the second Long to be compared.
   * @return int the number that denotes which Long comes first
   */
  @Override
  public int compare(MarkDownFile mdf1, MarkDownFile mdf2) {
    if (mdf1.getModifiedTime() < mdf2.getModifiedTime()) {
      return -1;
    } else if (mdf1.getModifiedTime() > mdf2.getModifiedTime()) {
      return 1;
    }
    return 0;
  }
}
