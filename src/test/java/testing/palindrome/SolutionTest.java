package testing.palindrome;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
  
  private static Solution underTest;
  
  @BeforeAll
  static void setUp() {
    underTest = new Solution();
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"anita lava la tina", "dabale arroz a la zorra el abad", "axxa"})
  void checkPalindrome(String word) {
    boolean actualResult = underTest.checkPalindrome(word);
    assertTrue(actualResult);
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"any phrase that's not a palindrome", "longword", "or just a short one"})
  void checkNonPalindrome(String word) {
    boolean actualResult = underTest.checkPalindrome(word);
    assertFalse(actualResult);
  }
}