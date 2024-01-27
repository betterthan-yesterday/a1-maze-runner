package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;
import java.io.IOException;

import ca.mcmaster.se2aa4.mazerunner.algorithms.PathAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.algorithms.RighthandAlgorithm;

public class Maze {

    public final File PATH_FILE;
    private MazeDecoder decoder;
    private final int[][] maze_array;
    private int[] start = new int[2];
    private int[] end = new int[2];
    private String algorithm;

    public Maze(File filename, String algo) throws IOException {
        this.PATH_FILE = filename;
        this.decoder = new MazeDecoder(PATH_FILE);
        this.maze_array = decoder.decode();
        getStartEnd();
        this.algorithm = algo;
    }

    private void getStartEnd() {
        for (int row = 0; row < maze_array.length; row++) { // Maybe belongs in MazeDecoder
            if (maze_array[row][0] == 0) {
                start[0] = row;
                start[1] = 0;
            }
            if (maze_array[row][maze_array[0].length - 1] == 0) {
                end[0] = row;
                end[1] = maze_array[0].length - 1;
            }
        };
    }

    public void printMaze() throws IOException {
        decoder.print();
    }

    public MazePath findCorrectPath() {
        PathAlgorithm algo = null;
        switch (algorithm) {
            case "tremaux" -> algo = new TremauxAlgorithm(maze_array, start, end);
            default -> algo = new RighthandAlgorithm(maze_array, start, end);
        }
        MazePath maze_solution = algo.solve();
        return maze_solution;
    }

    public boolean verifyPath(MazePath seq) {
        Navigator nav = new Navigator(maze_array, seq);
        boolean checkFromWest = nav.navigate(start, end);
        boolean checkFromEast = nav.navigate(end, start);
        return checkFromWest || checkFromEast;
    }
}
