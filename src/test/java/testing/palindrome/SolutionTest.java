package testing.palindrome;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {
  
  private static Solution underTest;
  
  @BeforeAll
  static void setUp() {
    underTest = new Solution();
    Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    root.setLevel(Level.TRACE);
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"anita lava la tina", "dabale arroz a la zorra el abad", "amar a la rama"})
  void checkSpanishPalindrome(String palabra) {
    assertTrue(underTest.checkPalindrome(palabra));
  }
  
  @ParameterizedTest
  @MethodSource("getEnglishPalindromes")
  void checkEnglishPalindrome(String word) {
    assertTrue(underTest.checkPalindrome(word));
  }
  
  @ParameterizedTest
  @CsvSource({"anything", "realize", "40912"})
  void checkNonPalindrome(String word) {
    assertFalse(underTest.checkPalindrome(word));
  }
  
  // source factory method
  private static Stream<Arguments> getEnglishPalindromes() {
    return Stream.of(Arguments.of("racecar"), Arguments.of("radar"), Arguments.of("able was I ere I saw elba"));
  }
  
}