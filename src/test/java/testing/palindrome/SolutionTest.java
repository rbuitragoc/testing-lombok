package testing.palindrome;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {
  
  private static Solution underTest;
  
  @BeforeAll
  static void setUp() {
    underTest = new Solution();
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"anita lava la tina", "dabale arroz a la zorra el abad", "axxa"})
  void checkSpanishPalindrome(String palabra) {
    assertTrue(underTest.checkPalindrome(palabra));
  }
  
  @ParameterizedTest
  @MethodSource("getEnglishPalindromes")
  void checkEnglishPalindrome(String word) {
    assertTrue(underTest.checkPalindrome(word));
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"any phrase that's not a palindrome", "longword", "or just a short one"})
  void checkNonPalindrome(String word) {
    assertFalse(underTest.checkPalindrome(word));
  }
  
  // source factory method
  private static Stream<Arguments> getEnglishPalindromes() {
    return Stream.of(Arguments.of("racecar"), Arguments.of("radar"), Arguments.of("able was I ere I saw elba"));
  }
  
}