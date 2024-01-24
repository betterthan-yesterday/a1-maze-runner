package ca.mcmaster.se2aa4.mazerunner.algorithms;

import java.util.Arrays;

import ca.mcmaster.se2aa4.mazerunner.MazePath;
import ca.mcmaster.se2aa4.mazerunner.algorithms.Movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.algorithms.Movement.Move;

public class RighthandAlgorithm extends PathAlgorithm {

    public RighthandAlgorithm(int[][] maze_array, int[] start, int[] end) {
        this.maze_array = maze_array;
        this.start = start;
        this.end = end;
        this.currentPos = start;
        this.currentDir = Direction.EAST;
        this.mover = new Movement();
    }

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

    @Override
    public MazePath solve() {
        String seq = "";
        while (!Arrays.equals(currentPos, end)) {
            if (checkRight(currentDir)) {
                seq += "R";
                currentDir = mover.updateDir(Move.RIGHT, currentDir);
                seq += "F";
                currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
            } else if (checkFront(currentDir)) {
                seq += "F";
                currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
            } else {
                seq += "L";
                currentDir = mover.updateDir(Move.LEFT, currentDir);
            }
        }
        return new MazePath(seq);
    }

}
