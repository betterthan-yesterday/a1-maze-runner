package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            if (!("-i".equals(args[0]) || "--input".equals(args[0]))) {
                System.err.println("/!\\ An error has occured (no input) /!\\");
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("/!\\ An error has occured (no args)) /!\\");
            System.exit(1);
        }

        System.out.println("** Starting Maze Runner");
        try {
            System.out.println("**** Reading the maze from file " + args[1]);
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
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
        } catch(Exception e) {
            System.err.println("/!\\ An error has occured (file not found) /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }
}
