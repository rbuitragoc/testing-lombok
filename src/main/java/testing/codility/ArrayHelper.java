package testing.codility;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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


}
