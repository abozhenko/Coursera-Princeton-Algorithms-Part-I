import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Andrew on 10/16/2016.
 */
public class PercolationStats {
    private final int trials;
    private double[] thresholds;

    public PercolationStats(int n, int trials){ // perform trials independent experiments on an n-by-n grid
        if (n <= 0){
            throw new IllegalArgumentException("Size of the percolation should be positive");
        }
        if (trials <= 0){
            throw new IllegalArgumentException("Number of trials should be positive");
        }

        this.trials = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++){
            thresholds[i] = calculatePercolationThreshold(n);
        }
    }

    private double calculatePercolationThreshold(int n){
        int attempt = 0;
        Percolation simulation = new Percolation(n);
        while (!simulation.percolates()){
            int row = StdRandom.uniform(n)+1;
            int col = StdRandom.uniform(n)+1;
            if (!simulation.isOpen(row, col)){
                attempt ++;
                simulation.open(row, col);
            }
        }
        return attempt/(double)(n*n);
    }

    public double mean(){ // sample mean of percolation threshold
        return StdStats.mean(thresholds);
    }

    public double stddev(){ // sample standard deviation of percolation threshold
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo(){ // low  endpoint of 95% confidence interval
        return mean() - 1.96*stddev()/Math.sqrt(trials);
    }
    public double confidenceHi(){ // high endpoint of 95% confidence interval
        return mean() + 1.96*stddev()/Math.sqrt(trials);
    }

    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);      // input file
        int trials = Integer.parseInt(args[1]);      // input file
        PercolationStats stats = new PercolationStats(size, trials);
        StdOut.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = %f, %f", stats.confidenceLo(), stats.confidenceHi());
    }    // test client (described below)
}
