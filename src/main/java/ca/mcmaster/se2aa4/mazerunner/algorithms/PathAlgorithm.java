package ca.mcmaster.se2aa4.mazerunner.algorithms;

import ca.mcmaster.se2aa4.mazerunner.MazePath;

public abstract class PathAlgorithm {

    enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public int[][] maze_array;
    public int[] start;
    public int[] end;
    private int[] currentPos = start;
    private Direction currentDir = Direction.EAST;

    public boolean checkRight() {
        return false;
    }

    public boolean checkFront() {
        return false;
    }

    public boolean checkLeft() {
        return false;
    }

    public abstract MazePath move();

}
