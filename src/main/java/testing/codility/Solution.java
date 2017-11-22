package testing.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author rick.
 */
public class Solution {

    /**
     * Find the smallest positive integer not present in a given integer (primitive) array.
     * Solution must assume that:
     * Array can have arbitrarily large size (N=100000). Array elements may range between -1M and 1M.
     * The result integer can grow up to 1000000
     * @param args
     */
    public static void main(String ... args) {
        for (int i = 0; i < 5; i++) {
            harness();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void harness() {
        Solution s = new Solution();
        int[] inputManual = new int[]{400, 99900, 3, 16, -200, 5, 6, 7, 8, 9, 10, 4, 1, 2, -55, 7, 9};
        int[] input = s.generateRandomPrimitiveIntArray();
        System.out.println(String.format("Random array generated: %s", Arrays.toString(input)));
        long start = System.currentTimeMillis();
        System.out.println(String.format("Getting solutionWithOn2: %s", s.solutionWithOn2(input)));
        long end = System.currentTimeMillis();
        System.out.println(String.format("(Took: %s ms)", end-start));
        start = System.currentTimeMillis();
        System.out.println(String.format("Getting solution: %s", s.solution(input)));
        end = System.currentTimeMillis();
        System.out.println(String.format("(Took: %s ms)", end-start));
        start = System.currentTimeMillis();
        System.out.println(String.format("Getting (parallel) solution: %s", s.parallelismSolution(input)));
        end = System.currentTimeMillis();
        System.out.println(String.format("(Took: %s ms)\n", end-start));
    }

    public int solutionThatFailsAtMinusOne(int[] a) {
        ArrayList<Integer> saved = new ArrayList<>();
        for (int x: a) {
            if (x < 0) {
                continue;
            }
            if (IntStream.of(a).noneMatch((i) -> i == (x + 1))) {
                saved.add(x + 1);
            }
        }
        return Collections.min(saved);
    }

    public int solutionTooMuchMemoryUsed(int[] a) {
        ArrayList<Integer> saved = new ArrayList<>();
        for (int i = 1; i < 100000; i++) {
            final int I = i;
            if (IntStream.of(a).noneMatch(x -> x == I)) {
                saved.add(i);
            }
        }
        System.out.println(String.format("Saved collection: %s", saved));
        return Collections.min(saved);
    }

    public int solutionWithOn2(int[] a) {
        for (int i = 1; i < 1000000; i++) {
            final int I = i;
            if (IntStream.of(a).noneMatch(x -> x == I)) {
                return i;
            }
        }
        return 0;
    }

    public int solution(int[] a) {
        return solution(a, false);
    }

    public int parallelismSolution(int[] a) {
        return solution(a, true);
    }

    public int solution(int[] a, boolean executeInParallel) {
        if (a == null) {
            return 0;
        } else if (a.length == 0) {
            return 1;
        }

        IntStream intStream = IntStream.rangeClosed(1, 100000);

        if (executeInParallel) {
            intStream = intStream.parallel();
        }

        return intStream
                .filter(i -> IntStream.of(a).noneMatch(x -> x == i))
                .findFirst()
                .getAsInt();
    }

    public int[] generateRandomPrimitiveIntArray() {
        int size = new Random().nextInt(300);
        final int min = -10;
        final int max = 75;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(min, max);
        }
        return array;
    }

}
