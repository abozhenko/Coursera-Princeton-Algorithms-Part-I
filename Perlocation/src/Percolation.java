/**
 * Created by Andrew on 10/16/2016.
 */
public class Percolation {
    int[][] sites;

    public Percolation(int n) {

        sites = new int[n][n];
    }               // create n-by-n grid, with all sites blocked
    public void open(int row, int col){

        sites[row - 1][col - 1] = 1;
    }       // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col){

        return sites[row - 1][col - 1] == 1;
    }  // is site (row, col) open?
    public boolean isFull(int row, int col){

        return sites[row - 1][col - 1] == 0;
    }  // is site (row, col) full?
    public boolean percolates(){
        return false;
    }              // does the system percolate?

    public static void main(String[] args){

    }   // test client (optional)
}
