package cs3500.pa02;

import java.util.Comparator;

/**
 * FROM PAO1:
 * A class that implements a comparator that can be used to sort by name.
 */
public class SortByName implements Comparator<MarkDownFile> {
  /**
   * Compares two Path names and returns a numeric value representing which comes first
   *
   * @param mdf1 the first object to be compared.
   * @param mdf2 the second object to be compared.
   * @return int the number that denotes which filetime comes first
   */
  @Override
  public int compare(MarkDownFile mdf1, MarkDownFile mdf2) {
    return mdf1.getCreatedAt().compareTo(mdf2.getCreatedAt());
  }
}
