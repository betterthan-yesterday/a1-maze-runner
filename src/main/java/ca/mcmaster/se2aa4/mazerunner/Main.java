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
            logger.info("** Input is: " + config);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        Maze themaze = null;
        try {
            themaze = new Maze(config.filename, config.algo);

            logger.info("** Reading the maze from file " + config.filename);
            // themaze.printMaze();
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
            ioe.printStackTrace();
            System.exit(1);
        }

        if (config.path == null) {
            logger.info("** Computing path");
            MazePath path_sequence = themaze.findCorrectPath();
            System.out.println(path_sequence.getFactorized());
        } else {
            MazePath thepath = new MazePath(config.path);
            logger.info("** Verifying path");
            logger.info("The inputed path is " + thepath.getCanonical());
            
            Boolean path_verified = themaze.verifyPath(thepath);
            System.out.println((path_verified ? "correct path" : "incorrect path"));
        }

        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException  {
        Options options = new Options();
        Option input_file = new Option("i", "input", true, "File to read");
        Option input_path = new Option("p", "path", true, "Path to verify");
        Option input_algo = new Option("method", true, "Algorithm to use");
        input_file.setRequired(true);
        input_path.setRequired(false);
        input_algo.setRequired(false);
        options.addOption(input_file);
        options.addOption(input_path);
        options.addOption(input_algo);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        File file_to_read = new File(cmd.getOptionValue(input_file));
        String path_to_verify = cmd.getOptionValue(input_path);
        String algo_to_use = cmd.getOptionValue(input_algo, "righthand");
        return new Configuration(file_to_read, path_to_verify, algo_to_use);
    }

    private record Configuration(File filename, String path, String algo) {
        
        Configuration {
            if (!filename.exists())
                throw new IllegalArgumentException("File does not exist");
            if (path != null && path.isEmpty())
                throw new IllegalArgumentException("Path cannot be empty");
            if (!(algo.equals("righthand") || algo.equals("tremaux")))
                throw new IllegalArgumentException("Algorithm not found");
        }
    }
}
