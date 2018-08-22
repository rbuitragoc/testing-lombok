package testing.cracking.suffling;

/**
 * @author rick
 *
 */
public enum Rank {
  ace("a"), two("2"), three("3"), four("4"), five("5"), six("6"), seven("7"), eight("8"), nine(
      "9"), ten("10"), jack("j"), queen("q"), king("k");
  private String face;

  private Rank(String value) {
    face = value;
  }

  public String getFace() {
    return face;
  }
  
}
