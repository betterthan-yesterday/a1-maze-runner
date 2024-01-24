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
