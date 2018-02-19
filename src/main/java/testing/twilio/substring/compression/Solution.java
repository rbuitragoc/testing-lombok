package testing.twilio.substring.compression;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author rick.
 */
public class Solution {

    public static void main(String ... args) {
        Solution sol = new Solution();
        String message = "aaaammmmmmtielllllaaodopeoifpppppaifmnimiiidjgcasdsssssss";
        String s = sol.compressedString(message);
        System.out.println(String.format(
                "Given the following string: %s\n the compressed version of it is: %s\n",
                message,
                s)
        );
    }

    static String compressedString(String message) {
        StringBuilder sb = new StringBuilder();
        char last = '_';
        int count = 0;
        boolean mustPrint = false;
        char[] asArray = message.toCharArray();
        for (int i = 0; i < asArray.length; i++) {
            char c = asArray[i];
            if (c != last) {
                if (count > 1)
                    sb.append(count);
                sb.append(c);
            }
            count = (c != last) ? 1 : count + 1;
            if (i == asArray.length - 1 && count > 1)
                sb.append(count);

            last = (char) c;
        }
        return sb.toString();
    }

}
