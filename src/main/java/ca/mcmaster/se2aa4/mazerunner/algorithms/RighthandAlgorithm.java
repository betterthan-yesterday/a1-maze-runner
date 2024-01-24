package ca.mcmaster.se2aa4.mazerunner.algorithms;

import ca.mcmaster.se2aa4.mazerunner.MazePath;

public class RighthandAlgorithm extends PathAlgorithm {

    public RighthandAlgorithm(int[][] maze_array, int[] start, int[] end) {
        this.maze_array = maze_array;
        this.start = start;
        this.end = end;
    }

    @Override
    public MazePath solve() {
        String seq = "";
        while (currentPos != end) {
            if (checkRight()) {
                seq += "R";
            } else if (checkFront()) {
                seq += "F";
            } else if (checkLeft()) {
                seq += "L";
            }
        }
        return new MazePath(seq);
    }

}
