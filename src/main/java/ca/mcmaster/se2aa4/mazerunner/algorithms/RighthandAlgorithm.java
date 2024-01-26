package ca.mcmaster.se2aa4.mazerunner.algorithms;

import java.util.Arrays;

import ca.mcmaster.se2aa4.mazerunner.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.Movement.Move;

public class RighthandAlgorithm extends PathAlgorithm {

    public RighthandAlgorithm(int[][] maze_array, int[] start, int[] end) {
        super(maze_array, start, end);
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
