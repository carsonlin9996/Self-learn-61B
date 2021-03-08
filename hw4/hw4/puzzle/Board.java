package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

    private int size;
    private int[][] grid;
    private final int BLANK = 0;

    /*Constructs a board from an N by N array of tiles where
      tiles[i][j] = tile at row i, column j
     */
    public Board(int[][] tiles){
        size = tiles.length;
        grid = new int[size][size];
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j){
        if(i < 0 || i >= size || j < 0 || i >= size){
            throw new IndexOutOfBoundsException();
        }
        return grid[i][j];
    }

    public int size(){
        return size;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    //provided in the skeleton code.
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    /*Source: Josh Hug @ http://joshh.ug/neighbors.html */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /*The number of tiles in the wrong position */
    public int hamming(){
        int wrongPosCnt = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(tileAt(i,j) != i * size + j + 1 && tileAt(i, j) != BLANK) {
                    wrongPosCnt += 1;
                }
            }
        }
        return wrongPosCnt;
    }

    public int manhattan(){
        int estMoves = 0;

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                int tiles = tileAt(row, col);
                if(tiles != BLANK) {
                   int expRow = (tiles - 1) / size;
                   int expCol = (tiles - 1) % size;
                    estMoves += Math.abs(row - expRow) + Math.abs(col - expCol);
                }
            }
        }
        return estMoves;
    }
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    //returns true if this board's tile values are the same position as y's
    public boolean equals(Object other){

        if(this == other){
            return true;
        }
        if(other == null){
            return false;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }

        Board that = (Board) other;
        if(this.size != that.size){
            return false;
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(this.tileAt(i , j) != that.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    /*public int hashCode() {
        return 1;
    }*/
}
