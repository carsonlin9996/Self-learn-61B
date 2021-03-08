package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;


public class Solver {


    private class SearchNode implements Comparable<SearchNode> {
       private WorldState worldState;
       private int moves;
       private SearchNode pre;
       private int priority;

       private SearchNode(WorldState w, int m, SearchNode pre){
           worldState = w;
           moves = m;
           this.pre = pre;
           this.priority = moves + w.estimatedDistanceToGoal();
       }

       private int moves(){
           return moves;
       }

       private WorldState world(){
           return worldState;
       }

       private SearchNode previous(){
           return pre;
       }

       public int compareTo(SearchNode node){
           return this.priority - node.priority;
       }
    }
    /*
    Constructor which solves the puzzle, computing
    everything necessary for moves() and solution() to
    not have to solve the problem again. Solves the
    puzzle using the A* algorithm. Assumes a solution exists.
     */
    private Stack<WorldState> path;

    public Solver(WorldState initial) {
        path = new Stack<>();
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));

        SearchNode goal = null;

        while (!pq.isEmpty()) {
            SearchNode min = pq.delMin(); //return first Node in PQ.
            WorldState minWS = min.world(); //return current worldstate
            int minMoves = min.moves(); //return # of moves
            SearchNode prev = min.previous(); //returns the previous world state
            if (minWS.isGoal()) {
                goal = min;
                break;
            }
            for (WorldState s : minWS.neighbors()) {
                if (prev == null || !s.equals(prev.world())) {
                    pq.insert(new SearchNode(s, minMoves + 1, min));
                }
            }
        }

        while (goal != null) {
            path.push(goal.world());
            goal = goal.previous();
        }
    }

    /*returns the minimum number of moves to solve the puzzle starting at the initial WorldState*/
    public int moves() {
        return path.size() - 1;
    }


    /*returns a sequence of WorldStates from the initial WorldState
      to the solution */
    public Iterable<WorldState> solution() {
        return path;
    }
}
