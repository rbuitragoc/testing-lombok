package testing.codility.binarygap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author rick.
 */
public class MySolution {

    public int solution(int n) {
        return findBinaryGap(toBinary(n));
    }

    public int findBinaryGap(String binary) {
        List<Integer> gaps = new ArrayList<>();
        int zeroCount = 0;
        boolean gotHeading = false;
        for (char c: binary.toCharArray()) {
            if (c == '1') {
                if (zeroCount > 0) {
                    gaps.add(zeroCount);
                    zeroCount = 0;
                }
                gotHeading = true;
            }
            if (c == '0') {
                if (gotHeading) {
                    zeroCount++;
                }
            }
        }

        return gaps.isEmpty() ? 0 : Collections.max(gaps);
    }

    private static String toBinary(int n) {
        int  bin[] = new int[100];
        int i = 0;
        while (n > 0) {
            bin[i++] = n % 2;
            n = n / 2;
        }
        StringBuilder buf = new StringBuilder();
        for (int j = i - 1; j >= 0; j--) {
            buf.append(bin[j]);
        }
        return buf.toString();
    }

    public static void main(String ... args) {
        MySolution my = new MySolution();
        int[] tests = new int[]{1041, 15, 4569, 62345, 616};
        IntStream.of(tests).forEach(n -> {System.out.println(
                String.format("Decimal %s? Got my solution! Its binary (%s) has this max gap: %s",
                n, toBinary(n), my.solution(n))); });
    }
}
