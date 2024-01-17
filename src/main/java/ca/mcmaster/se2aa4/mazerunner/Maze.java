package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    public final File PATH_FILE;
    private final Navigator pather;

    public Maze(File filename) {
        this.PATH_FILE = filename; 
        this.pather = new Navigator(PATH_FILE);
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
        return pather.discover_path();
    }

    public boolean verify_path(MazePath seq) {
        return pather.navigate_path(seq);
    }
}
