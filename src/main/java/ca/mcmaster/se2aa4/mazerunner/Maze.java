package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    public final File PATH_FILE;
    private final MazeRunner runner;

    public Maze(File filename) {
        this.PATH_FILE = filename; 
        this.runner = new MazeRunner(PATH_FILE);
    }

    public void print_maze() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("PASS ");
                }
            }
            System.out.print(System.lineSeparator());
        }
        reader.close();
    }

    public MazePath get_path() {
        return runner.discover_path();
    }

    public boolean verify_path(MazePath seq) {
        return runner.navigate_path(seq);
    }
}
