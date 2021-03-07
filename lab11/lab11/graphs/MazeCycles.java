package lab11.graphs;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private boolean foundCycle = false;
    private int[] cameFrom;
    int s;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(5,5);
        marked[s] = true;
    }

    private void dfs(int v){
        for(int w : maze.adj(v)){
            if(foundCycle){
                return;
            }
            if(!marked[w]){
                marked[w] = true;
                cameFrom[w] = v;
                dfs(w);

            } else if( w != cameFrom[v]){ //if 'w' is not the parent of 'v'
                cameFrom[w] = v;
                int curr = v;
                edgeTo[curr] = cameFrom[curr];
                while(curr != w){
                    curr = cameFrom[curr];
                    edgeTo[curr] = cameFrom[curr];
                    announce();
                }
                foundCycle = true;
                return;
            }

        }
    }

    @Override
    public void solve() {
        cameFrom = new int[maze.V()];
        /* Set point where circle search starts */
        dfs(s);
    }
    // Helper methods go here
}

