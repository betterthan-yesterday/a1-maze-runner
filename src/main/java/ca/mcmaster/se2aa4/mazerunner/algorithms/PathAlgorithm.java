package ca.mcmaster.se2aa4.mazerunner.algorithms;

import ca.mcmaster.se2aa4.mazerunner.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Movement;
import ca.mcmaster.se2aa4.mazerunner.Movement.Direction;

public abstract class PathAlgorithm {

    public int[][] maze_array;
    public int[] start;
    public int[] end;
    protected int[] currentPos;
    protected Direction currentDir;
    protected Movement mover;

    public abstract MazePath solve();

}
