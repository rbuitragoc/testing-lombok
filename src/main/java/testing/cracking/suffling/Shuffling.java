package testing.cracking.suffling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Write a method to shuffle a deck of cards. It must be a perfect shuffle - in other words, each
 * 52! permutations of the deck has to be equally likely. Assume that you are given a random number
 * generator which is perfect.
 * 
 * @author rick
 *
 */
public class Shuffling {

  public static List<Card> createDeck() {
    List<Card> cards = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card card = new Card(suit, rank);
        cards.add(card);
      }
    }
    return cards;
  }
  
  public static List<Card> shuffleWithBlindRandom(Deck deck) {
    Set<Card> shuffled = Collections.synchronizedSet(new HashSet<Card>());
    for (int i = 0; i < Deck.SIZE; i++) {
      int position = ThreadLocalRandom.current().nextInt(0, Deck.SIZE);
      shuffled.add(deck.getCards().get(position));
    }
    return Arrays.asList(shuffled.toArray(new Card[shuffled.size()]));
  }
  
  /**
   * WIP. Works but has a problem: it reduces the set of cards returned.
   * @param deck
   * @return
   */
  public static List<Card> shuffleWithDeadCard(Deck deck) {
    List<Card> shuffled = new ArrayList<>(deck.getCards());
    for (int i = 0; i < deck.getCards().size(); i++) {
      int position = (int) (Math.random() * (deck.getCards().size() - i)) + i;
      Card temp = deck.getCards().get(i);
      deck.getCards().set(i, deck.getCards().get(position));
      deck.getCards().set(position, temp);
    }
    return shuffled;
  }

  public static void main(String... strings) {
    Deck deck = new Deck(createDeck());
    System.out.println(deck);
    System.out.println(String.format("Got %s cards!", deck.getCards().size()));
    Deck shuffled = Deck.builder().cards(shuffleWithBlindRandom(deck)).build();
    System.out.println(shuffled);
    System.out.println(String.format("Got %s cards with blind random!", shuffled.getCards().size()));
    Deck shuffledWithDeadCard = Deck.builder().cards(shuffleWithBlindRandom(deck)).build();
    System.out.println(shuffledWithDeadCard);
    System.out.println(String.format("Got %s cards with dead card!", shuffledWithDeadCard.getCards().size()));
  }

}
