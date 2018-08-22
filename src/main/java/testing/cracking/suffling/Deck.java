package testing.cracking.suffling;

import java.util.List;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Deck {
  
  public static final int SIZE = 52;
  
  @Builder.Default
  List<Card> cards = new ArrayList<>();
}