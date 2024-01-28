package ca.mcmaster.se2aa4.mazerunner.algorithms;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.Movement.Move;

public class TremauxAlgorithm extends PathAlgorithm {

    private static final Logger logger = LogManager.getLogger();

    public TremauxAlgorithm(int[][] maze, int[] start, int[] end) {
        /* From PathAlgorithm.java:
        this.maze_array = array;
        this.start = start;
        this.end = end;

        this.currentPos = new int[2];
        this.currentPos[0] = start[0];
        this.currentPos[1] = start[1];
        this.currentDir = Direction.EAST;
        this.mover = new Movement();
        */
        super(maze, start, end);
    }

    @Override
    public MazePath solve() {
        int[][] modified_maze = maze_array.clone();

        while (!Arrays.equals(currentPos, end)) {
            int left_tile = checkLeft(currentDir);
            int right_tile = checkRight(currentDir);
            int front_tile = checkFront(currentDir);
        }


        currentPos = start;
        String seq = "FF";

        return new MazePath(seq);
    }

    private int checkLeft(Direction dir) {
        int tile = -1;
        switch (dir) {
            case NORTH -> tile = maze_array[currentPos[0]][currentPos[1]-1];
            case EAST -> tile = maze_array[currentPos[0]-1][currentPos[1]];
            case SOUTH -> tile = maze_array[currentPos[0]][currentPos[1]+1];
            case WEST -> tile = maze_array[currentPos[0]+1][currentPos[1]];
        }
        return tile;
    }

    private int checkRight(Direction dir) {
        int tile = -1;
        switch (dir) {
            case NORTH -> tile = maze_array[currentPos[0]][currentPos[1]+1];
            case EAST -> tile = maze_array[currentPos[0]+1][currentPos[1]];
            case SOUTH -> tile = maze_array[currentPos[0]][currentPos[1]-1];
            case WEST -> tile = maze_array[currentPos[0]-1][currentPos[1]];
        }
        return tile;
    }

    private int checkFront(Direction dir) {
        int tile = -1;
        switch (dir) {
            case NORTH -> tile = maze_array[currentPos[0]-1][currentPos[1]];
            case EAST -> tile = maze_array[currentPos[0]][currentPos[1]+1];
            case SOUTH -> tile = maze_array[currentPos[0]+1][currentPos[1]];
            case WEST -> tile = maze_array[currentPos[0]][currentPos[1]-1];
        }
        return tile;
    }

    private void markTile(int[][] maze_arr, Move relative_move) {
        switch (currentDir) {
            case NORTH -> {
                if (relative_move == Move.FORWARD) {
                    maze_arr[currentPos[0]-1][currentPos[1]] += 1;
                } else { // Only marks backwards once because an exit may be an entrance to another junction and it would be marked twice
                    maze_arr[currentPos[0]+1][currentPos[1]] = (maze_arr[currentPos[0]+1][currentPos[1]] == 1) ? 2 : maze_arr[currentPos[0]+1][currentPos[1]];
                }
            }
            case EAST -> {
                if (relative_move == Move.FORWARD) {
                    maze_arr[currentPos[0]][currentPos[1]+1] += 1;
                } else {
                    maze_arr[currentPos[0]][currentPos[1]-1] = (maze_arr[currentPos[0]][currentPos[1]-1] == 1) ? 2 : maze_arr[currentPos[0]][currentPos[1]-1];
                }
            }
            case SOUTH -> {
                if (relative_move == Move.FORWARD) {
                    maze_arr[currentPos[0]+1][currentPos[1]] += 1;
                } else {
                    maze_arr[currentPos[0]-1][currentPos[1]] = (maze_arr[currentPos[0]-1][currentPos[1]] == 1) ? 2 : maze_arr[currentPos[0]-1][currentPos[1]];
                }
            }
            case WEST -> {
                if (relative_move == Move.FORWARD) {
                    maze_arr[currentPos[0]][currentPos[1]-1] += 1;
                } else {
                    maze_arr[currentPos[0]][currentPos[1]+1] = (maze_arr[currentPos[0]][currentPos[1]+1] == 1) ? 2 : maze_arr[currentPos[0]][currentPos[1]+1];
                }
            }
        }
    }

}
