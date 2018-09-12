package testing.cracking.suffling;

import lombok.Data;

public enum Suit {
  clubs("♣"), diamonds("♦"), hearts("♥"), spades("♠");
  private String symbol;

  Suit(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return this.symbol;
  }
}