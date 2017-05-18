package uk.ac.aston.dc2310;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uk.ac.aston.dc2310.controller.ApplicationController;
import uk.ac.aston.dc2310.formatter.BasicOutputFormatter;
import uk.ac.aston.dc2310.formatter.OutputFormatter;
import uk.ac.aston.dc2310.formatter.PrettyOutputFormatter;

import java.util.Scanner;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public class ChineseEnglishDictionaryApplication {

    private static final Logger LOGGER = LogManager.getLogger(ChineseEnglishDictionaryApplication.class);

    public static void main(String[] args) {

        LOGGER.debug("Starting Chinese English Dictionary Application");

        OutputFormatter outputFormatter = null;

        // Check if mode has been passed as application argument
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("basic")) {
                outputFormatter = new BasicOutputFormatter();
            } else if (args[0].equalsIgnoreCase("pretty")) {
                outputFormatter = new PrettyOutputFormatter();
            }
        }

        // If not passed as an argument, prompt until mode is selected
        if (outputFormatter == null) {
            LOGGER.debug("Couldn't find 'Pretty' or 'Basic' as argument");
            System.out.println("Please type the mode with which you would like the run the application");
            System.out.println("Basic : Basic output with simple text formatting");
            System.out.println("Pretty : Pretty printed output in tabular format");

            Scanner reader = new Scanner(System.in);
            while (true) {
                System.out.print(">");
                String input = reader.next();
                if (input.equalsIgnoreCase("basic")) {
                    outputFormatter = new BasicOutputFormatter();
                    break;
                } else if (input.equalsIgnoreCase("pretty")) {
                    outputFormatter = new PrettyOutputFormatter();
                    break;
                } else {
                    System.out.println("Could not recognise input, please enter either 'Pretty' or 'Basic'");
                }
            }
        }

        // Using interface here to allow application to be implemented differently if wanted
        ApplicationController applicationController = new ApplicationController();

        applicationController.start(outputFormatter);

    }

}
