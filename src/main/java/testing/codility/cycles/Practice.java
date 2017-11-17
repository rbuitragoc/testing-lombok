package testing.codility.cycles;

import java.util.stream.IntStream;

/**
 * @author rick.
 */
public class Practice {

    public static void main(String ... args) {
        Practice p = new Practice();
        int input = 10;
        System.out.println(String.format("Factorial of %s (%s!) using parallel streams: %s\n", input, input,
                p.factorial(input)));
        System.out.println(String.format("Rectangle Triangle of base %s: \n %s", input, p.rectangleTriangle(input)));

    }

    /**
     * Pretty self explanatory
     * @param n is the number for which we want to calculate factorial function
     * @return factorial function calculated on given n
     * @throws ArithmeticException if at any step of mutiplying n * (n+1) produces a number larger than Integer.MAX_VALUE
     */
    private int factorial(int n) {
        return IntStream.rangeClosed(1, n).parallel().reduce(1, Math::multiplyExact);
    }

    /**
     * Prints a rectangle triangle with the 90 degree angle at the bottom line, thus making legs parallel and orthogonal
     * to the horizon, correspondingly. The base leg measures exactly n asterisks.
     * The horizontal distance between any pair of asterisks is one whitespace character.
     * @param n the size of the base leg of the triangle
     */
    private String rectangleTriangle(int n) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int x = 1; x < i; x++) {
                buf.append("* ");
            }
            buf.append("\n");
        }
        return buf.toString();
    }
}
