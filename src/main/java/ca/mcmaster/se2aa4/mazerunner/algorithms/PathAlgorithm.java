package ca.mcmaster.se2aa4.mazerunner.algorithms;

import ca.mcmaster.se2aa4.mazerunner.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Movement;
import ca.mcmaster.se2aa4.mazerunner.Movement.Direction;

public abstract class PathAlgorithm {

    protected final int[][] maze_array;
    protected final int[] start;
    protected final int[] end;
    protected int[] currentPos;
    protected Direction currentDir;
    protected Movement mover;

    public PathAlgorithm(int[][] array, int[] start, int[] end) {
        this.maze_array = array;
        this.start = start;
        this.end = end;
        this.currentPos = new int[2];
        this.currentPos[0] = start[0];
        this.currentPos[1] = start[1];
        this.currentDir = Direction.EAST;
        this.mover = new Movement();
    }

    public abstract MazePath solve();

}
