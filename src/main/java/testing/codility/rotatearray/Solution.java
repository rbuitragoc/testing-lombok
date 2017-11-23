package testing.codility.rotatearray;

import testing.codility.ArrayHelper;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author rick.
 */
public class Solution extends ArrayHelper {

    public int[] solution(int[] a, int k) {
        // possible opt: empty array
        if (a.length == 0) {
            return a;
        }

        // possible opt: same element in array
        if (IntStream.of(a).allMatch((n) -> n == a[0])) {
            return a;
        }

        // possible opt: k = x(a.length), x > 0; the output is a
        if (k >= a.length && k % a.length == 0) {
            return a;
        }

        // another possible optimization: if k > a.length, reduce k as many times as it multiplies a.length
        if (k > a.length && k % a.length != 0) {
            k = k % a.length;
            System.out.println(String.format("reduced k to %s", k));
        }

        int[] positions = IntStream.range(0, a.length).toArray();
        System.out.println(String.format("Original positions of %s elements: %s", a.length, Arrays.toString(positions)));
        int[] r = Arrays.copyOf(a, a.length);
        for (int i = 0, l = a.length; i < k; i++, l--) {
            // need to reset when right-left counter reaches start of array
            if (l == 0) {
                k -= i; // decreasing the limit of iterations, avoiding infinite loop,
                l = a.length; // resetting right-left counter and,
                i = 0; // resetting left-right counter
            }
            positions = IntStream.concat(
                    IntStream.range(i + 1, l + i),
                    IntStream.range(0, i + 1)).toArray();
            System.out.println(Arrays.toString(positions));
        }
        for (int x = 0; x < a.length; x++) {
            r[x] = a[positions[x]];
        }
        return r;
    }

    public static void main(String ... args) {
        int min = 0, max = 100, k = 37;//ThreadLocalRandom.current().nextInt(min, max);
        int[] input = new int[]{1,2,3,4,5,6,7,8,9};//generateRandomPrimitiveIntArray(100, -1000, 1000);
        int[] result = new Solution().solution(input, k);
        System.out.println(String.format("Array %s\n... rotated %s times\n... looks like this: %s",
                Arrays.toString(input), k, Arrays.toString(result)));
    }
}
