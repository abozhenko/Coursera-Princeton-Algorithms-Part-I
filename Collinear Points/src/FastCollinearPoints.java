import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

/**
 * Created by Andrew on 2/19/2017.
 */
public class FastCollinearPoints {
    private LineSegment[] segments;

    /**
     * finds all line segments containing 4 points
     *
     * @param points list of input points
     */
    public FastCollinearPoints(Point[] points) {
        verifyInput(points);

        Queue<LineSegment> result = new Queue<LineSegment>();
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point iPoint = points[i];
                        double jSlope = iPoint.slopeTo(points[j]);
                        if (jSlope == iPoint.slopeTo(points[k]) &&
                                jSlope == iPoint.slopeTo(points[l])) {

                            Point[] pts = new Point[]{
                                    points[i], points[j], points[k], points[l]};
                            result.enqueue(getSegment(pts));
                        }
                    }
                }
            }
        }
        segments = new LineSegment[result.size()];
        int p = 0;
        for (LineSegment s: result) {
            segments[p++] = s;
        }
    }

    private void verifyInput(Point[] points) {
        if (points == null) throw new NullPointerException("points argument is nul");

        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException("Points can not be null");
            }
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].equals(points[j])) {
                    throw new IllegalArgumentException(
                            "Input array shouldn't contain duplicates");
                }
            }
        }
    }

    private LineSegment getSegment(Point[] points) {
        Arrays.sort(points);
        return new LineSegment(points[0], points[points.length - 1]);
    }

    /**
     * @return the number of every (maximal) line segments that connects a subset
     *         of 4 or more of the points
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * @return collection of  every (maximal) line segments that connects a subset
     *         of 4 or more of the points
     */
    public LineSegment[] segments() {
        return segments.clone();
    }
}
