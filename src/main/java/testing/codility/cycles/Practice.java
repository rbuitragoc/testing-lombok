package testing.codility.cycles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author rick.
 */
public class Practice {

    public static void main(String ... args) {
        Practice p = new Practice();
        int input = 10;
        System.out.println(String.format("Factorial of %s (%s!) using parallel streams: %s\n",
                input, input, p.factorial(input)));
        System.out.println(String.format("Rectangle Triangle of base %s:%s", input, p.rectangleTriangle(input)));
        System.out.println(String.format("Upside down Triangle of %s levels: \n\n%s",
                input, p.upsideDownTriangle(input)));
        input *= 3;
        System.out.println(String.format("Fibonacci sequence to the number lesser than %s: \n\n%s",
                input, Arrays.toString(p.fibonacci(input))));
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

    /**
     * Print a triangle made of asterisks separated by spaces and consisting of n rows again,
     * but this time upside down, and make it symmetrical. Consecutive rows have 2n - 1, 2n - 3, ..., 3, 1 asterisks
     * and should be indented by o, 2, 4, ..., 2(n - 1) spaces.
     * @param n
     * @return
     */
    private String upsideDownTriangle(int n) {
        StringBuilder buf = new StringBuilder();
        for (int i = n; i > 0; i--) {
            for (int s = 0; s < (n - i); s++) {
                buf.append("  ");
            }
            for (int a = 1; a < 2 * i; a++) {
                buf.append("* ");
            }

            buf.append("\n");
        }
        return buf.toString();
    }

    /**
     * Prints the fibonacci sequence for numbers smaller than n
     * @param n the limit to the fibonacci sequence
     * @return a sorted array where all elements are the integers of the fibonacci sequence up to n
     */
    private Integer[] fibonacci(int n) {
        ArrayList<Integer> fib = new ArrayList<>();
        int c = 1;
        int b = 0;
        int a = 0;

        fib.add(0);

        while (c < n) {
            fib.add(c);
            a = b;
            b = c;
            c = b + a;
        }
        return fib.toArray(new Integer[fib.size()]);
    }
}
