package testing.codility.arrayofdecimals;

import testing.codility.ArrayHelper;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * <div id="brinza-task-description">
 <p>A non-empty zero-indexed array A consisting of N integers is given. This array contains a decimal representation of a number V, i.e. element A[K] contains the K-th least significant digit of the decimal representation of V.</p>
 <p>For example, array A such that:</p>
 <tt style="white-space:pre-wrap">  A[0] = 3
 A[1] = 5
 A[2] = 1</tt>
 <p>represents the number V = 153.</p>
 <p>Write a function:</p>
 <blockquote><p style="font-family: monospace; font-size: 9pt; display: block; white-space: pre-wrap"><tt>class Solution { public int solution(int[] A); }</tt></p></blockquote>
 <p>that, given an array A consisting of N integers specifying a decimal representation of a number V, returns the sum of the digits in the decimal representation of the number 17*V.</p>
 <p>For example, given array A such that:</p>
 <tt style="white-space:pre-wrap">  A[0] = 3
 A[1] = 5
 A[2] = 1</tt>
 <p>the function should return 9, because:</p>
 <blockquote><ul style="margin: 10px;padding: 0px;"><li>array A represents the number 153,</li>
 <li>17 * 153 = 2601,</li>
 <li>the sum of the digits in the decimal representation of 2601 is 2 + 6 + 0 + 1 = 9.</li>
 </ul>
 </blockquote><p>Assume that:</p>
 <blockquote><ul style="margin: 10px;padding: 0px;"><li>N is an integer within the range [<span class="number">1</span>..<span class="number">1,000,000</span>];</li>
 <li>each element of array A is an integer within the range [<span class="number">0</span>..<span class="number">9</span>].</li>
 </ul>
 </blockquote><p>Complexity:</p>
 <blockquote><ul style="margin: 10px;padding: 0px;"><li>expected worst-case time complexity is O(N);</li>
 <li>expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).</li>
 </ul>
 </blockquote><p>Elements of input arrays can be modified.</p>
 </div>
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
