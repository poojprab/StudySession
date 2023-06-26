package cs3500.pa02;

/**
 * Enumeration to represent possible user inputs
 */
public enum AnswerType {
  /**
   * Value representing the input in which the user wants to exit
   */
  EXIT("X"),
  /**
   * Value representing the input in which the user wants to see the answer
   */
  ANSWER("A"),
  /**
   * Value representing the input in which the user wants to mark as hard
   */
  HARD("H"),
  /**
   * Value representing the input in which the user wants to mark as easy
   */
  EASY("E");

  /**
   * The string value associated with the Enum
   */
  private final String value;

  /**
   * Constructor for AnswerType
   *
   * @param v String value associated with the enum
   */
  AnswerType(String v) {
    this.value = v;
  }

  /**
   * Gets the value of the Enum
   *
   * @return String with the associated value of the enum
   */
  public String getValue() {
    return this.value;
  }
}

