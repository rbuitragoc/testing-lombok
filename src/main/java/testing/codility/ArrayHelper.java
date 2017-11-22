package testing.codility;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author rick.
 */
public class ArrayHelper {

    public static int[] generateRandomPrimitiveIntArray() {
        return generateRandomPrimitiveIntArray(300, -10, 75);
    }

    public static int[] generateRandomPrimitiveIntArray(int limit, int min, int max) {
        int size = new Random().nextInt(limit);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(min, max);
        }
        return array;
    }

    /**
     * generating a pseudo-random int[] of paired items, enforcing a single int of odd occurences.
     * @param size the size of the array, plus 1 if the value is even.
     * @return the only element for which an odd number of occurences is included.
     */
    public static int[] generateOddLengthArrayWithUnpairedItem(int size) {
        if (size % 2 == 0) {
            System.out.println("Will augment size in 1 since specified size wasn't odd");
            size += 1;
        }

        int[] base = new int[size];
        int min = 1, max = 1000000000;
        for (int i = 0; i < size - 2; i += 2) {
            base[i] = ThreadLocalRandom.current().nextInt(min, max);
            base[i+1] = base[i];
        }
//        base[size - 1] = new Solution().solution(base); // don't you just love it when you actually reuse code?
        base[size - 1] = base[size - 2]; // enforcing an odd number != 1
        return base;
    }

}
