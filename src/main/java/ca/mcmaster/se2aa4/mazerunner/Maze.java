package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    public final File PATH_FILE;

    public Maze(File filename) {
        // this.PATH_FILE = filename; 
        this.PATH_FILE = new File("examples/straight.maz.txt");
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

    private MazeRunner getRunner() {
        return new MazeRunner(PATH_FILE);
    }

    public MazePath findCorrectPath() {
        return getRunner().discoverPath();
    }

    public boolean verifyPath(MazePath seq) {
        return getRunner().navigate_path(seq);
    }
}
