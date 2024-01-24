package ca.mcmaster.se2aa4.mazerunner.algorithms;

import ca.mcmaster.se2aa4.mazerunner.MazePath;
import ca.mcmaster.se2aa4.mazerunner.algorithms.Movement.Direction;

public abstract class PathAlgorithm {

    public int[][] maze_array;
    public int[] start;
    public int[] end;
    protected int[] currentPos;
    protected Direction currentDir;
    protected Movement mover;

    public boolean checkRight(Direction dir) {
        boolean right_is_path = false;
        switch (dir) {
            case NORTH -> right_is_path = maze_array[currentPos[0]][currentPos[1]+1] == 0;
            case EAST -> right_is_path = maze_array[currentPos[0]+1][currentPos[1]] == 0;
            case SOUTH -> right_is_path = maze_array[currentPos[0]][currentPos[1]-1] == 0;
            case WEST -> right_is_path = maze_array[currentPos[0]-1][currentPos[1]] == 0;
        }
        return right_is_path;
    }

    public boolean checkFront(Direction dir) {
        boolean front_is_path = false;
        switch (dir) {
            case NORTH -> front_is_path = maze_array[currentPos[0]-1][currentPos[1]] == 0;
            case EAST -> front_is_path = maze_array[currentPos[0]][currentPos[1]+1] == 0;
            case SOUTH -> front_is_path = maze_array[currentPos[0]+1][currentPos[1]] == 0;
            case WEST -> front_is_path = maze_array[currentPos[0]][currentPos[1]-1] == 0;
        }
        return front_is_path;
    }

    public boolean checkLeft(Direction dir) {
        boolean left_is_path = false;
        switch (dir) {
            case NORTH -> left_is_path = maze_array[currentPos[0]][currentPos[1]-1] == 0;
            case EAST -> left_is_path = maze_array[currentPos[0]-1][currentPos[1]] == 0;
            case SOUTH -> left_is_path = maze_array[currentPos[0]][currentPos[1]+1] == 0;
            case WEST -> left_is_path = maze_array[currentPos[0]+1][currentPos[1]] == 0;
        }
        return left_is_path;
    }

    public abstract MazePath solve();

}
