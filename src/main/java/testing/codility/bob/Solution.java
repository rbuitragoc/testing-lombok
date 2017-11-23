package testing.codility.bob;


import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * @author rick.
 */
public class Solution {

    public int solution(Point2D[] a) {
        double[] slopes = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            slopes[i] = slope(new Point2D(0, 0), a[i]);
        }
        System.out.println(String.format("Slopes! %s", Arrays.toString(slopes)));
        return (int) DoubleStream.of(slopes).distinct().count();
    }

    private double slope(Point2D p1, Point2D p2) {
        return (double) (p2.y - p1.y) / (p2.x - p1.x);
    }

    static class Point2D {
        int x;
        int y;
        public Point2D(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    public static void main(String ... args) {
        Point2D[] points = new Point2D[]{
                new Point2D(-1, 2),
                new Point2D(1, 2),
                new Point2D(2, 4),
                new Point2D(-3, 2),
                new Point2D(2, -2),
                new Point2D(-2, -4),
                new Point2D(4, 8),
                new Point2D(-3, 2),
                new Point2D(3, -3)
        };
        int s = new Solution().solution(points);
        System.out.println("Solution is " + s);
    }
}
