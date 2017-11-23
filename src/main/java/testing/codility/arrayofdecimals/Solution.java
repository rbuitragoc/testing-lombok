package testing.codility.arrayofdecimals;

import testing.codility.ArrayHelper;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author rick.
 */
public class Solution extends ArrayHelper {

    public int solution(int[] a) {
        // first, obtain the number. Iterate the array, multiplying by 10^x
        BigDecimal v = BigDecimal.valueOf(0);
        // then, multiply times 17
        BigDecimal m = BigDecimal.valueOf(0);
        // then iterate and sum
        int r = 0;

        for (int x = 0; x < a.length; x++) {
            v = v.add(BigDecimal.valueOf(a[x] * Math.pow(10, x)));
        }
        System.out.println("V=" + v);
        m = v.multiply(BigDecimal.valueOf(17));
        System.out.println("M=" + m);
        String s = m.toBigIntegerExact().toString();
        System.out.println("S=" + s);
        for (Character c: s.toCharArray()) {
            System.out.println(c);
            r += Integer.parseInt(String.valueOf(c));
        }
        return r;
    }

    public static void main(String ... args) {
        int[] array = generateRandomPrimitiveIntArray(200, 0, 9);
        System.out.println("Array: " + Arrays.toString(array));
        int s = new Solution().solution(array);
        System.out.println("Solution: " + s);
    }
}
