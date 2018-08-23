package testing.cracking.suffling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.tomcat.util.http.fileupload.util.Streams;

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
   * Complexity: 2 cycles. Space: 1 separate List.
   * @param deck
   * @return
   */
  public static List<Card> shuffleWithDeadCard(Deck deck) {
    List<Card> shuffled = new ArrayList<>(deck.getCards());
    for (int i = 0; i < Deck.SIZE; i++) {
      int position = ThreadLocalRandom.current().nextInt(i, Deck.SIZE);
      Card temp = deck.getCards().get(i);
      Card nowDead = deck.getCards().get(position);
      // swapping...
      shuffled.add(i, nowDead);
      shuffled.add(position, temp);
    }
    return shuffled.stream().distinct().collect(Collectors.toList());
  }
  
  /**
   * Complexity: ignoring the List to Array translation, just 1 cycle. Space: 1 structure (array)
   * @param deck
   * @return
   */
  public static String[] shuffleWithDeadCardUsingArrays(Deck deck) {
    String[] shuffled = new String[Deck.SIZE];
    deck.getCards().stream().map(c -> c.toString()).collect(Collectors.toList()).toArray(shuffled);
    for (int i = 0; i < Deck.SIZE; i++) {
      int position = ThreadLocalRandom.current().nextInt(i, Deck.SIZE);
      String temp = shuffled[i];
      String nowDead = shuffled[position];
      shuffled[i] = nowDead;
      shuffled[position] = temp;
    }
    return shuffled;
  }

  public static void main(String... strings) {
    Deck deck = new Deck(createDeck());
    System.out.println(deck.getCards());
    System.out.println(String.format("- Got %s cards!", deck.getCards().size()));
    
    Deck shuffled = Deck.builder().cards(shuffleWithBlindRandom(deck)).build();
    System.out.println(shuffled.getCards());
    System.out.println(String.format("- Got %s cards with blind random!", shuffled.getCards().size()));
    
    Deck shuffledWithDeadCard = Deck.builder().cards(shuffleWithDeadCard(deck)).build();
    System.out.println(shuffledWithDeadCard.getCards());
    System.out.println(String.format("- Got %s cards with dead card!", shuffledWithDeadCard.getCards().size()));
    
    String[] shuffledWithDeadCardsArray = shuffleWithDeadCardUsingArrays(deck);
    System.out.println(Arrays.toString(shuffledWithDeadCardsArray));
    System.out.println(String.format("- Got %s cards with dead cards array!", shuffledWithDeadCardsArray.length));
  }

}
