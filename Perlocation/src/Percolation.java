import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Andrew on 10/16/2016.
 */

public class Percolation {

    private final int size;
    private final WeightedQuickUnionUF uf;
    private final int startIndex;
    private final int endIndex;
    private final boolean[] openSites;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n <= 0){
            throw new IllegalArgumentException("Size of the percolation should be positive");
        }
        size = n;
        int flattenSize = n * n + 2; // Flatten array with 2 virtual start and end.
        openSites = new boolean[flattenSize];

        uf = new WeightedQuickUnionUF(flattenSize);
        startIndex = n*n;
        endIndex = n*n + 1;

        openSites[startIndex] = true;
        openSites[endIndex] = true;
    }

    public void open(int row, int col){ // open site (row, col) if it is not open already
        validateRowAndCol(row, col);

        int index = getFlattenIndex(row, col);

        if (!openSites[index]){
            openSites[index] = true;

            int[] neighbors = getNeighborsIndexes(row, col, index);

            for (int neighbor : neighbors) {
                if (openSites[neighbor]) {
                    uf.union(index, neighbor);
                }
            }
        }
    }

    public boolean isOpen(int row, int col){ // is site (row, col) open?
        validateRowAndCol(row, col);

        int index = getFlattenIndex(row, col);
        return openSites[index];
    }

    public boolean isFull(int row, int col){ // is site (row, col) full?
        validateRowAndCol(row, col);
        int index = getFlattenIndex(row, col);
        return uf.connected(startIndex, index);
    }

    public boolean percolates(){ // does the system percolate?
        return uf.connected(startIndex, endIndex);
    }

    private void validateRowAndCol(int row, int col) {
        validateDimension(row, "Row");
        validateDimension(col, "Col");
    }

    private void validateDimension(int value, String dimensionName) {
        if (value < 1 || value > size){
            throw new IndexOutOfBoundsException(String.format("%1s parameter is not in range 1..%2d", dimensionName, size));
        }
    }

    private int getFlattenIndex(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    private int[] getNeighborsIndexes(int row, int col, int index) {
        int[] possibleNeighbors = new int[]{-1,-1,-1,-1}; // just to avoid boxing/unboxing
        int count = 2;

        if (row == 1) {
            possibleNeighbors[0] = startIndex;
        }
        else{
            possibleNeighbors[0] = index - size;
        }

        if (row == size) {
            possibleNeighbors[1] = endIndex;
        }
        else{
            possibleNeighbors[1] = index + size;
        }

        if (col != 1){
            possibleNeighbors[2] = index - 1;
            count++;
        }
        if (col != size){
            possibleNeighbors[3] = index + 1;
            count++;
        }

        int[] result = new int[count];
        int i = 0;
        for (int ind : possibleNeighbors) {
            if (ind != -1){
                result[i++] = ind;
            }
        }
        return result;
    }

    public static void main(String[] args){ // test client (optional)
//        Percolation perc = new Percolation(0);
//        Percolation perc = new Percolation(10);
//        perc.open(10,11);
//        perc.open(0,1);
    }
}
