package testing.twilio.substring;

import java.util.Arrays;

/**

 * @author rick.
 */
public class Solution {

    public static void main(String ... args) {
        Solution sol = new Solution();
        String s = "I am using hackerrank for improving my skills";
        String t = "am hackerrank improving ";
        String[] missing = sol.missingWords(s, t);
        System.out.println(
                String.format(
                        "Given this phrase: '%s' \n and this substring: '%s'\n These are the missing words: %s",
                        s,
                        t,
                        Arrays.toString(missing))
        );
    }

    public static String[] missingWords(String s, String t) {
        String [] sSplitted = s.split("\\s");
        String[] tSplitted = t.split("\\s");
        StringBuilder sb = new StringBuilder();
        for (String word: sSplitted) {
            if (!inSubstring(word, tSplitted)) {
                sb.append(word).append(" ");
            }
        }
        return sb.toString().trim().split("\\s");
    }

    private static boolean inSubstring(String word, String[] tSplitted) {
        for (String sword: tSplitted) {
            if (word.equalsIgnoreCase(sword)) {
                return true;
            }
        }
        return false;
    }
}
