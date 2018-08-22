package testing.cracking.suffling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
  Suit suit;
  Rank rank;
}