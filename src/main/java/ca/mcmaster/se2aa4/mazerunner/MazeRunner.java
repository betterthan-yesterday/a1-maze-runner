package ca.mcmaster.se2aa4.mazerunner;


public class MazeRunner {

    private final int[][] maze_array;

    public MazeRunner(int[][] array) {
        this.maze_array = array;
    }

    public MazePath discoverPath() {
        return new MazePath("FFFFF");
    }

    public boolean navigate_path(MazePath seq) {
        if (seq.getSequence().equals("FFFFF")) {
            return true;
        }
        return false;
    }

}
