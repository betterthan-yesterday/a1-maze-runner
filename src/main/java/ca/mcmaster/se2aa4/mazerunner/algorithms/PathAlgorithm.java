package ca.mcmaster.se2aa4.mazerunner.algorithms;

import ca.mcmaster.se2aa4.mazerunner.MazePath;

public abstract class PathAlgorithm {

    enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    enum Move {
        FORWARD, LEFT, RIGHT, BACKWARD
    }

    public int[][] maze_array;
    public int[] start;
    public int[] end;
    protected int[] currentPos = start;
    private Direction currentDir = Direction.EAST;

    public boolean checkRight() {
        boolean right_is_path = false;
        switch (currentDir) {
            case NORTH:
                right_is_path = maze_array[currentPos[0]][currentPos[1]+1] == 0;
            case EAST:
                right_is_path = maze_array[currentPos[0]+1][currentPos[1]] == 0;
            case SOUTH:
                right_is_path = maze_array[currentPos[0]][currentPos[1]-1] == 0;
            case WEST:
                right_is_path = maze_array[currentPos[0]-1][currentPos[1]] == 0;
        }
        return right_is_path;
    }

    public boolean checkFront() {
        boolean front_is_path = false;
        switch (currentDir) {
            case NORTH:
                front_is_path = maze_array[currentPos[0]-1][currentPos[1]] == 0;
            case EAST:
                front_is_path = maze_array[currentPos[0]][currentPos[1]+1] == 0;
            case SOUTH:
                front_is_path = maze_array[currentPos[0]+1][currentPos[1]] == 0;
            case WEST:
                front_is_path = maze_array[currentPos[0]][currentPos[1]-1] == 0;
        }
        return front_is_path;
    }

    public boolean checkLeft() {
        boolean left_is_path = false;
        switch (currentDir) {
            case NORTH:
                left_is_path = maze_array[currentPos[0]][currentPos[1]-1] == 0;
            case EAST:
                left_is_path = maze_array[currentPos[0]-1][currentPos[1]] == 0;
            case SOUTH:
                left_is_path = maze_array[currentPos[0]][currentPos[1]+1] == 0;
            case WEST:
                left_is_path = maze_array[currentPos[0]+1][currentPos[1]] == 0;
        }
        return left_is_path;
    }

    public void updatePos(Move move) {
        switch (currentDir) {
            case NORTH:
                currentPos[0] = (move == Move.FORWARD) ? currentPos[0] - 1 : currentPos[0] + 1;
            case EAST:
                currentPos[1] = (move == Move.FORWARD) ? currentPos[1] + 1 : currentPos[1] - 1;
            case SOUTH:
                currentPos[0] = (move == Move.FORWARD) ? currentPos[0] + 1 : currentPos[0] - 1;
            case WEST:
                currentPos[1] = (move == Move.FORWARD) ? currentPos[1] - 1 : currentPos[1] + 1;
        }
    }

    public void updateDir(Move move) {
        switch (currentDir) {
            case NORTH:
                currentDir = (move == Move.RIGHT) ? Direction.EAST : Direction.WEST;
            case EAST:
                currentDir = (move == Move.RIGHT) ? Direction.SOUTH : Direction.NORTH;
            case SOUTH:
                currentDir = (move == Move.RIGHT) ? Direction.WEST : Direction.EAST;
            case WEST:
                currentDir = (move == Move.RIGHT) ? Direction.NORTH : Direction.SOUTH;
        }
    }

    public abstract MazePath solve();

}
