package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][];
    private WeightedQuickUnionUF map;
    private WeightedQuickUnionUF map2;

    private int top;
    private int bottom;
    private int sideLength;
    private int openSites = 0;

    //create N by N grid, with all sites initial
    public Percolation(int N){
        if(N <= 0){
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        top = 0;
        bottom = N * N + 1;

        map = new WeightedQuickUnionUF(N * N + 2);
        map2 = new WeightedQuickUnionUF(N*N + 1);
        sideLength = N;
    }

    //open the site(row, col) if it is not open
    public void open(int row, int col){
        validate(row, col);
        if(!isOpen(row, col)){
            grid[row][col] = true;
            openSites  += 1;
        }

        if(row == 0){
            map.union(xyTo1D(row,col), top);
            map2.union(xyTo1D(row,col), top);
        }
        if(row == sideLength - 1){
            map.union(xyTo1D(row, col), bottom);
        }

        if(row > 0 && isOpen(row - 1, col)){
            map.union(xyTo1D(row, col), xyTo1D(row - 1, col ));
            map2.union(xyTo1D(row, col), xyTo1D(row - 1, col ));

        }
        if(row < sideLength - 1 && isOpen(row + 1, col)){
            map.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            map2.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }

        if(col > 0 && isOpen(row, col - 1)){
            map.union(xyTo1D(row,col), xyTo1D(row, col - 1));
            map2.union(xyTo1D(row,col), xyTo1D(row, col - 1));
        }
        if(col < sideLength - 1 && isOpen(row, col + 1)){
            map.union(xyTo1D(row,col), xyTo1D(row, col + 1));
            map2.union(xyTo1D(row,col), xyTo1D(row, col + 1));
        }


    }

    //is the site(row, col) open?
    public boolean isOpen(int row, int col){
        validate(row, col);
        return grid[row][col];
    }

    //is the site(row,col) full?
    public boolean isFull(int row, int col){
        validate(row,col);
        return map2.connected(xyTo1D(row,col), top);
    }

    public int numberOfOpenSites(){
        return openSites;
    }

    //does the system percolate?
    public boolean percolates(){
        return map.connected(bottom, top);
    }

    private int xyTo1D(int row, int col){
        return row * sideLength + col + 1;
    }

    private void validate(int row, int col){
        if(row < 0 || col < 0 || row >= sideLength || col >= sideLength){
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args){
    }

}
