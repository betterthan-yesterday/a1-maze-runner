package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;

public class MazeRunner {

    public final Maze maze;

    public MazeRunner(File pathname) {
        this.maze = new Maze(pathname);
    }

    public MazePath discover_path() {
        return new MazePath("FF");
    }

    public boolean navigate_path(MazePath seq) {
        return false;
    }

}
