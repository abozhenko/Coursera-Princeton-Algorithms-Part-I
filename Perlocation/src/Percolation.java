import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Andrew on 10/16/2016.
 */

public class Percolation {

    private final int size;
    // logical uf, correct fore determining percolation, but has drawback that liquid
    // goes up
    private final WeightedQuickUnionUF logicalUF;
    // is suitable for visualisation as liquid goes only down, but is not appropriate
    // for verification whether system percolates or not as it doe
    // not contain virtual end.
    private final WeightedQuickUnionUF liquidUF;
    private final int startIndex;
    private final int endIndex;
    private final boolean[] openSites;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "Size of the percolation should be positive");
        }
        size = n;
        int flattenSize = n * n + 2; // Flatten array with 2 virtual start and end.
        openSites = new boolean[flattenSize];

        logicalUF = new WeightedQuickUnionUF(flattenSize);
        liquidUF = new WeightedQuickUnionUF(flattenSize -1);
        startIndex = n*n;
        endIndex = n*n + 1;

        openSites[startIndex] = true;
        openSites[endIndex] = true;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowAndCol(row, col);

        int index = getFlattenIndex(row, col);

        if (!openSites[index]) {
            openSites[index] = true;

            int[] neighbors = getNeighborsIndexes(row, col, index);

            for (int neighbor : neighbors) {
                if (openSites[neighbor]) {
                    logicalUF.union(index, neighbor);
                    // avoid virtual end for liquid like union find
                    if (neighbor != endIndex) {
                        liquidUF.union(index, neighbor);
                    }
                }
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowAndCol(row, col);

        int index = getFlattenIndex(row, col);
        return openSites[index];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowAndCol(row, col);
        int index = getFlattenIndex(row, col);
        return liquidUF.connected(startIndex, index);
    }

    // does the system percolate?
    public boolean percolates() {

        return logicalUF.connected(startIndex, endIndex);
    }

    private void validateRowAndCol(int row, int col) {
        validateDimension(row, "Row");
        validateDimension(col, "Col");
    }

    private void validateDimension(int value, String dimensionName) {
        if (value < 1 || value > size) {
            throw new IndexOutOfBoundsException(
                String.format(
                        "%1s parameter is not in range 1..%2d",
                        dimensionName, size));
        }
    }

    private int getFlattenIndex(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    private int[] getNeighborsIndexes(int row, int col, int index) {
        // just to avoid boxing/unboxing
        int[] possibleNeighbors = new int[]{-1, -1, -1, -1};
        int count = 2;

        if (row == 1) {
            possibleNeighbors[0] = startIndex;
        }
        else {
            possibleNeighbors[0] = index - size;
        }

        if (row == size) {
            possibleNeighbors[1] = endIndex;
        }
        else {
            possibleNeighbors[1] = index + size;
        }

        if (col != 1) {
            possibleNeighbors[2] = index - 1;
            count++;
        }
        if (col != size) {
            possibleNeighbors[3] = index + 1;
            count++;
        }

        int[] result = new int[count];
        int i = 0;
        for (int ind : possibleNeighbors) {
            if (ind != -1) {
                result[i++] = ind;
            }
        }
        return result;
    }

    public static void main(String[] args) { // test client (optional)
//        Percolation perc = new Percolation(0);
//        Percolation perc = new Percolation(10);
//        perc.open(10,11);
//        perc.open(0,1);
    }
}
