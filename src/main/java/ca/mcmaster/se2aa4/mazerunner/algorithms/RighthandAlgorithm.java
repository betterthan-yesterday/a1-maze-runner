package ca.mcmaster.se2aa4.mazerunner.algorithms;

import ca.mcmaster.se2aa4.mazerunner.MazePath;

public class RighthandAlgorithm extends PathAlgorithm {

    public RighthandAlgorithm(int[][] maze_array, int[] start, int[] end) {
        this.maze_array = maze_array;
        this.start = start;
        this.end = end;
    }

    @Override
    public MazePath move() {
        String seq = "";
        return new MazePath(seq);
    }



}
