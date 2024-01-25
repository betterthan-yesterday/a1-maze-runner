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

        Configuration config = null;
        try {
            config = configure(args);
        } catch (ParseException pe) {
            logger.error(pe.getMessage());
            System.exit(1);
        }

        Maze themaze = null;
        try {
            themaze = new Maze(config.filename);
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
            System.exit(1);
        }
        MazePath thepath = new MazePath(config.path);

        try {
            logger.info("** Reading the maze from file " + config.filename);
            themaze.printMaze();
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
            System.exit(1);
        }

        if (thepath.getRaw().isEmpty()) {
            logger.info("** Computing path");
            MazePath path_sequence = themaze.findCorrectPath();
            System.out.println("A valid path is " + path_sequence.getFactorized());
        } else {
            logger.info("** Verifying path");
            Boolean path_verified = themaze.verifyPath(thepath);
            System.out.println("The inputed path is " + thepath.getRaw());
            System.out.println((path_verified ? "correct path" : "incorrect path"));
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
