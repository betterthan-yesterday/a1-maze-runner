package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;

public class PathFinder {

    public final Maze maze;

    public PathFinder(File pathname) {
        this.maze = new Maze(pathname);
    }

    public MazePath navigate() {
        return new MazePath("FF");
    }

}
