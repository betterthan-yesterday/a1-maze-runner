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

            for (int[] row : modified_maze)
                logger.info(Arrays.toString(row));
            logger.info("current: " + Arrays.toString(currentPos));
            
            boolean is_junction = (((left_tile>0)?1:0) + ((right_tile>0)?1:0) + ((front_tile>0)?1:0)) > 1;
            if (is_junction) {
                logger.info("junction");
                /*
                 * Check junction has at least one path that is double marked. The assumption is made
                 * that if at least one path is double marked, then the junction has been visited
                 * enough times to be a dead end.
                 * The reason the min between the paths is taken is because that was the first path
                 * taken into the dead end and the algorithm is backtracking.
                 * Priority: RIGHT->FORWARD->LEFT
                 */
                if (right_tile == 3 || left_tile == 3 || front_tile == 3) {
                    left_tile = (left_tile == 0) ? 999 : left_tile;
                    right_tile = (right_tile == 0) ? 999 : right_tile;
                    front_tile = (front_tile == 0) ? 999 : front_tile;
                    int min = Math.min(Math.min(left_tile, right_tile), front_tile);
                    if (min == right_tile) {
                        currentDir = mover.updateDir(Move.RIGHT, currentDir);
                        markTile(modified_maze, Move.FORWARD);
                        currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                    } else if (min == front_tile) {
                        markTile(modified_maze, Move.FORWARD);
                        currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                    } else if (min == left_tile) {
                        currentDir = mover.updateDir(Move.LEFT, currentDir);
                        markTile(modified_maze, Move.FORWARD);
                        currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                    }
                } else {
                    if (right_tile == 1) {
                        currentDir = mover.updateDir(Move.RIGHT, currentDir);
                        markTile(modified_maze, Move.FORWARD);
                        currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                    } else if (front_tile == 1){
                        markTile(modified_maze, Move.FORWARD);
                        currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                    } else if (left_tile == 1) {
                        currentDir = mover.updateDir(Move.LEFT, currentDir);
                        markTile(modified_maze, Move.FORWARD);
                        currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                    } else { // dead end - U-turn
                        currentDir = mover.updateDir(Move.LEFT, currentDir); 
                        currentDir = mover.updateDir(Move.LEFT, currentDir); 
                        markTile(modified_maze, Move.FORWARD);
                        currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                    }
                }
            } else {
                if (right_tile != 0) {
                    currentDir = mover.updateDir(Move.RIGHT, currentDir);
                    if (checkFront(currentDir) == 2) {
                        markTile(modified_maze, Move.FORWARD);
                    }
                    currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                } else if (front_tile != 0) {
                    if (checkFront(currentDir) == 2) { // If the front tile has already been marked, mark it again, then move on top
                        markTile(modified_maze, Move.FORWARD);
                    }
                    currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                } else if (left_tile != 0) {
                    currentDir = mover.updateDir(Move.LEFT, currentDir);
                    if (checkFront(currentDir) == 2) {
                        markTile(modified_maze, Move.FORWARD);
                    }
                    currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                } else { // dead end - U-turn
                    currentDir = mover.updateDir(Move.LEFT, currentDir); 
                    currentDir = mover.updateDir(Move.LEFT, currentDir); 
                }
            }
        }

        currentPos[0] = start[0];
        currentPos[1] = start[1];
        String seq = "";
        while (!Arrays.equals(currentPos, end)) {
            int left_tile = checkLeft(currentDir);
            int right_tile = checkRight(currentDir);
            int front_tile = checkFront(currentDir);
            // boolean is_junction = (((left_tile>0)?1:0) + ((right_tile>0)?1:0) + ((front_tile>0)?1:0)) > 1;
            // if (is_junction) {
                // Follow the marked path with priority RIGHT->FORWARD->LEFT to keep consistent
                if (right_tile == 1 || right_tile == 2) {
                    seq += "R";
                    currentDir = mover.updateDir(Move.RIGHT, currentDir);
                    seq += "F";
                    currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                } else if (front_tile == 1 || front_tile == 2) {
                    seq += "F";
                    currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                } else if (left_tile == 1 || left_tile == 2) {
                    seq += "L";
                    currentDir = mover.updateDir(Move.LEFT, currentDir);
                    seq += "F";
                    currentPos = mover.updatePos(Move.FORWARD, currentDir, currentPos);
                } else { // dead end - U-turn
                    currentDir = mover.updateDir(Move.LEFT, currentDir); 
                    currentDir = mover.updateDir(Move.LEFT, currentDir); 
                }
            // }
        }

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
