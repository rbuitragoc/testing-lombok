package testing.codility.giraffes;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author rick.
 */
public class Solution {
    public int solution(int[] a) {
        int groups = 0;
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
            if (i == a.length && a[i] > a[i - 1] ) {
                groups++;
            }
            if (i < a.length - 1 && a[i] < a[i + 1]) {
                groups++;
            }
        }
        return groups;
    }

    public static void main(String ... args) {
        int[] array = new int[]{4, 3, 2, 6, 1};//generateRandomPrimitiveIntArray(200, 0, 9);
        System.out.println("Array: " + Arrays.toString(array));
        int s = new Solution().solution(array);
        System.out.println("Solution: " + s);
    }
}
