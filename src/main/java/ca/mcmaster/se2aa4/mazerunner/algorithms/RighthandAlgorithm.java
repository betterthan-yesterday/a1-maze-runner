package ca.mcmaster.se2aa4.mazerunner.algorithms;

import java.util.Arrays;

import ca.mcmaster.se2aa4.mazerunner.MazePath;

public class RighthandAlgorithm extends PathAlgorithm {

    public RighthandAlgorithm(int[][] maze_array, int[] start, int[] end) {
        this.maze_array = maze_array;
        this.start = start;
        this.currentPos = start;
        this.currentDir = Direction.EAST;
        this.end = end;
    }

    @Override
    public MazePath solve() {
        String seq = "";
        while (!Arrays.equals(currentPos, end)) {
            if (checkRight(currentDir)) {
                seq += "R";
                updateDir(Move.RIGHT);
                seq += "F";
                updatePos(Move.FORWARD);
            } else if (checkFront(currentDir)) {
                seq += "F";
                updatePos(Move.FORWARD);
            } else {
                seq += "L";
                updateDir(Move.LEFT);
            }
        }
        return new MazePath(seq);
    }

}
