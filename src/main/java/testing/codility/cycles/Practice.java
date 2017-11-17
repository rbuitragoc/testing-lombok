package testing.codility.cycles;

import java.util.stream.IntStream;

/**
 * @author rick.
 */
public class Practice {

    public static void main(String ... args) {
        int input = 10;
        System.out.println(String.format("Factorial of %s (%s!) using parallel streams: %s", input, input,
                new Practice().factorial(input)));
    }

    private int factorial(int n) {
        return IntStream.rangeClosed(1, n).parallel().reduce(1, Math::multiplyExact);
    }
}
