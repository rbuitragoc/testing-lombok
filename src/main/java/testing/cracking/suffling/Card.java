package testing.cracking.suffling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
  Suit suit;
  Rank rank;
  @Override
  public String toString() {
    return String.format("%s(%s)", rank, suit);
  }
}