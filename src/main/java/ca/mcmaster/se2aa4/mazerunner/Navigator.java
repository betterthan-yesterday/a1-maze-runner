package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import ca.mcmaster.se2aa4.mazerunner.Movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.Movement.Move;

public class Navigator {

    int[][] maze_array;
    MazePath sequence;
    Movement mover;

    public Navigator(int[][] arr, MazePath seq) {
        this.maze_array = arr;
        this.sequence = seq;
        this.mover = new Movement();
    }

    private boolean checkValid(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        if (maze_array[row][col] == 1) { // Trying to move into a wall
            return false;
        }
        return true;
    }

    public boolean navigate(int[] start, int[] end) {
        int[] currentPos = new int[2];
        currentPos[0] = start[0];
        currentPos[1] = start[1];

        Direction currentDir;
        if (start[1] == 0) {
            currentDir = Direction.EAST;
        } else {
            currentDir = Direction.WEST;
        }

        for (int i = 0; i < sequence.length(); i++) {
            char move = sequence.getMove(i);
            int[] newPos = new int[2];
            switch (move) {
                case 'F' -> newPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                case 'L' -> currentDir = mover.updateDir(Move.LEFT, currentDir);
                case 'R' -> currentDir = mover.updateDir(Move.RIGHT, currentDir);
                case 'B' -> newPos = mover.updatePos(Move.BACKWARD, currentDir, currentPos);
            }

            // The navigator will stay in the same spot if it tries to move foward into a wall,
            // but will continue to follow the sequence.
            if (checkValid(newPos))
                currentPos = newPos;

            // If the navigator reaches the end of the maze, return true regardless of if there
            // are still moves left in the sequence.
            if (Arrays.equals(currentPos, end)) {
                return true;
            }
        }
        return false;
    }

}
