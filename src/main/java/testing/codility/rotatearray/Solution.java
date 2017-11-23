package testing.codility.rotatearray;

import testing.codility.ArrayHelper;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * A zero-indexed array A consisting of N integers is given. Rotation of the array means that each element is shifted
 * right by one index, and the last element of the array is moved to the first place. For example,
 * the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]
 * (elements are shifted right by one index and 6 is moved to the first place).
 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
 *
 * Write a function:
 * class Solution { public int[] solution(int[] A, int K); }
 * that, given a zero-indexed array A consisting of N integers and an integer K, returns the array A rotated K times.
 *
 * For example, given
 *
 * A = [3, 8, 9, 7, 6]
 *
 * K = 3
 * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
 * [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 * [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 * [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 *
 * For another example, given
 *
 * A = [0, 0, 0]
 *
 * K = 1
 *
 * the function should return [0, 0, 0]
 *
 * Given
 *
 * A = [1, 2, 3, 4]
 *
 * K = 4
 *
 * the function should return [1, 2, 3, 4]
 *
 * Assume that:
 *
 * N and K are integers within the range [0..100];
 * each element of array A is an integer within the range [−1,000..1,000].
 *
 * @author rick.
 */
public class Solution extends ArrayHelper {

    public int[] solution(int[] a, int k) {
        int[] positions = IntStream.range(0, a.length).toArray();
        System.out.println(String.format("Original positions of %s elements: %s", a.length, Arrays.toString(positions)));

        // possible opt: empty array
        if (a.length == 0) {
            System.out.println("OPT: Empty array, returning");
            return a;
        }

        // possible opt: same element in array
        if (IntStream.of(a).allMatch((n) -> n == a[0])) {
            System.out.println("OPT: All elements in array are the same, returning");
            return a;
        }

        // possible opt: k = x(a.length), x > 0; the output is a
        if (k >= a.length && k % a.length == 0) {
            System.out.println("OPT: k is exact multiple of array size, returning");
            return a;
        }

        // possible optimization: if k > a.length, decrement k exactly to the modulo of multiplying a.length
        if (k > a.length && k % a.length != 0) {
            k = k % a.length;
            System.out.println(String.format("OPT: k is greater than array size, but not exact multiple. Reduced k to %s", k));
        }

        int[] r = Arrays.copyOf(a, a.length);
        for (int i = 0, l = a.length; i < k; i++, l--) {
            // need to reset when right-left counter reaches start of array
            if (l == 0) {
                System.out.println(String.format("Resetting counters, the hit waterlevel: l=%s, k=%s, i=%s", l, k, i));
                System.out.println(Arrays.toString(positions));
                k -= i; // decreasing the limit of iterations, avoiding infinite loop,
                l = a.length; // resetting right-left counter and,
                i = 0; // resetting left-right counter
            }
            positions = IntStream.concat(
                    IntStream.range(i + 1, l + i),
                    IntStream.range(0, i + 1)).toArray();
        }
        for (int x = 0; x < a.length; x++) {
            r[x] = a[positions[x]];
        }
        return r;
    }

    public static void main(String ... args) {
        int min = 0, max = 100, k = ThreadLocalRandom.current().nextInt(min, max);
        int[] input = generateRandomPrimitiveIntArray(100, -1000, 1000);
                //IntStream.generate(()-> {return ThreadLocalRandom.current().nextInt(-1000, 1000);}).limit(max).toArray();
                //new int[]{1,2,3,4,5,6,7};
        long start = System.currentTimeMillis();
        int[] result = new Solution().solution(input, k);
        long end = System.currentTimeMillis();
        System.out.println(String.format("Array %s\n... rotated %s times\n... looks like this: %s\n\nTook %s ms.",
                Arrays.toString(input), k, Arrays.toString(result), end-start));
    }
}
