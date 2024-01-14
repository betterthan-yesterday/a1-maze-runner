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
                logger.error("/!\\ An error has occured (no input) /!\\");
                System.exit(1);
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured (no args)) /!\\");
            System.exit(1);
        }

        logger.info("** Starting Maze Runner");
        try {
            logger.info("**** Reading the maze from file " + args[1]);
            BufferedReader reader = new BufferedReader(new FileReader(args[1]));
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
            logger.error("/!\\ An error has occured (file not found) /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
