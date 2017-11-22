package testing.codility.oddoccurrencesinarray;

import testing.codility.ArrayHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rick.
 */
public class Solution {

    public int solution(int[] a) {
        int r = 0;
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int n: a) {
            if (occurrences.containsKey(n)) {
                occurrences.put(n, occurrences.get(n) + 1);
            } else {
                occurrences.put(n, 1);
            }
        }
        return occurrences.keySet().stream().filter(k -> occurrences.get(k) % 2 != 0).findFirst().orElse(0);
    }

    public static void main(String ... args) {
        int[] inputArray = ArrayHelper.generateOddLengthArrayWithUnpairedItem(11);
        System.out.println(String.format("From array (%s), getting only unpaired number: %s", Arrays.toString(inputArray), new Solution().solution(inputArray)));
    }
}
