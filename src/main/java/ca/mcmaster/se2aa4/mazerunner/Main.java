package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        // try {
        //     if (!("-i".equals(args[0]) || "--input".equals(args[0]))) {
        //         logger.error("/!\\ An error has occured (no input) /!\\");
        //         System.exit(1);
        //     }
        // } catch (Exception e) {
        //     logger.error("/!\\ An error has occured (no args)) /!\\");
        //     System.exit(1);
        // }

        logger.info("** Starting Maze Runner");
        try {
            Configuration config = configure(args);
            logger.info("**** Reading the maze from file " + config.filename);

            try {
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
            } catch (IOException ioe) {
                logger.error(ioe.getMessage());
            }

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
            if (!filename.exists()) 
                throw new IllegalArgumentException(filename + " does not exist.");
        }
    }
}
