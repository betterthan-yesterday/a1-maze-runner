package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import ca.mcmaster.se2aa4.mazerunner.Movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.Movement.Move;

public class Navigator {

    int[][] maze_array;
    MazePath path;
    Movement mover;

    public Navigator(int[][] arr, MazePath path) {
        this.maze_array = arr;
        this.path = path;
        this.mover = new Movement();
    }

    private boolean checkInvalid(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        if (maze_array[row][col] == 1) { // Detect wall
            return true;
        } else if (row < 0 || row >= maze_array.length || col < 0 || col >= maze_array[0].length) { // Detect out of bounds
            return true;
        }
        return false;
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

        for (int i = 0; i < path.getCanonicalLength(); i++) {
            char move = path.getMove(i);
            switch (move) {
                case 'F' -> currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                case 'L' -> currentDir = mover.updateDir(Move.LEFT, currentDir);
                case 'R' -> currentDir = mover.updateDir(Move.RIGHT, currentDir);
                case 'B' -> currentPos = mover.updatePos(Move.BACKWARD, currentDir, currentPos);
            }

            // If the navigator tries to move into a wall or goes out of bounds, return false
            if (checkInvalid(currentPos))
                return false;
        }
        if (Arrays.equals(currentPos, end)) {
            return true;
        }
        return false;
    }

}
