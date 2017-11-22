package testing.codility.streams;

import testing.codility.ArrayHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author rick.
 */
public class StreamsPractice extends ArrayHelper {

    public static void main(String... args) {
        harness();
    }

    public static void harness() {
        StreamsPractice s = new StreamsPractice();
        s.negativeAirTemperature();
        int[] input = generateRandomPrimitiveIntArray(20, -60, 50);
        System.out.println(String.format("Reversed array (%s): %s", Arrays.toString(input), Arrays.toString(s.reverse(input))));
    }

    public void synchronizedSetTest() {
        StreamsPractice sp = new StreamsPractice();
        Set<Integer> seen = Collections.synchronizedSet(new HashSet<Integer>());
        Boolean isPresent = seen.stream().map(e -> {
            if (seen.add(e)) return 0;
            else return e;
        }).findFirst().isPresent();
        System.out.println(String.format("Seen integer? %s", isPresent));
    }

    /**
     * Prints the negative air temperature reading count from an arbitrary int[] containing temperature readings.
     */
    public void negativeAirTemperature() {
        int[] temperatures = generateRandomPrimitiveIntArray(20, -20, 200);
        long result = IntStream.of(temperatures).filter(t -> t < 0).count();
        System.out.println(String.format("Temperature readings below zero from the set (%s): %d", Arrays.toString(temperatures), result));
    }

    /**
     * Returns the reverse an array of integers of arbitrary length
     * @param a an int[]
     * @return a reversed int[] containing the elements of a but in reverse order
     */
    public int[] reverse(int[] a) {
        int[] r = new int[a.length];
        for (int i = a.length - 1, n = 0; i >= 0; i--, n++) {
            r[n] = a[i];
        }
        return r;
    }
}
