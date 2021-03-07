package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /*

    int[] distTo;
    int[] edgeTo;
    boolean[] marked;
    protected Maze maze;
     */

    int start;
    int end;
    private Maze maze;
    private boolean targetFound;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {

        super(m);
        maze = m;
        start = maze.xyTo1D(sourceX, sourceY);
        end = maze.xyTo1D(targetX, targetY);
        distTo[start] = 0;
        edgeTo[start] = start;
        marked[start] = true;
        targetFound = false;
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> fringe = new ArrayDeque<>();
        fringe.add(start);

        while(!fringe.isEmpty()){
            int first = fringe.poll();

            if(first == end){
                targetFound = true;
                return;
            }

            for(int w : maze.adj(first)){
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = first;
                    distTo[w] = distTo[first] + 1;
                    announce();
                    if(w == end){
                        targetFound = true;
                        return;
                    }
                    fringe.add(w);
                }
            }

        }
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
    }


    @Override
    public void solve() {
        bfs();
    }
}

