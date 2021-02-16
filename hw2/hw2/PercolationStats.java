package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.introcs.StdDraw;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid

    private int trials;
    private double[] fractions;
    /**
     *
     * @param N = creates a N * N grid
     * @param T = performs T trials of percolation simulation
     * @param pf = PercolationFactory data type, make(N) method returns a Percolation data type.
     */
    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        trials = T;

        Percolation grid = pf.make(N); //returns a Percolation data type
        fractions = new double[T];
        for(int i = 0; i < T; i++){
            int openSites = 0;
            if(!grid.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);

                if(!grid.isOpen(row, col)){
                    grid.open(row, col);
                    openSites += 1;
                }
            }
            fractions[i] = openSites/ (N * N);
        }
    }
    // sample mean of percolation threshold
    public double mean(){
        double fractionsTotal = 0.0;
        for(double i : fractions){
            fractionsTotal += i;
        }
        return fractionsTotal/trials;

        // return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        double mu = mean();
        double std = stddev();

        return mu - 1.96 * std / Math.sqrt(trials);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        double mu = mean();
        double std = stddev();

        return mu + 1.96 * std / Math.sqrt(trials);
    }
}
