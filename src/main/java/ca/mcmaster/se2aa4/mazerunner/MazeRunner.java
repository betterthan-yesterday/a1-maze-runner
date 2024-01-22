package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;

public class MazeRunner {

    private final Maze maze;

    public MazeRunner(File pathname) {
        this.maze = new Maze(pathname);
    }

    public MazePath discoverPath() {
        return new MazePath("FFFFF");
    }

    public boolean navigate_path(MazePath seq) {
        return false;
    }

}
