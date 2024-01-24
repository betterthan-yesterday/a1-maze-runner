package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;
import java.io.IOException;

public class Maze {

    public final File PATH_FILE;
    public int[][] maze_array;
    private MazeDecoder decoder;
    private MazeRunner runner;

    public Maze(File filename) throws IOException {
        this.PATH_FILE = filename;
        this.decoder = new MazeDecoder(PATH_FILE);

        this.maze_array = decoder.decode();
        this.runner = new MazeRunner(maze_array);
    }

    public void printMaze() throws IOException {
        decoder.print();
    }

    public MazePath findCorrectPath() {
        return runner.discoverPath();
    }

    public boolean verifyPath(MazePath seq) {
        return runner.followPath(seq);
    }
}
