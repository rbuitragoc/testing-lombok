package testing.palindrome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.ArrayDeque;

/**
 * @author rick.
 */
@Slf4j
public class Solution {
  
  /**
   * Checks a non-null String for palindrome.
   * @param word the input String instance against which the palindrome check is to be performed
   * @return true, if the input String is palindrome, or false otherwise
   */
  public boolean checkPalindrome(String word) {
    log.debug("checking palindrome for word '{}'", word);
    // null check á la Spring
    Assert.notNull(word, "assumed word not a null instance!");
    // compressing
    String compressedWord = word.replaceAll("\\s", "");
    // will use Deque to compare head and tail until it's either non palindrome
    // or palindrome under one out of two scenarios: empty list or one single character
    ArrayDeque<Character> deque = new ArrayDeque<>();
    for (char c: compressedWord.toCharArray()) {
       deque.add(c);
    }
    log.trace("Populated deque: {}", deque);
    while (!deque.isEmpty() ) {
      if (deque.size() == 1) {
        return true;
      }
      Character first = deque.removeFirst();
      Character last = deque.removeLast();
      if (first.charValue() != last.charValue()) {
        return false;
      }
      log.trace("one round and deque looks shorter! {}", deque);
    }
    return true;
  }
}
