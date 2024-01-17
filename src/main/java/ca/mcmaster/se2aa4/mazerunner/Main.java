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
            Maze themaze = new Maze(config.filename);

            try {
                themaze.print_maze();
            } catch (IOException ioe) {
                logger.error(ioe.getMessage());
            }

            MazePath path_sequence = themaze.get_path();
            System.out.println(path_sequence.sequence);

        } catch (ParseException pe) {
            logger.error(pe.getMessage());
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException  {
        Options options = new Options();
        Option input = new Option("i", "input", true, "File to read");
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        File file_to_read = new File(cmd.getOptionValue(input));
        return new Configuration(file_to_read);
    }

    private record Configuration(File filename) {
        
        Configuration {
        }
    }
}
