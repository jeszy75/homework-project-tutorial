package rollingdice.solver;

import puzzle.solver.BreadthFirstSearch;

import rollingdice.model.Direction;
import rollingdice.model.RollingDiceState;

public class Main {

    public static void main(String[] args) {
        var bfs = new BreadthFirstSearch<Direction>();
        bfs.solveAndPrintSolution(new RollingDiceState());
    }

}
