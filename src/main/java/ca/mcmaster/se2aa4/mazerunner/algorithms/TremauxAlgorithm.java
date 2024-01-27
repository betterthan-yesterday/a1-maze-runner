package ca.mcmaster.se2aa4.mazerunner.algorithms;

import java.util.Arrays;

import ca.mcmaster.se2aa4.mazerunner.MazePath;
import ca.mcmaster.se2aa4.mazerunner.Movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.Movement.Move;

public class TremauxAlgorithm extends PathAlgorithm {

    public TremauxAlgorithm(int[][] maze, int[] start, int[] end) {
        super(maze, start, end);
    }

    @Override
    public MazePath solve() {
        int[][] modified_maze = maze_array.clone();

        while (!Arrays.equals(currentPos, end)) {
            int left_tile = checkLeft(currentDir);
            int right_tile = checkRight(currentDir);
            int front_tile = checkFront(currentDir);

            // if ((left_tile + right_tile + front_tile) > 1) {

            // } else {

            // }
            
            currentDir = mover.updateDir(Move.LEFT, currentDir);
            currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);

            currentDir = mover.updateDir(Move.RIGHT, currentDir);
            currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);

        }

        currentPos = start;
        String seq = "";

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

}
