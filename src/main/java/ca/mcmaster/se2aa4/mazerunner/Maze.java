package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    public final File PATH_FILE;
    public int[][] maze_array;
    private MazeDecoder decoder;
    private MazeRunner runner;

    public Maze(File filename) throws IOException {
        // this.PATH_FILE = filename; 
        this.PATH_FILE = new File("examples/straight.maz.txt");
        this.decoder = new MazeDecoder(PATH_FILE);

        this.maze_array = decoder.decode();
        this.runner = new MazeRunner(maze_array);
    }

    public void printMaze() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("# ");
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("- ");
                }
            }
            System.out.print(System.lineSeparator());
        }
        reader.close();
    }

    public MazePath findCorrectPath() {
        return runner.discoverPath();
    }

    public boolean verifyPath(MazePath seq) {
        return runner.navigate_path(seq);
    }
}
