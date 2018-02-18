package testing.codility.frog;

/**
 * A small frog wants to get to the other side of the road. The frog is currently located at position X and wants to get
 * to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.

 Count the minimal number of jumps that the small frog must perform to reach its target.

 Write a function:

 class Solution { public int solution(int X, int Y, int D); }

 that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or
 greater than Y.

 For example, given:

 X = 10
 Y = 85
 D = 30
 the function should return 3, because the frog will be positioned as follows:

 after the first jump, at position 10 + 30 = 40
 after the second jump, at position 10 + 30 + 30 = 70
 after the third jump, at position 10 + 30 + 30 + 30 = 100
 Assume that:

 X, Y and D are integers within the range [1..1,000,000,000];
 X ≤ Y.
 Complexity:

 expected worst-case time complexity is O(1);
 expected worst-case space complexity is O(1).
 * @author rick.
 */
public class Solution {

    public static void main(String ... args) {
        Solution s = new Solution();
        int x = 7;
        int y = 11;
        int d = 3;
        System.out.println(
                String.format("Frog that leaps %sm will need to jump at least %d times move from %d and at least to %d",
                        d, s.solution(x, y, d), x, y));
    }

    public int solution(int x, int y, int d) {
        int totalDistance = y - x;
        int exactLeaps = totalDistance / d;
        return (totalDistance % d > 0) ? exactLeaps + 1 : exactLeaps;
    }

}
