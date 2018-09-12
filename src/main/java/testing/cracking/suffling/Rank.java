package testing.cracking.suffling;

/**
 * @author rick
 *
 */
public enum Rank {
  ace("A"), two("2"), three("3"), four("4"), five("5"), six("6"), seven("7"), eight("8"), nine(
      "9"), ten("10"), jack("J"), queen("Q"), king("K");
  private String face;

  Rank(String value) {
    face = value;
  }

  public String getFace() {
    return face;
  }

}
