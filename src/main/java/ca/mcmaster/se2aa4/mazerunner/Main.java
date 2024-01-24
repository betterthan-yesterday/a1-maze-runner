package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Options;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        try {
            Configuration config = configure(args);
            logger.info("**** Reading the maze from file " + config.filename);
            Maze themaze;
            try {
                themaze = new Maze(config.filename);
                MazePath thepath = new MazePath(config.path);

                try {
                    themaze.printMaze();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage());
                }

                if (thepath.getSequence().isEmpty()) {
                    logger.info("**** Computing path");
                    MazePath path_sequence = themaze.findCorrectPath();
                    System.out.println("A valid path is " + path_sequence.getSequence());
                } else {
                    logger.info("**** Verifying path");
                    Boolean path_verified = themaze.verifyPath(thepath);
                    System.out.println("The inputed path is " + thepath.getSequence());
                    System.out.println("The inputed path is " + (path_verified ? "valid" : "invalid"));
                }
            } catch (IOException ioe) {
                logger.error(ioe.getMessage());
                System.exit(1);
            }
        } catch (ParseException pe) {
            logger.error(pe.getMessage());
        }
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException  {
        Options options = new Options();
        Option input_file = new Option("i", "input", true, "File to read");
        Option input_path = new Option("p", "path", true, "Path to verify");
        input_path.setRequired(false);
        options.addOption(input_file);
        options.addOption(input_path);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        File file_to_read = new File(cmd.getOptionValue(input_file));
        String path_to_verify = cmd.getOptionValue(input_path);
        return new Configuration(file_to_read, path_to_verify);
    }

    private record Configuration(File filename, String path) {
        
        Configuration {
        }
    }
}
