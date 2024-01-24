package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.algorithms.PathAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.algorithms.RighthandAlgorithm;

public class MazeRunner {

    private final int[][] maze_array;
    private int[] start = new int[2];
    private int[] end = new int[2];

    public MazeRunner(int[][] array) {
        this.maze_array = array;
        getStartEnd();
    }

    private void getStartEnd() {
        for (int row = 0; row < maze_array.length; row++) {
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

    public MazePath discoverPath() {
        PathAlgorithm algo = new RighthandAlgorithm(maze_array, start, end);
        MazePath maze_solution = algo.solve();
        return maze_solution;
    }

    public boolean followPath(MazePath seq) {
        Navigator nav = new Navigator(maze_array, seq);
        boolean checkFromWest = nav.navigate(start, end);
        boolean checkFromEast = nav.navigate(end, start);
        return checkFromWest || checkFromEast;
    }

}
