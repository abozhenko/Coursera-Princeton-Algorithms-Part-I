import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Andrew on 2/15/2017.
 */
public class BruteCollinearPoints {

    /**
     * finds all line segments containing 4 points
     *
     * @param points list of input points
     */
    public BruteCollinearPoints(Point[] points) {

    }

    /**
     * @return the number of every (maximal) line segments that connects a subset
     *         of 4 or more of the points
     */
    public int numberOfSegments() {
        return segments().length;
    }

    /**
     * @return collection of  every (maximal) line segments that connects a subset
     *         of 4 or more of the points
     */
    public LineSegment[] segments() {
        throw new NotImplementedException();
    }
}
